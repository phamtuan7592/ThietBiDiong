package com.example.tuan4_th2

import android.media.Image
import android.service.quicksettings.Tile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PageItemModel(
    val tile: String,
    val desc: String,
     val image: Int
)

@Composable
fun PageItem(page : PageItemModel){
    Column(modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.tile,
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = page.tile,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text= page.desc,
            fontSize = 15.sp,
            color = Color.Gray
        )
    }
}
