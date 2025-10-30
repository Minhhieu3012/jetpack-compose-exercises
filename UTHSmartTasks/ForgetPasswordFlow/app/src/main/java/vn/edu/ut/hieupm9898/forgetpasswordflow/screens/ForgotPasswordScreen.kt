package vn.edu.ut.hieupm9898.forgetpasswordflow.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import vn.edu.ut.hieupm9898.forgetpasswordflow.R

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    // state luu gia tri email
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Transparent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // Logo Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.height(80.dp).fillMaxWidth()
            )

            Text(
                text = "SmartTasks",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF00ACC1)
                )
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Forget Password Heading
        Text(
            text = "Forget Password?",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description
        Text(
            text = "Enter your Email, we will send you a verification code.",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(
                    text = "Your Email",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFA3A2A2),
                focusedBorderColor = Color(0xFF00ACC1),
                cursorColor = Color(0xFF00ACC1),
                unfocusedContainerColor = Color(0xFFF6F4F4),
                focusedContainerColor = Color(0xFFF6F4F4)
            ),
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Hien thi thong bao loi
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp, bottom = 15.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Next Button
        Button(
            onClick = {
                when {
                    email.isEmpty() -> {
                        message = "Vui lòng nhập email"
                        messageColor = Color(0xFFe83d37)
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color(0xFFe83d37)
                    }
                    else -> {
                        message =""
                        navController.navigate("verification/$email")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5)
            )
        ) {
            Text(
                text = "Next",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(navController = NavController(LocalContext.current))
}
