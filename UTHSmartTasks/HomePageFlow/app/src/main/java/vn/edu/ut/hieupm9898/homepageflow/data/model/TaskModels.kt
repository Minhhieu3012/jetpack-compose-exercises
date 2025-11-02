package vn.edu.ut.hieupm9898.homepageflow.data.model

import com.google.gson.annotations.SerializedName

// Model chinh cho 1 Task, bao gom tat ca thong tin chi tiet tu DetailScreen
data class Task(
    @SerializedName("id")
    val id: String, // dung String cho id tu api se an toan

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("category")
    val category: String, // Them tu DetailScreen

    @SerializedName("priority")
    val priority: String, // Them tu DetailScreen

    @SerializedName("dateTime")
    val dateTime: String,

    @SerializedName("colorHex")
    val colorHex: String,

    @SerializedName("subtasks")
    val subtasks: List<Subtask>, // Them tu DetailScreen

    @SerializedName("attachments")
    val attachments: List<Attachment> // Them tu DetailScreen
)

// Model cho 1 Subtask
data class Subtask(
    @SerializedName("id")
    val id: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("isCompleted")
    val isCompleted: Boolean
)

// Model cho 1 Attachment
data class Attachment(
    @SerializedName("id")
    val id: String,

    @SerializedName("filename")
    val filename: String,

    @SerializedName("url")
    val url: String
)

