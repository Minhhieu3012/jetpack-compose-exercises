package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.homepageflow.R
import vn.edu.ut.hieupm9898.homepageflow.data.model.Task


// ========================== Màn hình chính =====================================
@Composable
fun HomeScreen(
    tasks: List<Task>,
    onTaskClick: (String) -> Unit,
    onFabClick: () -> Unit
) {
    Scaffold(
        // BottomAppBar
        bottomBar = { TaskBottomBar() },
        // Nut floating action button
        floatingActionButton = { TaskFAB(onClick = onFabClick) },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F6F6))
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Khoang cach giua cac item
            ) {
                // Header
                item {
                    TaskHeader()
                }
                // Danh sach Tasks
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onTaskClick = { onTaskClick(task.id) }
                    )
                }
            }
        }
    )
}


// ========================== Header =====================================
@Composable
fun TaskHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
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

// ======================== Phần Task Item ==================================
@Composable
fun TaskItem(
    task: Task,
    onTaskClick: () -> Unit
) {

    // Logic mới: Đặt màu dựa trên status
    val cardColor = when (task.status) {
        "In Progress" -> Color(0xFFE1BBC1)
        "Pending" -> Color(0xFFC0DCB9)
        "Completed" -> Color(0xFFB7E9FF)
        else -> Color(0xFFE0E0E0)
    }

    var isChecked by remember { mutableStateOf(task.isCompleted) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Loai bo box shadow
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick() }
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 4.dp)
        ) {
            // Phan tren: Checkbox, title, desc
            Row(
                verticalAlignment = Alignment.Top,
            ) {

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        // TODO: Gọi ViewModel để lưu trạng thái này
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.padding(end = 8.dp) // Thêm padding bên phải Checkbox
                )

                Column {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF333333),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Phan duoi: Status, DateTime
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Status: ${task.status}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = task.dueDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}

// ======================== Thanh Bottom App Bar ==================================
@Composable
fun TaskBottomBar() {
    BottomAppBar(
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        // BottomAppBar là một RowScope.
        // Chúng ta dùng IconButton và Spacer để tạo khoảng trống cho FAB.

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

        // để chừa chỗ cho nút FAB ở giữa
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
fun TaskFAB(onClick: () -> Unit) {
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
fun HomeScreenPreview() {
    HomeScreen(tasks = emptyList(), onTaskClick = {}, onFabClick = {})
}