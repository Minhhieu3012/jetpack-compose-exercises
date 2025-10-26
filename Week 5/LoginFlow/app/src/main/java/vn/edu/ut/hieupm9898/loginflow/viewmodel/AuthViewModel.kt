package vn.edu.ut.hieupm9898.loginflow.viewmodel

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Data class cho user profile
data class UserProfile(
    val name: String = "",
    val email: String = "",
    val dateOfBirth: String = "",
    val photoUrl: String = ""
)

// Sealed class (lớp niêm phong) cho auth state
// định nghĩa các trạng thái có thể xảy ra trong quá trình đăng nhập
sealed class AuthState {
    object Idle : AuthState() // Trạng thái chờ, chưa làm gì
    object Loading : AuthState() // đang xử lý
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {
    // B1: Khởi tạo
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var googleSignInClient: GoogleSignInClient? = null

    init {
        checkCurrentUser()
    }

    // State cho trạng thái xác thực
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // State cho thông tin profile
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    // B1: KHởi tạo
    private fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            _authState.value = AuthState.Success(currentUser.uid)
            loadUserProfile()
        } else {
            _authState.value = AuthState.Idle
        }
    }

    private fun loadUserProfile() {
        val user = auth.currentUser
        user?.let {
            _userProfile.value = UserProfile(
                name = it.displayName ?: "",
                email = it.email ?: "",
                photoUrl = it.photoUrl?.toString() ?: ""
            )
        }
    }

    // B2: Chuẩn bị (UI gọi)
    // Khởi tạo GoogleSignInClient
    fun initGoogleSignIn(context: Context, webClientId: String) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    // B3: Bắt đầu đăng nhập (UI gọi)
    // Lấy Intent để launch Sign In
    fun getSignInIntent(): Intent? {
        _authState.value = AuthState.Loading
        return googleSignInClient?.signInIntent
    }

    // B4: Xử lý kết quả từ Google Sign In
    fun handleSignInResult(result: ActivityResult) {
        viewModelScope.launch { // chạy trên 1 coroutine
            try {
                // Lấy tài khoản google từ kết quả trả về
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    // Nếu lấy được tk google -> dùng nó để sign in firebase
                    firebaseAuthWithGoogle(account)
                } else {
                    _authState.value = AuthState.Error("Không thể lấy thông tin tài khoản")
                }
            } catch (e: ApiException) {
                _authState.value = AuthState.Error("Google Sign In thất bại: ${e.message}")
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Lỗi: ${e.message}")
            }
        }
    }

    // B5: Đăng nhập vào Firebase (nội bộ)
    private suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        try {
            // lấy idToken từ tài khoản google
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            // dùng token để sign in vào firebase
            val authResult = auth.signInWithCredential(credential).await()

            val user = authResult.user
            if (user != null) {
                // nếu thành công -> lưu thông tin vào state
                _authState.value = AuthState.Success(user.uid)
                loadUserProfile()
            } else {
                _authState.value = AuthState.Error("Đăng nhập Firebase thất bại")
            }
        } catch (e: Exception) {
            _authState.value = AuthState.Error("Firebase Auth thất bại: ${e.message}")
        }
    }

    // gọi hàm này khi nhấn nút logout (UI gọi)
    fun signOut(context: Context) {
        viewModelScope.launch {
            try {
                auth.signOut()
                googleSignInClient?.signOut()?.await()

                _authState.value = AuthState.Idle
                _userProfile.value = null
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Lỗi khi đăng xuất: ${e.message}")
            }
        }
    }

    // gọi hàm này để update thông tin profile
    fun updateUserProfile(name: String, email: String, dateOfBirth: String) {
        _userProfile.value = _userProfile.value?.copy(
            name = name,
            email = email,
            dateOfBirth = dateOfBirth
        )
    }
}