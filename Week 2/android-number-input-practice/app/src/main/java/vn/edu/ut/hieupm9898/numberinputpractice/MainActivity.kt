package vn.edu.ut.hieupm9898.numberinputpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.numberinputpractice.ui.theme.NumberInputPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberInputPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumberInputApp()
                }
            }
        }
    }
}

@Composable
fun NumberInputApp() {
    // Tạo trạng thái lưu trữ giá trị nhập và danh sách số
    var inputText by remember { mutableStateOf("") }
    var numberList by remember { mutableStateOf(emptyList<Int>()) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text ="Thực hành 02",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val textFieldsHeight = 50.dp

            // Tạo ô nhập số lượng
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text(text = "Nhập vào số lượng") },
                keyboardOptions = KeyboardOptions (
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = textFieldsHeight) // Giữ cho chiều cao cố định
                    .clip(RoundedCornerShape(18.dp))
                    .border( width = 1.dp, color = Color.Gray,  shape = RoundedCornerShape(18.dp))
            )

            // Tạo nút
            Button(
                onClick = {
                    try {
                        val number = inputText.toInt()
                        if (number > 0) {
                            numberList = (1..number).toList()
                            showError = false
                        } else {
                            showError = true
                            numberList = emptyList()
                        }
                    } catch(e: NumberFormatException) {
                        showError = true
                        numberList = emptyList()
                    }
                },
                modifier = Modifier
                    .defaultMinSize(minHeight = textFieldsHeight)
                    .width(80.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2395f3),
                    contentColor = Color.White
                )
            ) {
                Text("Tạo", fontSize = 18.sp)
            }
        }

        if(showError) {
            Text(
                text = "Dữ liệu bạn nhập không hợp lệ",
                color = Color(0xFFe83d37),
                modifier = Modifier.padding(8.dp)

            )
        }

        // Hiển thị danh sách số
        LazyColumn {
            items(numberList) { number ->
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = number.toString(),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NumberInputAppPreview() {
    NumberInputPracticeTheme {
        NumberInputApp()
    }
}