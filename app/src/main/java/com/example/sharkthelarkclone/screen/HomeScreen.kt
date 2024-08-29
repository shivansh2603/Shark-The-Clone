package com.example.sharkthelarkclone.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Text("This is Home Screen")
        Button(onClick = { navController.navigate("CalendarScreen") }) {
            Text("Go to Calendar Screen")
        }
    }
    // ... add more composable here
}