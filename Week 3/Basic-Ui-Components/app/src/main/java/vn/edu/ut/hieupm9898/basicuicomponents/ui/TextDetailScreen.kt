package vn.edu.ut.hieupm9898.basicuicomponents.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TextDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Text(
                text = "Text Detail",
                modifier = Modifier.weight(1f),
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier.weight(1f))
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            StyledText()
        }
    }
}
// StyledText() giữ nguyên
@Composable
fun StyledText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 32.sp)) { append("The ") }
        withStyle(style = SpanStyle(fontSize = 32.sp, textDecoration = TextDecoration.LineThrough)) { append("quick ") }
        withStyle(style = SpanStyle(color = Color(0xFFD2691E), fontSize = 50.sp, fontWeight = FontWeight.W400)) { append("B") }
        withStyle(style = SpanStyle(color = Color(0xFFD2691E), fontSize = 32.sp)) { append("rown") }
        withStyle(style = SpanStyle(fontSize = 32.sp)) { append("\nfox j u m p s ") }
        withStyle(style = SpanStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) { append("over") }
        withStyle(style = SpanStyle(fontSize = 32.sp, textDecoration = TextDecoration.Underline)) { append("\nthe") }
        withStyle(style = SpanStyle(fontSize = 32.sp)) { append(" ") }
        withStyle(style = SpanStyle(fontSize = 32.sp, fontFamily = FontFamily.Cursive)) { append("lazy") }
        withStyle(style = SpanStyle(fontSize = 32.sp)) { append(" dog.") }
    }
    Text(text = annotatedString, lineHeight = 40.sp, modifier = Modifier.padding(horizontal = 16.dp))
}