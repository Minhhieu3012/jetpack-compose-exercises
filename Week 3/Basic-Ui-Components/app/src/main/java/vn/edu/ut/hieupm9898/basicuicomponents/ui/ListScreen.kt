package vn.edu.ut.hieupm9898.basicuicomponents.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import vn.edu.ut.hieupm9898.basicuicomponents.navigation.Screen // <-- THÊM IMPORT

@Composable
fun ListScreen(navController: NavController) { // <-- Đổi tên hàm
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "UI Components List",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                textAlign = TextAlign.Center
            )
        }
        item { SectionHeader(title = "Display") }
        item {
            ComponentCard(
                title = "Text",
                description = "Displays text",
                onClick = { navController.navigate(Screen.TextDetail.route) } // Sửa route
            )
        }
        item {
            ComponentCard(
                title = "Image",
                description = "Displays an image",
                onClick = { navController.navigate(Screen.ImageDetail.route) } // Sửa route
            )
        }
        item { SectionHeader(title = "Input") }
        item {
            ComponentCard(
                title = "TextField",
                description = "Input field for text",
                onClick = { navController.navigate(Screen.TextField.route) } // Sửa route
            )
        }
        item {
            ComponentCard(
                title = "PasswordField",
                description = "Input field for passwords"
                // onClick = { navController.navigate(Screen.PasswordField.route) } // Bỏ comment khi bạn tạo màn hình này
            )
        }
        item { SectionHeader(title = "Layout") }
        item {
            ComponentCard(
                title = "Column",
                description = "Arranges elements vertically",
                onClick = { navController.navigate(Screen.ColumnLayoutDetail.route) } // Sửa route
            )
        }
        item {
            ComponentCard(
                title = "Row",
                description = "Arranges elements horizontally",
                onClick = { navController.navigate(Screen.RowLayoutDetail.route) } // Sửa route
            )
        }
        item {
            ComponentCard(
                title = "Tự tìm hiểu",
                description = "Tìm ra tất cả các thành phần UI cơ bản",
                backgroundColor = Color(0xFFF5B0B0)
            )
        }
    }
}

// Các hàm SectionHeader và ComponentCard giữ nguyên
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun ComponentCard(
    title: String,
    description: String,
    backgroundColor: Color = Color(0xFFA7D0F1),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = description,
                fontSize = 14.sp
            )
        }
    }
}