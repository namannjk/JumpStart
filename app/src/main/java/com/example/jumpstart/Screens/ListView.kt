package com.example.jumpstart.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel
import com.example.jumpstart.Components.BottomAppBar
import com.example.jumpstart.Components.TopAppBar
import com.example.jumpstart.models.Item


@Composable
fun ListView(
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(appViewModel,title = "Explore",navController) },
        bottomBar = { BottomAppBar(appViewModel,navController) }
    ) { contentPadding ->
        ShowList(
            itemsList = appViewModel.items.value,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun ShowList(itemsList: List<Item>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
         contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsList) { item ->
            ItemDes(item = item)
        }
    }
}


@Composable
fun ItemDes(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp), // Space between cards
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Image on the left
                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Space between image and text
                // Text content
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp,bottom = 5.dp)
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "MRP: ₹${item.price}",
                            fontWeight = FontWeight.Light,
                            color = Color.DarkGray
                        )
                        Text(
                            text = item.shippingTime + " shipping",
                            fontWeight = FontWeight.Light,
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            // Divider after the text (aligned to exclude the image)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 106.dp), // Adjust padding for alignment
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}

