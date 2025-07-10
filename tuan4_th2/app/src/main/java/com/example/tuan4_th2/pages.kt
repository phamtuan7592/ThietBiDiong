package com.example.tuan4_th2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

val pageItems = listOf(
    PageItemModel(
        "Easy Time Management",
        "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
        R.drawable.anh1
    ),
    PageItemModel(
        "Increase Work Effectiveness",
        "Time management and the determination of more important tasks will give your job statistics better and always improve",
        R.drawable.anh2
    ),
    PageItemModel(
        "Increase Work Effectiveness",
        "Time management and the determination of more important tasks will give your job statistics better and always improve",
        R.drawable.anh2
    ),
    PageItemModel(
        "Reminder Notification",
        "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
        R.drawable.anh2
    )
)

@Composable
fun Page(navController: NavController) {
    val pagerState = rememberPagerState { pageItems.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            PageItem(page = pageItems[page])
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage > 0) {
                IconButton(
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF006EE9))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp)) // giữ chỗ nút back khi không hiển thị
            }

            Button(
                onClick = {
                    if (pagerState.currentPage < pageItems.lastIndex) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        navController.navigate("startScreen") {
                            popUpTo("pages") { inclusive = true }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == pageItems.lastIndex) "Get Started" else "Next",
                    color = Color.White
                )
            }
        }
    }
}


