package vn.edu.ut.hieupm9898.forgetpasswordflow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import vn.edu.ut.hieupm9898.forgetpasswordflow.R

@Composable
fun VerificationScreen(navController: NavController, email: String) {
    val otpLength = 5
    var otpValues by remember { mutableStateOf(List(otpLength) { "" }) }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Transparent) }

    val focusRequesters = List(otpLength) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Back button
        IconButton(
            onClick = { navController.popBackStack() },
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
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
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

        // Heading
        Text(
            text = "Verify Code",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description
        Text(
            text = "Enter the code we just sent you on your registered Email",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // OTP Input Fields
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            otpValues = otpValues.toMutableList().also { it[index] = newValue }

                            // Auto move focus
                            when {
                                newValue.isNotEmpty() && index < otpLength - 1 -> {
                                    focusRequesters[index + 1].requestFocus()
                                }
                                newValue.isEmpty() && index > 0 -> {
                                    focusRequesters[index - 1].requestFocus()
                                }
                                index == otpLength - 1 && newValue.isNotEmpty() -> {
                                    focusManager.clearFocus()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .focusRequester(focusRequesters[index]),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFADADAD),
                        focusedBorderColor = Color(0xFF00ACC1),
                        cursorColor = Color(0xFF00ACC1)
                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true
                )
            }
        }

        // Error message
        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = message, color = messageColor, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Next Button
        Button(
            onClick = {
                val otp = otpValues.joinToString("")
                when {
                    otp.isBlank() -> {
                        message = "Vui lòng nhập mã OTP."
                        messageColor = Color.Red
                    }
                    otp.length < otpLength -> {
                        message = "Mã OTP phải đủ $otpLength số."
                        messageColor = Color.Red
                    }
                    else -> {
                        navController.navigate("reset_password/$email")
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
fun VerificationScreenPreview() {
    VerificationScreen(
        navController = NavController(LocalContext.current),
        email = "james.madison@examplepetstore.com"
    )
}
