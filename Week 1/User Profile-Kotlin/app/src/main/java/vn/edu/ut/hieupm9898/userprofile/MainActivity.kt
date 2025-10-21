package vn.edu.ut.hieupm9898.userprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.annotation.DrawableRes

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale

import vn.edu.ut.hieupm9898.userprofile.ui.theme.UserProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserProfileTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF6F8FA)) {
                    UserProfileScreen()
                }
            }
        }
    }
}

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    name: String = "Phan Minh Hieu",
    location: String = "TP.Ho Chi Minh, VietNam",
    @DrawableRes avatarRes: Int = R.drawable.avatar_1
) {
    Box(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color(0xFF098589))
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // outer light-blue ring + inner circular image with white border
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(160.dp)) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFAED2DE))  //màu nền vòng ngoài
                )

                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, color = Color(0xFFE2E6EB), shape = CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF101010)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = location,
                fontSize = 20.sp,
                color = Color(0xFF6B6B6B)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfile() {
    UserProfileTheme {
        UserProfileScreen()
    }
}


