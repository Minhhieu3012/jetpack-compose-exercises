package vn.edu.ut.hieupm9898.userinfovalidator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.userinfovalidator.ui.theme.UserInfoValidatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserInfoValidatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NameAgeApp()
                }
            }
        }
    }
}

// Hàm phân loại tuổi
fun classifyAge(age: Int): String {
    return when {
        age > 65 -> "Người già (>65)"
        age in 6..65 -> "Người lớn (6-65)"
        age in 2..5 -> "Trẻ em (2-6)"
        age in 0..1 -> "Em bé (<2)"
        else -> "Tuổi không hợp lệ"
    }
}

@Composable
fun NameAgeApp() {
    // Trạng thái của các biến
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Transparent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 22.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Khung chứa nhập họ tên và tuổi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
                .padding(30.dp)
        ) {
            Column {

                // Ô nhập họ và tên
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "Họ và tên",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 20.dp)
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier
                            .weight(2f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

                }

                Spacer(modifier = Modifier.height(12.dp))

                // Ô nhập tuổi
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "Tuổi",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 20.dp),
                    )
                    TextField(
                        value = age,
                        onValueChange = { age = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(2f) // chiếm khoảng 2 phần không gian bên phải
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Thông báo
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        // Nút kiểm tra
        Button(
            onClick = {
                messageColor = Color.Red
                val trimmedName = name.trim()
                val trimmedAge = age.trim()

                when {
                    trimmedName.isEmpty() -> message = "Vui lòng nhập họ và tên"
                    trimmedAge.isEmpty() -> message = "Vui lòng nhập tuổi"
                    else -> {
                        val ageInt = trimmedAge.toIntOrNull()
                        if (ageInt == null) {
                            message = "Tuổi phải là số"
                        } else if (ageInt < 0) {
                            message = "Tuổi không hợp lệ"
                        } else {
                            messageColor = Color(0xFF2E7D32)
                            message = classifyAge(ageInt)
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1565C0)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(48.dp)
        ) {
            Text("Kiểm tra", fontSize = 20.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NameAgeAppPreview() {
    UserInfoValidatorTheme {
        NameAgeApp()
    }
}