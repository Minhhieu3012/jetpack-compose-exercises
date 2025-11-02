package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vn.edu.ut.hieupm9898.homepageflow.data.model.Task
import vn.edu.ut.hieupm9898.homepageflow.data.network.RetrofitClient

// data class dai dien cho toan bo state cua giao dien ma viewModel xu ly
data class TaskUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(), // Ds cho home screen
    val selectedTask: Task? = null, // Du lieu cho Detail screen
    val error: String? = null // Xu ly loi
)

// ViewModel
class TasksViewModel : ViewModel() {

    // lay api client
    private val apiService = RetrofitClient.api

    // _uiState la trang thai noi bo (co the thay doi)
    private val _uiState = MutableStateFlow(TaskUiState())

    // uiState la trang thai hien thi (chi doc)
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    // Tai ds task khi khoi tao viewModel
    init {
        loadTasks()
    }

    // ham goi api lay tat ca tasks
    fun loadTasks() {
        viewModelScope.launch {
            _uiState.update{ it.copy(isLoading = true, error = null) }

            try {
                val taskList = apiService.getTasks()

                _uiState.update {
                    it.copy(isLoading = false, tasks = taskList)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Không thể tải danh sách task: ${e.message}")
                }
            }
        }
    }

    // Ham goi api de lay chi tiet 1 Task
    fun loadTaskDetail(taskId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Gọi API với taskId
                val taskDetail = apiService.getTaskDetail(taskId) //

                _uiState.update {
                    it.copy(isLoading = false, selectedTask = taskDetail)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Không thể tải chi tiết task: ${e.message}")
                }
            }
        }
    }

    // Ham goi api de xoa 1 Task
    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Gọi API Xóa
                apiService.deleteTask(taskId)

                // Sau khi xóa, không cần làm gì ở đây.
                // UI sẽ tự động điều hướng về.
                _uiState.update { it.copy(isLoading = false) }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Không thể xóa task: ${e.message}")
                }
            }
        }
    }
}