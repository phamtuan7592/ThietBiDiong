package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.AddTaskUiState
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskPriority
import com.example.myapplication.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun updatePriority(priority: TaskPriority) {
        _uiState.value = _uiState.value.copy(priority = priority)
    }

    fun saveTask() {
        val currentState = _uiState.value

        if (currentState.title.isBlank()) {
            _uiState.value = currentState.copy(error = "Title cannot be empty")
            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(isLoading = true)
            try {
                val task = Task(
                    title = currentState.title,
                    description = currentState.description,
                    priority = currentState.priority
                )
                repository.addTask(task)
                _uiState.value = currentState.copy(
                    isLoading = false,
                    isSaved = true,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = AddTaskUiState()
    }
}

class AddTaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
