package com.example.myapplication.model


import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.MEDIUM
)

enum class TaskPriority(val color: Long) {
    LOW(0xFF81C784),      // Xanh lá
    MEDIUM(0xFF64B5F6),   // Xanh dương
    HIGH(0xFFFFB74D),     // Vàng
    URGENT(0xFFE57373)    // Đỏ
}

// UI States
data class TaskListUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class AddTaskUiState(
    val title: String = "",
    val description: String = "",
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)