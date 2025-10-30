package vn.edu.ut.hieupm9898.forgetpasswordflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vn.edu.ut.hieupm9898.forgetpasswordflow.screens.ForgotPasswordScreen
import vn.edu.ut.hieupm9898.forgetpasswordflow.ui.theme.ForgetPasswordFlowTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.forgetpasswordflow.screens.ConfirmScreen
import vn.edu.ut.hieupm9898.forgetpasswordflow.screens.ResetPasswordScreen
import vn.edu.ut.hieupm9898.forgetpasswordflow.screens.VerificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForgetPasswordFlowTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "forgot_password") {
                    composable("forgot_password") {
                        ForgotPasswordScreen(navController = navController)
                    }

                    composable("verification/{email}") { navBackStackEntry -> // Truyền email qua route
                        val email = navBackStackEntry.arguments?.getString("email") ?: ""
                        VerificationScreen(navController = navController, email = email)
                    }

                    composable("reset_password/{email}") { navBackStackEntry ->
                        val email = navBackStackEntry.arguments?.getString("email") ?: ""
                        ResetPasswordScreen(navController = navController, email = email)
                    }

                    composable("confirm/{email}") { navBackStackEntry ->
                        val email = navBackStackEntry.arguments?.getString("email") ?: ""
                        ConfirmScreen(navController = navController)
                    }
                }
            }
        }
    }
}
