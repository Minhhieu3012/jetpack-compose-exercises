package vn.edu.ut.hieupm9898.homepageflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.edu.ut.hieupm9898.homepageflow.ui.screens.DetailScreen
import vn.edu.ut.hieupm9898.homepageflow.ui.screens.EmptyScreen
import vn.edu.ut.hieupm9898.homepageflow.ui.screens.HomeScreen
import vn.edu.ut.hieupm9898.homepageflow.ui.screens.TasksViewModel
import vn.edu.ut.hieupm9898.homepageflow.ui.theme.HomePageFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomePageFlowTheme {
                // Tao NavController
                val navController = rememberNavController()

                // Tao ViewModel
                val viewModel: TasksViewModel = viewModel()

                // Lang nghe state tu viewModel
                val uiState by viewModel.uiState.collectAsState()

                // Thiet lap NavHost (bieu do dieu huong)
                NavHost(navController = navController, startDestination = "home") {
                    // Route cho man hinh chinh (chua logic home or empty)
                    composable("home") {
                        if(uiState.tasks.isEmpty() && !uiState.isLoading) {
                            EmptyScreen()
                        } else {
                            HomeScreen(
                                tasks = uiState.tasks,
                                onTaskClick = { taskId ->
                                    navController.navigate("detail/$taskId")
                                }
                            )
                        }
                    }

                    // Route cho man hinh chi tiet
                    composable("detail/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")

                        LaunchedEffect(taskId) {
                            if (taskId != null) {
                                viewModel.loadTaskDetail(taskId)
                            }
                        }

                        DetailScreen(
                            task = uiState.selectedTask,
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onDeleteClick = {
                                if (taskId != null) {
                                    viewModel.deleteTask(taskId)
                                    // Sau khi xóa, quay lại
                                    navController.popBackStack()
                                    // Yêu cầu load lại danh sách sau khi quay về
                                    viewModel.loadTasks()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
