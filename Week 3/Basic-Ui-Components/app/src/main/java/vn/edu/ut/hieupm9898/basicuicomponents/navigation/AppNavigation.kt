package vn.edu.ut.hieupm9898.basicuicomponents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.basicuicomponents.ui.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route // Bắt đầu từ màn hình Welcome
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.List.route) {
            ListScreen(navController = navController)
        }
        composable(Screen.TextDetail.route) {
            TextDetailScreen(navController = navController)
        }
        composable(Screen.ImageDetail.route) {
            ImageDetailScreen(navController = navController)
        }
        composable(Screen.TextField.route) {
            TextFieldScreen(navController = navController)
        }
        // composable(Screen.PasswordField.route) { PasswordFieldScreen(navController) } // Bỏ comment khi bạn tạo màn hình này
        composable(Screen.ColumnLayoutDetail.route) {
            ColumnLayoutDetailScreen(navController = navController)
        }
        composable(Screen.RowLayoutDetail.route) {
            RowLayoutDetailScreen(navController = navController)
        }
    }
}