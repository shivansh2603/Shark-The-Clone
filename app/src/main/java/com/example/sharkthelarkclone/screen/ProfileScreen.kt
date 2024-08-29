package com.example.sharkthelarkclone.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column {
        Text("This is Profile Screen")
        Button(onClick = { navController.navigate("HomeScreen") }) {
            Text("Go to Home Screen")
        }
    }
    // ... add more composable here
}