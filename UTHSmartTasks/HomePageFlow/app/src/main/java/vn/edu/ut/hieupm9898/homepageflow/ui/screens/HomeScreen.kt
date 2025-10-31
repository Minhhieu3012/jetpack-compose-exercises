package vn.edu.ut.hieupm9898.homepageflow.ui.screens

import androidx.compose.ui.graphics.Color

// Sample Data
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val dateTime: String,
    val color: Color,
    val isCompleted: Boolean
)

// Sample Task List
val sampleTasks = listOf(
    Task(
        id = 1,
        title = "Complete Android Project",
        description = "Finish the UI, integrate API, and\n write documentation",
        status = "Status: In Progress",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFFE1BBC1),
        isCompleted = true
    ),

    Task(
        id = 2,
        title = "Doctor Appointment 2",
        description = "This task is related to Work. It\n needs to be completed",
        status = "Status: Pending",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFF94E398),
        isCompleted = true
    ),

    Task(
        id = 3,
        title = "Meeting",
        description = "This task is related to Fitness. It\n needs to be completed",
        status = "Status: Pending",
        dateTime = "14:00 2500-03-26",
        color = Color(0xFFB7E9FF),
        isCompleted = false
    )
)