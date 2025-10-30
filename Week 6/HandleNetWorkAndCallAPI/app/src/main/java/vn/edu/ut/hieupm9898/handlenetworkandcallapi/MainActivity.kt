package vn.edu.ut.hieupm9898.handlenetworkandcallapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import vn.edu.ut.hieupm9898.handlenetworkandcallapi.ui.screen.HandleNetWorkAndCallAPIApp
import vn.edu.ut.hieupm9898.handlenetworkandcallapi.ui.theme.HandleNetWorkAndCallAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandleNetWorkAndCallAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HandleNetWorkAndCallAPIApp()
                }
            }
        }
    }
}