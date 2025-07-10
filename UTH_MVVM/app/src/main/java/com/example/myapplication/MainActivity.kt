package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.repository.TaskRepositoryImpl
import com.example.myapplication.view.TaskListScreen
import com.example.myapplication.view.AddTaskScreen
import com.example.myapplication.viewmodel.AddTaskViewModel
import com.example.myapplication.viewmodel.AddTaskViewModelFactory
import com.example.myapplication.viewmodel.TaskListViewModel
import com.example.myapplication.viewmodel.TaskListViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UTHSmartTasksApp()
            }
        }
    }
}

@Composable
fun UTHSmartTasksApp() {
    val repository = remember { TaskRepositoryImpl() }
    var currentScreen by remember { mutableStateOf("list") }

    when (currentScreen) {
        "list" -> {
            val viewModel: TaskListViewModel = viewModel(
                factory = TaskListViewModelFactory(repository)
            )
            TaskListScreen(
                viewModel = viewModel,
                onAddTaskClick = { currentScreen = "add" },
                onBackClick = { /* Handle back navigation */ }
            )
        }

        "add" -> {
            val viewModel: AddTaskViewModel = viewModel(
                factory = AddTaskViewModelFactory(repository)
            )
            AddTaskScreen(
                viewModel = viewModel,
                onBackClick = { currentScreen = "list" },
                onTaskSaved = { currentScreen = "list" }
            )
        }
    }
}