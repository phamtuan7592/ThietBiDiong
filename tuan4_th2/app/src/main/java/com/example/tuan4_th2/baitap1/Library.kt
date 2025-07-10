package com.example.tuan4_th2.baitap1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.jar.Attributes.Name
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.graphics.Color


@Composable
fun BookItem(book: Book, isSelected: Boolean, onCheckedChange: () -> Unit){
    Card(Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isSelected, onCheckedChange = {onCheckedChange()},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFD32F2F))
            )
            Text(book.title, Modifier.weight(1f))
        }
    }
}

@Composable
fun Library(navController: NavHostController, viewModel: LibraryViewModel) {
    var name by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hệ thống \n Quản lý thư viện",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.padding(15.dp))


        Column(Modifier.padding(16.dp)) {
            Text("Sinh viên", fontWeight = FontWeight.Medium)

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nhập tên sinh viên") },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            viewModel.updateStudent(name)
                            showList = true
                        }
                    }
                ) {
                    Text("Thay đổi")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showList) {
                Text("Danh sách sinh viên:", fontWeight = FontWeight.Medium)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 150.dp)
                ) {
                    items(viewModel.getStudents()) { student ->
                        Text(
                            text = student.name,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    name = student.name
                                    viewModel.updateStudent(student.name)
                                    showList = false
                                }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.padding(15.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F0F0)
            )
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Sách đã mượn", fontWeight = FontWeight.Medium)

                val borrowed = viewModel.borrowedBooks()
                if (borrowed.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Bạn chưa mượn quyển sách nào", color = Color.Black)
                            Text("Nhấn 'Thêm' để bắt đầu hành trình đọc sách!", color = Color.Black)
                        }
                    }
                } else {
                    LazyColumn {
                        items(borrowed) { book ->
                            BookItem(book, true) {
                                viewModel.toggleBorrow(book)
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.padding(15.dp))

        if (viewModel.isShowingAvailable()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0F0F0)
                )
            ) {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    Text("Chọn sách để mượn", fontWeight = FontWeight.Medium)
                    val available = viewModel.getAvailableBooks()
                    if (available.isEmpty()) {
                        Text("Không còn sách khả dụng.", color = Color.Black)
                    } else {
                        LazyColumn {
                            items(available) { book ->
                                BookItem(book, false) {
                                    viewModel.toggleBorrow(book)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.padding(15.dp))
        }
        Button(
            onClick = { viewModel.toggleShowAvailable() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        )
        {
            Text(if (viewModel.isShowingAvailable()) "Ẩn danh sách" else "Thêm")
        }
    }
}
