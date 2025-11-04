package vn.edu.ut.hieupm9898.homepageflow.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path
import vn.edu.ut.hieupm9898.homepageflow.data.model.BaseResponse
import vn.edu.ut.hieupm9898.homepageflow.data.model.Task

// ĐỊNH NGHĨA ENDPOINT API MÀ ỨNG DỤNG SẼ GIAO TIẾP
interface ApiService { // Thư viện Retrofit gọi các API được định nghĩa phương thức HTTP (GET,DELETE)

    @GET("api/researchUTH/tasks") // Đường dẫn tương đối
    // Trả về BaseResponse của một List<Task>
    suspend fun getTasks(): BaseResponse<List<Task>>

    @GET("api/researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): BaseResponse<Task>

    @DELETE("api/researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit>
}