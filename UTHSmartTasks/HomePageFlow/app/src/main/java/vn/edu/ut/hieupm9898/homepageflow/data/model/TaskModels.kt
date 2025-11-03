package vn.edu.ut.hieupm9898.homepageflow.data.model

import com.google.gson.annotations.SerializedName

/**
 * 1. ĐỐI TƯỢNG BAO BỌC (WRAPPER) CHUNG
 */
data class BaseResponse<T>(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: T // T có thể là List<Task> hoặc Task
)

/**
 * 2. CẬP NHẬT TASK MODEL
 * Khớp chính xác với các trường trong JSON
 */
data class Task(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("priority")
    val priority: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("dueDate")
    val dueDate: String,

    @SerializedName("subtasks")
    val subtasks: List<Subtask>,

    @SerializedName("attachments")
    val attachments: List<Attachment>
)

/**
 * 3. CẬP NHẬT SUBTASK MODEL
 */
data class Subtask(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("isCompleted")
    val isCompleted: Boolean
)

/**
 * 4. CẬP NHẬT ATTACHMENT MODEL
 */
data class Attachment(
    @SerializedName("id")
    val id: String,

    @SerializedName("fileName")
    val filename: String,

    @SerializedName("fileUrl")
    val fileUrl: String
)