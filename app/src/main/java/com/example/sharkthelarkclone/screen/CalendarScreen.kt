package com.example.sharkthelarkclone.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CalendarScreen(navController: NavController) {
    Column {
        Text("This is Calendar Screen")
        Button(onClick = { navController.navigate("WorkSpaceScreen") }) {
            Text("Go to WorkSpace Screen")
        }
    }
    // ... add more composable here
}