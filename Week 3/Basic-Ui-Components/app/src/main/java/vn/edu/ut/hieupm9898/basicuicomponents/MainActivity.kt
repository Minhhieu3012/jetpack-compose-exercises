package vn.edu.ut.hieupm9898.basicuicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import vn.edu.ut.hieupm9898.basicuicomponents.navigation.AppNavigation
import vn.edu.ut.hieupm9898.basicuicomponents.ui.theme.BasicUiComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUiComponentsTheme {
                AppNavigation()
            }
        }
    }
}