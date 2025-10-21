package vn.edu.ut.hieupm9898.basicuicomponents.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RowLayoutDetailScreen(navController: NavController) {
    // Định nghĩa các màu sắc
    val lightBlue = Color(0xFFBEC6F6)
    val darkBlue = Color(0xFF3F81D1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // P1: Icon back
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(32.dp)
                            .clickable{ navController.popBackStack() }
                    )
                }
            }

            // P2: Title
            Text(
                text = "Row Layout",
                modifier = Modifier.weight(1f),
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // P3: Box rỗng để cân bằng
            Box(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Xếp các hàng theo chiều dọc
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Khoảng cách giữa các hàng
        ) {
            // Tạo 4 hàng giống nhau
            repeat(4) {
                // Mỗi hàng là một Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(Color(0xFFE0DDDD), shape = RoundedCornerShape(16.dp)),
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Khoảng cách giữa các ô
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tạo ô có bo góc và màu nền
                    Surface(
                        modifier = Modifier
                            .weight(1f) // Chia đều chiều rộng cho 3 ô
                            .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                            .height(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = lightBlue
                    ) {}
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 2.dp, top = 12.dp, end = 2.dp, bottom = 12.dp)
                            .height(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = darkBlue
                    ) {}
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 12.dp, end = 8.dp, bottom = 12.dp)
                            .height(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = lightBlue
                    ) {}
                }
            }
        }
    }
}