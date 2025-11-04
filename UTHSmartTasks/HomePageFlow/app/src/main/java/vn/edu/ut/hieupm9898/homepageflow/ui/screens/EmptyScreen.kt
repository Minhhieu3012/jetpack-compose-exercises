package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.edu.ut.hieupm9898.homepageflow.R

// ======================== Màn hình chính ==================================
@Composable
fun EmptyScreen(onFabClick: () -> Unit) {
    Scaffold(
        // BottomAppBar
        bottomBar = { EmptyBottomBar() },
        // Nut floating action button
        floatingActionButton = { EmptyFAB(onClick = onFabClick) },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F6F6))
                    .padding(paddingValues) // Áp dụng padding của Scaffold
                    .padding(horizontal = 16.dp) // Áp dụng padding ngang chung
            ) {
                // LazyColumn chỉ chứa Header, được neo ở trên cùng
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        EmptyHeader()
                    }
                }

                // đặt trong Box để có thể căn giữa
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(110.dp))

                    // Card hiển thị trạng thái trống
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE6E6E6)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp, horizontal = 28.dp), // padding ben trong card
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.empty_state),
                                contentDescription = "No Task",
                                modifier = Modifier
                                    .size(140.dp)
                                    .padding(start = 16.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "No Tasks Yet!",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Stay productive—add something to do",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }
        }
    )
}

// ======================== Header ==================================
@Composable
fun EmptyHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo UTH",
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFC2D7E8))
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "SmartTasks",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )

            Text(
                text = "A simple and efficient to-do app",
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF2196F3)
            )
        }

        IconButton(onClick = { /* TODO */}) {
            Image(
                painter = painterResource(id = R.drawable.noti_button),
                contentDescription = "Notification Button",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

// ======================== Thanh Bottom App Bar ==================================
@Composable
fun EmptyBottomBar() {
    BottomAppBar(
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        // Icon 1: Home
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f) // Chia đều không gian
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF2196F3) // Màu đã chọn
            )
        }

        // Icon 2: DateRange
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f) // Chia đều không gian
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date Range",
                modifier = Modifier.size(40.dp),
                tint = Color.Gray // Màu chưa chọn
            )
        }

        // Khoảng trống thủ công cho FAB
        Spacer(modifier = Modifier.weight(1f))

        // Icon 3: List
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f) // Chia đều không gian
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "List",
                modifier = Modifier.size(40.dp),
                tint = Color.Gray // Màu chưa chọn
            )
        }

        // Icon 4: Settings
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f) // Chia đều không gian
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(40.dp),
                tint = Color.Gray // Màu chưa chọn
            )
        }
    }
}

// ======================== Nut Floating Action Button (FAB) ==================================
@Composable
fun EmptyFAB(onClick: () -> Unit) {
    Box {
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = Color(0xFF2196F3),
            modifier = Modifier
                .size(70.dp)
                .offset(y = 50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(onFabClick = {})
}