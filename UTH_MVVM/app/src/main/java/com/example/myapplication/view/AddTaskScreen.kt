package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.TaskPriority
import com.example.myapplication.viewmodel.AddTaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel,
    onBackClick: () -> Unit = {},
    onTaskSaved: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle navigation after save
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onTaskSaved()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = "Add New",
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        // Form Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Task Title
            Text(
                text = "Task",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            OutlinedTextField(
                value = uiState.title,
                onValueChange = viewModel::updateTitle,
                placeholder = { Text("Do homework") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1E88E5),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                )
            )

            // Description
            Text(
                text = "Description",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            OutlinedTextField(
                value = uiState.description,
                onValueChange = viewModel::updateDescription,
                placeholder = { Text("Don't give up") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1E88E5),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            // Error Message
            uiState.error?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }


            // Add Button
            Button(
                onClick = { viewModel.saveTask() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        text = "Add",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}