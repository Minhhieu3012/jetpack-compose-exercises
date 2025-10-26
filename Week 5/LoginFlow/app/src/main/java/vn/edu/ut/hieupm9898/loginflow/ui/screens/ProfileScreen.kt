package vn.edu.ut.hieupm9898.loginflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import vn.edu.ut.hieupm9898.loginflow.R
import vn.edu.ut.hieupm9898.loginflow.viewmodel.AuthViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class) // Cần thiết để dùng các API mới của Material3
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel() // lấy viewModel
) {
    val context = LocalContext.current

    // 1. lắng nghe thông tin profile từ viewModel
    val userProfile by authViewModel.userProfile.collectAsState()

    // 2. Tạo Local state để lưu trữ thông tin người dùng
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var photoUrl by remember { mutableStateOf("") }

    // 3. Cập nhật các local state khi dữ liệu từ viewModel thay đổi
    LaunchedEffect(userProfile) {
        userProfile?.let { profile ->
            name = profile.name
            email = profile.email
            dateOfBirth = profile.dateOfBirth
            photoUrl = profile.photoUrl
        }
    }

    // 4. State điều khiển việc ẩn / hiện của datePicker
    var showDatePicker by remember { mutableStateOf(false) }

    // State của chính datePicker (để biết ngày nào đang được chọn)
    val datePickerState = rememberDatePickerState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Profile",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196F3)
                        )
                    }
                },

                // nút back (điều hướng)
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .background(
                                color = Color(0xFF2196F3),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                },
                actions = {

                    // Nút log out (điều hướng)
                    IconButton(
                        onClick = {
                            authViewModel.signOut(context) // Thêm context parameter
                            navController.navigate("login") {
                                popUpTo("profile") { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color(0xFFE53935),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Anh dai dien (avatar)
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
            ) {
                AsyncImage(
                    model = photoUrl.ifEmpty { "https://randomuser.me/api/portraits/women/44.jpg" },
                    placeholder = painterResource(id = R.drawable.avatar),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                // nút đổi ảnh (camera)
                IconButton(
                    onClick = { /* TODO: Chức năng đổi ảnh */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(36.dp)
                        .offset(x = 10.dp, y = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_camera),
                        contentDescription = "Change profile picture",
                        tint = Color(0xFF3F51B5),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // nhap ten
            Text(
                text = "Name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Enter your name", color = Color(0xFF544C4C)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color(0xFF2196F3)
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            // nhap email
            Text(
                text = "Email",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Enter your email", color = Color(0xFF544C4C)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color(0xFF2196F3)
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            // nhap ngay sinh
            Text(
                text = "Date of Birth",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Date",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color(0xFF2196F3)
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            // nut save
            Button(
                onClick = {
                    authViewModel.updateUserProfile(name, email, dateOfBirth)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(
                    text = "Save Changes",
                    fontWeight = FontWeight(weight = 600),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // nut back
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    text = "Back",
                    fontWeight = FontWeight(weight = 600),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
    // Hiển thị Dialog khi showDatePicker == true
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false }, // tắt dialog khi nhấn ra ngoài
            confirmButton = { // nút ok
                TextButton(
                    onClick = {
                        showDatePicker = false
                        // lấy ngày đã chọn từ datePickerState (dưới dạng mili giây)
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            // chuyển mili giây sang ngày
                            val localDate = Instant.ofEpochMilli(selectedDateMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            dateOfBirth = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        }
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = { // nút cancel (đóng dialog)
                TextButton(onClick = { showDatePicker = false }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            // Nội dung của dialog là composable DatePicker
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}