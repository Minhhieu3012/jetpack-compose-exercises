package vn.edu.ut.hieupm9898.loginflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.loginflow.ui.screens.LoginScreen
import vn.edu.ut.hieupm9898.loginflow.ui.screens.ProfileScreen
import vn.edu.ut.hieupm9898.loginflow.ui.theme.LoginFlowTheme
import vn.edu.ut.hieupm9898.loginflow.viewmodel.AuthViewModel
import vn.edu.ut.hieupm9898.loginflow.viewmodel.AuthState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()

    // Tự động chuyển đến profile nếu đã đăng nhập
    val startDestination = if (authState is AuthState.Success) "profile" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(route = "profile") {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}