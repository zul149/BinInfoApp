package com.example.binlist.bininfo.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.binlist.bininfo.data.BinInfo

// Компонент для отображения информации о BIN
@Composable
fun BinInfoCard(binInfo: BinInfo) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Card Info",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Scheme: ${binInfo.scheme ?: "N/A"}", color = Color.Gray)
            Text("Type: ${binInfo.type ?: "N/A"}", color = Color.Gray)
            Text("Brand: ${binInfo.brand ?: "N/A"}", color = Color.Gray)
            Text("Prepaid: ${if (binInfo.prepaid == true) "Yes" else "No"}", color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Country Info",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            binInfo.country?.let {
                Text("Name: ${it.name ?: "N/A"} ${it.emoji ?: ""}", color = Color.Gray)
                Text("Code: ${it.alpha2 ?: "N/A"}", color = Color.Gray)
                Text("Currency: ${it.currency ?: "N/A"}", color = Color.Gray)
                Text(
                    text = "Coordinates: ${it.latitude}, ${it.longitude}",
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        openMap(context, it.latitude ?: 0.0, it.longitude ?: 0.0)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bank Info",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            binInfo.bank?.let {
                Text("Name: ${it.name ?: "N/A"}", color = Color.Gray)
                Text("City: ${it.city ?: "N/A"}", color = Color.Gray)
                Text(
                    text = "URL: ${it.url ?: "N/A"}",
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        openBrowser(context, it.url)
                    }
                )
                Text(
                    text = "Phone: ${it.phone ?: "N/A"}",
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        openDialer(context, it.phone)
                    }
                )
            }
        }
    }
}


private fun openBrowser(context: Context, url: String?) {
    val finalUrl = url ?: "https://yandex.ru"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
    context.startActivity(intent)
}

private fun openDialer(context: Context, phone: String?) {
    val phoneNumber = phone ?: ""
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    context.startActivity(intent)
}

private fun openMap(context: Context, latitude: Double, longitude: Double) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}