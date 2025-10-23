package vn.edu.ut.hieupm9898.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.firebaseauth.ui.theme.FirebaseAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseAuthApp()
                }
            }
        }
    }
}

@Composable
fun FirebaseAuthApp() {
    var loginResult by remember { mutableStateOf<LoginResult?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val success = (0..1).random() == 1
                loginResult = if (success)
                    LoginResult.Success("sample@gmail.com")
                else
                    LoginResult.Error("User canceled the Google sign-in process.")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2395f3),
                contentColor = Color.White
            )
        ) {
            Text(text = "Login by Gmail", fontSize = 22.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hiển thị thông báo lỗi hoặc thành công
        AnimatedVisibility(visible = loginResult != null) {
            when (val result = loginResult) {
                is LoginResult.Success -> SuccessMessage(result.email)
                is LoginResult.Error -> ErrorMessage(result.message)
                null -> {}
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFEAC7C4), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Google Sign-In Failed",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = message,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun SuccessMessage(email: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFB8DDF8), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Success!",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Hi $email\nWelcome to UTHSmartTasks",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}

// Kết quả đăng nhập
sealed class LoginResult {
    data class Success(val email: String) : LoginResult()
    data class Error(val message: String) : LoginResult()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FirebaseAuthAppPreview() {
    FirebaseAuthTheme {
        FirebaseAuthApp()
    }
}


