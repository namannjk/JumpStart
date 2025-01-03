package com.example.jumpstart.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel
import com.example.jumpstart.Components.BottomAppBar
import com.example.jumpstart.Components.TopAppBar
import com.example.jumpstart.models.Item


@Composable
fun GridView(
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    //val homeUiState = appViewModel._homeUiState.collectAsState().value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(appViewModel,title = "Explore",navController) },
        bottomBar = { BottomAppBar(appViewModel,navController) }
    ){ contentPadding ->
        ShowGridList(
            itemsList = appViewModel.items.value,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun ShowGridList(itemsList : List<Item>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Number of columns (e.g., 2 columns)
        modifier = modifier.padding(16.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(itemsList) { item ->
            GridItem(item)
        }
    }
}

@Composable
fun GridItem(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp), // Space between cards
        colors = CardDefaults.cardColors(
            containerColor = Color.White)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                modifier = Modifier.width(100.dp).height(100.dp),
              //  colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "₹${item.price}",
                fontWeight = FontWeight.Light,
                color = Color.DarkGray
            )
        }

    }
}

