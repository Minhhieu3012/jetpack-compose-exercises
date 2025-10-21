package vn.edu.ut.hieupm9898.librarymanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.librarymanagementsystem.data.SampleData
import vn.edu.ut.hieupm9898.librarymanagementsystem.ui.theme.LibraryManagementSystemTheme
import vn.edu.ut.hieupm9898.librarymanagementsystem.ui.theme.components.BookItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibraryManagementSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LibraryManagementSystemApp()
                }
            }
        }
    }
}

@Composable
fun LibraryManagementSystemApp() {
    val studentList = SampleData.StudentList
    var currentStudentIndex by remember { mutableStateOf(0) }
    val currentStudent = studentList[currentStudentIndex]

    var borrowedBookIds by remember(currentStudent) {
        mutableStateOf(currentStudent.borrowBookIds.toMutableList())
    }

    Scaffold(
        bottomBar = {
            AppBottomNavigation()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hệ thống\nQuản lý thư viện",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(80.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sinh viên",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // day cac muc ra 2 phia
            ) {
                TextField(
                    value = currentStudent.name,
                    onValueChange = { },
                    readOnly = true,
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

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { currentStudentIndex = (currentStudentIndex + 1) % studentList.size },
                    modifier = Modifier.fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0b54b0),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "Thay đổi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Danh sách",
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFCDC9C9))
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (borrowedBookIds.isEmpty()) {
                    // Nếu danh sách mượn rỗng, hiển thị Text
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    LazyColumn {
                        items(SampleData.BookList) { book ->
                            // Chỉ hiển thị những sách sinh viên đã mượn
                            if (borrowedBookIds.contains(book.id)) {
                                BookItem(
                                    book = book,

                                    isChecked = true, // Luôn được check vì đây là sách đã mượn
                                    onItemClick = {
                                        // Vẫn cho phép bỏ tick để trả sách
                                        borrowedBookIds.remove(book.id)
                                        borrowedBookIds = borrowedBookIds.toMutableList()
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Nut them
            Button(
                modifier = Modifier.fillMaxWidth(fraction = 0.5f),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0b54b0),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = "Thêm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// Composable cho thanh điều hướng dưới cùng
@Composable
fun AppBottomNavigation() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val items = listOf("Quản lý", "DS Sách", "Sinh viên")
    val icons = listOf(Icons.Default.Home, Icons.AutoMirrored.Filled.List, Icons.Default.Face)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedTab == index,
                onClick = { selectedTab = index }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LibraryManagementSystemAppPreview() {
    LibraryManagementSystemTheme {
        LibraryManagementSystemApp()
    }
}

