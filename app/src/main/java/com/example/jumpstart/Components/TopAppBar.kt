package com.example.jumpstart.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(appViewModel: AppViewModel, title: String,navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFe6eaf6))
            .padding(16.dp)

    ){
        Row(){
            Text(
                text = title,
                modifier = Modifier.padding(start = 28.dp,top = 30.dp, bottom = 0.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            if(appViewModel.currentScreen.value != 3) {
                Text(text = "Filter",
                    color = Color(0xFF599c6b),
                    modifier = Modifier
                        .padding(
                            start = 200.dp,
                            end = 10.dp,
                            top = 30.dp,
                            bottom = 0.dp
                        )
                        .clickable {
                            navHostController.navigate("filterScreen")
                        }
                )
            }
        }
        if(appViewModel.currentScreen.value == 1 || appViewModel.currentScreen.value == 2) {
            TextField(
                value = appViewModel.currentSearch.value,
                onValueChange = { appViewModel.updateSearch(it) },
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White

                ),
                placeholder = { Text("Search") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}


