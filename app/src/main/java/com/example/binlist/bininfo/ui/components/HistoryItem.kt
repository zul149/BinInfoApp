package com.example.binlist.bininfo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.unit.sp
import com.example.binlist.bininfo.data.BinHistory

// Компонент для отображения истории запроса
@Composable
fun HistoryItem(binHistory: BinHistory) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "BIN: ${binHistory.bin}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Scheme: ${binHistory.scheme ?: "N/A"}", color = Color.Gray)
            Text("Type: ${binHistory.type ?: "N/A"}", color = Color.Gray)
            Text("Brand: ${binHistory.brand ?: "N/A"}", color = Color.Gray)
            Text("Country: ${binHistory.countryName ?: "N/A"} (${binHistory.countryCode ?: "N/A"})", color = Color.Gray)
            Text("Bank: ${binHistory.bankName ?: "N/A"}", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("URL: ${binHistory.url ?: "N/A"}", color = Color.Gray)
            Text("City: ${binHistory.city ?: "N/A"}", color = Color.Gray)
        }
    }
}
