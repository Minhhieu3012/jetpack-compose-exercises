package vn.edu.ut.hieupm9898.forgetpasswordflow.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ConfirmScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Transparent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // button back
        IconButton(
            onClick = { navController.popBackStack()},
            modifier = Modifier
                .align(Alignment.Start)
                .background(
                    color = Color(0xFF1E88E5),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

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
            text = "Confirm",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description
        Text(
            text = "We are here to help you!",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Nhap username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username", color = Color.Gray) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Username", tint = Color.Gray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFC9C7C7),
                focusedBorderColor = Color(0xFF1E88E5),
                unfocusedContainerColor = Color(0xFFE7E7E7),
                focusedContainerColor = Color(0xFFE7E7E7)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nhap email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "Your email", color = Color.Gray) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email", tint = Color.Gray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFC9C7C7),
                focusedBorderColor = Color(0xFF1E88E5),
                unfocusedContainerColor = Color(0xFFE7E7E7),
                focusedContainerColor = Color(0xFFE7E7E7)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nhap password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Your Password", color = Color.Gray) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password", tint = Color.Gray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFC9C7C7),
                focusedBorderColor = Color(0xFF1E88E5),
                unfocusedContainerColor = Color(0xFFE7E7E7),
                focusedContainerColor = Color(0xFFE7E7E7)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                color = messageColor,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Next Button
        Button(
            onClick = {
                when {
                    username.isBlank() || password.isBlank() || email.isBlank() -> {
                        message = "Vui lòng nhập đầy đủ thông tin"
                        messageColor = Color.Red
                    }

                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color(0xFFe83d37)
                    }

                    password.length < 8 -> {
                        message = "Mật khẩu phải có ít nhất 8 ký tự"
                        messageColor = Color.Red
                    }
                    else -> {
                        navController.navigate("/$email")
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
                text = "Submit",
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
fun ConfirmScreenPreview() {
    ConfirmScreen(navController = NavController(LocalContext.current))
}