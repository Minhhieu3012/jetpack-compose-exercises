package vn.edu.ut.hieupm9898.loginflow.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.loginflow.R
import vn.edu.ut.hieupm9898.loginflow.viewmodel.AuthState
import vn.edu.ut.hieupm9898.loginflow.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    // 1. State Management
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    // lấy context ( cần cho google sign in)
    val context = LocalContext.current

    // theo dõi state xác thực từ viewModel
    val authState by authViewModel.authState.collectAsState()

    // tạo 1 state điều khiển snackbar (thông báo lỗi)
    val snackbarHostState = remember { SnackbarHostState() }

    // Web Client ID từ google-services.json
    val webClientId = "496991862577-kaaki1429g8tjd212t9us1fvqd6reec0.apps.googleusercontent.com"

    // 2. Xử lý logic (side effects)
    // Khởi tạo Google Sign In 1 lần duy nhất
    LaunchedEffect(Unit) {
        authViewModel.initGoogleSignIn(context, webClientId)
    }

    // Chuẩn bị 1 trình chạy (launcher) để nhận kết quả trả về từ activity đăng nhập google
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Khi google trả về kết quả, gửi nó cho viewModel xử lý
        authViewModel.handleSignInResult(result)
    }

    // Xử lý trạng thái đăng nhập
    LaunchedEffect(authState) {
        when (val state = authState) {
            is AuthState.Success -> {
                navController.navigate("profile") {
                    // Xóa màn hình login khỏi ngăn xếp
                    popUpTo("login") { inclusive = true }
                }
            }
            is AuthState.Error -> {
                // Hiển thị thông báo lỗi
                snackbarHostState.showSnackbar(state.message)
            }
            else -> {} // Không làm gì trong các trạng thái khác
        }
    }

    // 3. Bố cục giao diện UI
    // Scaffold là 1 layout cơ bản của material 3 (cho phép có bottomBar, snackBarHost,...)
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }, // Vị trí hiển thị snackbar
        bottomBar = {
            Text(
                text = "© UTHSmartTasks",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                fontSize = 18.sp,
                color = Color(0xFF4A4646)
            )
        }
    ) { paddingValues -> // giúp nội dung không bị che bởi bottomBar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(80.dp))

                // logo uth
                Image(
                    painter = painterResource(id = R.drawable.uth_logo),
                    contentDescription = "UTH Logo",
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ten ung dung
                Text(
                    text = "SmartTasks",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
                Text(
                    text = "A simple and efficient to-do app",
                    fontSize = 14.sp,
                    color = Color(0xFF2196F3)
                )

                Spacer(modifier = Modifier.height(100.dp))

                // tieu de welcome
                Text(
                    text = "Welcome",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ready to explore? Log in to get started.",
                    fontSize = 16.sp,
                    color = Color(0xFF4A4646),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                // nut dang nhap
                OutlinedButton(
                    onClick = {
                        // Khi click vào nút, lấy intent từ viewModel và khởi chạy nó
                        val signInIntent = authViewModel.getSignInIntent()
                        signInIntent?.let { launcher.launch(it) }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD5EDFF)
                    ),

                    // vô hiệu hóa nút nếu đang ở trạng thái Loading
                    enabled = authState !is AuthState.Loading
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        // UI động (khi trạng thái là Loading)
                        if (authState is AuthState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color(0xFF130160)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_google_logo),
                                contentDescription = "Google Logo",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))

                        // thay đổi cả text và nút dựa trên state
                        Text(
                            text = if (authState is AuthState.Loading) "ĐANG ĐĂNG NHẬP..." else "SIGN IN WITH GOOGLE",
                            fontWeight = FontWeight(weight = 600),
                            color = Color(0xFF130160)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}