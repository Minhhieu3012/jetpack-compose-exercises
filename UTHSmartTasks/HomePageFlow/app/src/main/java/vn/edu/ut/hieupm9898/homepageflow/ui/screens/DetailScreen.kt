package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.homepageflow.R
import vn.edu.ut.hieupm9898.homepageflow.data.model.Attachment
import vn.edu.ut.hieupm9898.homepageflow.data.model.Subtask
import vn.edu.ut.hieupm9898.homepageflow.data.model.Task


// ================================= Màn hình chính =====================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    task: Task?,
    onNavigateBack: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = { DetailTopBar(
            onNavigateBack = onNavigateBack,
            onDeleteClick = onDeleteClick
        ) } // Thanh bar tren cung (fixed)
    ) { paddingValues ->
        if(task != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Tieu de Task
                DetailHeader(task = task)
                Spacer(modifier = Modifier.height(16.dp))

                // The thong tin (category, status, priority)
                InfoCard(task = task)
                Spacer(modifier = Modifier.height(24.dp))

                // Phan Subtasks
                SubtasksSection( subtasks = task.subtasks)
                Spacer(modifier = Modifier.height(24.dp))

                // Phan attachments
                AttachmentsSection(attachments = task.attachments)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ================================= Thanh Top Bar =====================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onNavigateBack: () -> Unit,
    onDeleteClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(top = 12.dp),
        title = {
            Text(
                text = "Detail",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
        },

        // Nut back
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .background(Color(0xFF2196F3), RoundedCornerShape(16.dp))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        },

        // Nut Delete
        actions = {
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.delete_navigation),
                    contentDescription = "Delete",
                    modifier = Modifier.size(60.dp)
                )
            }
        },

        // Dat mau nen TopAppBar
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

// ================================= Phần Tiêu đề =====================================
@Composable
fun DetailHeader(task: Task) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = task.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

// ================================= Phần Info Card =====================================
@Composable
fun InfoCard(task: Task) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE1BBC1)
        ),
        elevation = CardDefaults.cardElevation(0.dp) // Khong co bong
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoItem(
                iconResId = R.drawable.category,
                label = "Category",
                value = task.category
            )
            InfoItem(
                iconResId = R.drawable.status,
                label = "Status",
                value = task.status
            )
            InfoItem(
                iconResId = R.drawable.priority,
                label = "Priority",
                value = task.priority
            )
        }
    }
}

// ================================= Thông tin Item (Icon + 2 dòng text) =====================================
@Composable
fun InfoItem(iconResId: Int, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

// ================================= Phần Subtasks =====================================
@Composable
fun SubtasksSection(subtasks: List<Subtask>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Subtasks",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Dùng vòng lặp để hiển thị tất cả subtask
        subtasks.forEach { subtask ->
            SubtaskItem(text = subtask.title)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// ================================= Thông tin subtasks (Checkbox + Text) =====================================
@Composable
fun SubtaskItem(text: String) {
    var isChecked by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFE6E6E6)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                lineHeight = 20.sp // Tăng khoảng cách dòng
            )
        }
    }
}

// ================================= Phần Attachments =====================================
@Composable
fun AttachmentsSection(attachments: List<Attachment>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Attachments",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Dùng vòng lặp để hiển thị tất cả attachment
        attachments.forEach { attachment ->
            AttachmentItem(filename = attachment.filename)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// ================================= Thông tin Attachments =====================================
@Composable
fun AttachmentItem(filename: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFE6E6E6)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.attachment_icon),
                contentDescription = "File",
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = filename,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(onNavigateBack = {}, onDeleteClick = {}, task = null)
}