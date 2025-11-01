package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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

// Sample Data
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val dateTime: String,
    val color: Color,
    val isCompleted: Boolean
)

// Sample Task List
val sampleTasks = listOf(
    Task(
        id = 1,
        title = "Complete Android Project",
        description = "Finish the UI, integrate API, and\n write documentation",
        status = "Status: In Progress",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFFE1BBC1),
        isCompleted = true
    ),

    Task(
        id = 2,
        title = "Doctor Appointment 2",
        description = "This task is related to Work. It\n needs to be completed",
        status = "Status: Pending",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFFBAD7BA),
        isCompleted = true
    ),

    Task(
        id = 3,
        title = "Meeting",
        description = "This task is related to Fitness. It\n needs to be completed",
        status = "Status: Pending",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFFB7E9FF),
        isCompleted = false
    )
)

// Màn hình chính
@Composable
fun HomeScreen() {
    // Quan ly trang thai cua danh sach task
    var tasks by remember { mutableStateOf(sampleTasks) }

    Scaffold(
        // BottomAppBar
        bottomBar = { TaskBottomBar() },
        // Nut floating action button
        floatingActionButton = { TaskFAB() },
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
                        onCheckedChange = { newCheckedState ->
                            // cap nhat trang thai cua task
                            tasks = tasks.map {
                                if (it.id == task.id) {
                                    it.copy(isCompleted = newCheckedState)
                                } else {
                                    it
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

// Cac thanh phan Composable
// ======================== Header ==================================
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

// ======================== Mot the Task Item ==================================
@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = task.color
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Loai bo box shadow
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            // Phan tren: Checkbox, title, desc
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black,
                    ),
                )

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF333333),
                        lineHeight = 20.sp
                    )
                }
            }

            // Phan duoi: Status, DateTime
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = task.status,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = task.dateTime,
                    style = MaterialTheme.typography.bodyLarge,
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
        modifier = Modifier
            .height(65.dp), // tang chieu cao
        content = {
            NavigationBar(
                containerColor = Color.Transparent, // Loai bo mau nen
                tonalElevation = 0.dp
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(30.dp)
                        ) },
                    selected = true,
                    onClick = { /* TODO */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2196F3),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Date Range",
                            modifier = Modifier.size(30.dp)
                        ) },
                    selected = false,
                    onClick = { /* TODO */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2196F3),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )

                // Item trống để tạo không gian cho FAB
                NavigationBarItem(
                    icon = { /* Empty */ },
                    enabled = false,
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "List",
                            modifier = Modifier.size(30.dp)
                        ) },
                    selected = false,
                    onClick = { /* TODO */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2196F3),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(30.dp)
                        ) },
                    selected = false,
                    onClick = { /* TODO */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2196F3),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    )
}

// ======================== Nut Floating Action Button (FAB) ==================================
@Composable
fun TaskFAB() {
    FloatingActionButton(
        onClick = { /*TODO: Xử lý thêm task mới*/ },
        shape = CircleShape,
        containerColor = Color(0xFF2196F3),
        modifier = Modifier.offset(y = 30.dp).size(70.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Task",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}