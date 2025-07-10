package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Task
import com.example.myapplication.viewmodel.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    onAddTaskClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "List",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1E88E5)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF1E88E5), CircleShape)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFE53935), CircleShape)
                ) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )


        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF1E88E5))
                }
            }

            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = Color.Red
                    )
                }
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Task List
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 16.dp)
                    ) {
                        items(uiState.tasks) { task ->
                            TaskItem(
                                task = task,
                                onToggleComplete = { viewModel.toggleTaskCompletion(task) }
                            )
                        }
                    }

                    // Floating Action Button
                    FloatingActionButton(
                        onClick = onAddTaskClick,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        containerColor = Color(0xFF1E88E5)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add Task",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onToggleComplete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(task.priority.color)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = task.description,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
