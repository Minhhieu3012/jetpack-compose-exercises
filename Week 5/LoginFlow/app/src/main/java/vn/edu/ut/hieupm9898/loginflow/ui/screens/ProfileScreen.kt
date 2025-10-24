package vn.edu.ut.hieupm9898.loginflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import vn.edu.ut.hieupm9898.loginflow.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    // State
    var name by remember { mutableStateOf("Melissa Peters") }
    var email by remember { mutableStateOf("melpeters@gmail.com") }
    var dateOfBirth by remember { mutableStateOf("23/05/1995") }

    // State dieu khien hien thi datepicker
    var showDatePicker by remember { mutableStateOf(false) }
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
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack()},
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
                    Spacer(modifier = Modifier.size(48.dp))
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

            // Anh dai dien
            AsyncImage(
                model = "https://randomuser.me/api/portraits/women/44.jpg",
                placeholder = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

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
                onValueChange = { name = it }, // update state khi user nhap
                placeholder = { Text(text = "Enter your name", color = Color(0xFF544C4C)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

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
                onValueChange = { email = it }, // update state khi user nhap
                placeholder = { Text(text = "Enter your email", color = Color(0xFF544C4C)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

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
                onValueChange = {}, // khong can vi chung ta dung datepicker
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Date",
                        modifier = Modifier.size(36.dp)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color(0xFF544C4C),
                    focusedTextColor = Color.Black
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))

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
    // logic cho datepickerDialog
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        // lay ngay da chon va dinh dang lai
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if(selectedDateMillis != null) {
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
            dismissButton = {
                TextButton(onClick = {showDatePicker = false}) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}