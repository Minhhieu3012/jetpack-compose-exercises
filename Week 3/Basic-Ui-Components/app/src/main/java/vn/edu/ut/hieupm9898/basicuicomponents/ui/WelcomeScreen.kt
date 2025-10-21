package vn.edu.ut.hieupm9898.basicuicomponents.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.basicuicomponents.R
import vn.edu.ut.hieupm9898.basicuicomponents.navigation.Screen // <-- THÊM IMPORT
import vn.edu.ut.hieupm9898.basicuicomponents.ui.theme.BasicUiComponentsTheme

@Composable
fun WelcomeScreen(navController: NavController) { // <-- THÊM NavController
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 8.dp)
            .padding(bottom = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "Phan Minh Hieu", fontWeight = FontWeight.W800, fontSize = 20.sp)
            Text(text = "089205019898", fontWeight = FontWeight.W500, fontSize = 16.sp)
        }
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier.height(233.dp).width(216.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = "Jetpack Compose", fontWeight = FontWeight.W500, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 16.sp,
                color = Color(0xFF4A4646),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Button(
            // <-- THAY ĐỔI Ở ĐÂY: Thêm hành động điều hướng
            onClick = { navController.navigate(Screen.List.route) },
            modifier = Modifier.width(340.dp).height(60.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(text = "I'm ready", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenAppPreview() {
    BasicUiComponentsTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}