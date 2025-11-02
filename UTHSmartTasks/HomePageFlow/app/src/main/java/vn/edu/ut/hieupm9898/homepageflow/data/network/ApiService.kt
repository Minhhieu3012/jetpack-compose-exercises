package vn.edu.ut.hieupm9898.homepageflow.data.network

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import vn.edu.ut.hieupm9898.homepageflow.data.model.Task

interface ApiService {
    // GET | https://amock.io/api/researchUTH/tasks
    @GET("api/researchUTH/tasks")
    suspend fun getTasks(): List<Task> // lay ds tat ca tasks

    // GET | https://amock.io/api/researchUTH/task/1
    @GET("api/researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): Task // lay chi tiet 1 Task

    // DEL | https://amock.io/api/researchUTH/task/1
    @DELETE("api/researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit> // xoa 1 Task
}