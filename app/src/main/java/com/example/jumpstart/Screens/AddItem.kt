package com.example.jumpstart.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel
import com.example.jumpstart.Components.BottomAppBar
import com.example.jumpstart.Components.TopAppBar

@Composable
fun AddItem(appViewModel: AppViewModel, navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(appViewModel,title = "Add Item",navController) },
        bottomBar = { BottomAppBar(appViewModel,navController) }
    ) { contentPadding ->
        AddItemComponent(
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemComponent(modifier: Modifier) {
    var itemName by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val shippingMethods = listOf("Same Day Shipping", "One Day Shipping", "Two Day Shipping")
    var selectedMethod by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = itemPrice,
            onValueChange = { itemPrice = it },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("Item Price") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = selectedMethod,
            onValueChange = { selectedMethod = it },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize() // Assign size for DropdownMenu
                },
            label = { Text("Shipping Method") },
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            shippingMethods.forEach { method ->
                DropdownMenuItem(
                    onClick = {
                        selectedMethod = method
                        expanded = false
                    },
                    text = { Text(text = method) }
                )
            }
        }


        Button(
            onClick = { /* Handle click */ },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF599c6b)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Submit", color = Color.White, textAlign = TextAlign.Center)
        }
    }
}


