package com.example.sharkthelarkclone.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sharkthelarkclone.R

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ChatList(navController)
        }
    }
    // ... add more composable here
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(username: String = "User") {
    // to track whether search mode is active or not
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Surface( // Add Surface to apply background color
                color = Color(0xFF9DBDFF),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp,
                    bottomStart = 16.dp, bottomEnd = 16.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Add padding for content within Surface
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //profile photo
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Image(
                            painter = painterResource(R.drawable.icons8_male_user_50),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = username, style = MaterialTheme.typography.titleMedium)
                    }
                    IconButton(onClick = { /* Handle search icon click (if needed) */ }) {
                        Icon(
                            imageVector = Filled.Search,
                            contentDescription = "Search"
                        )
                    }

                }
            }
        }
    )
}

@Composable
fun ChatList(navController: NavHostController) {
    val chats = listOf(
        Chat("Alice", "Hey, how are you?", "10:30 AM"),
        Chat("Bob", "Did you see the game last night?", "Yesterday"),
        Chat("Charlie", "Meeting at 3 PM?", "2 hours ago"),
        Chat("David", "Just checking in...", "5 days ago")
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(chats) { chat ->
            ChatListItem(chat, navController) {
                navController.navigate("chat/${chat.name}")
            }
        }
    }
}

@Composable
fun ChatListItem(chat: Chat, navController: NavHostController, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.icons8_male_user_50),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = chat.name, style = MaterialTheme.typography.titleMedium)
            Text(text = chat.lastMessage, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(Modifier.weight(1f))
        Text(text = chat.timestamp, style = MaterialTheme.typography.bodySmall)
    }
}

data class Chat(
    val name: String,
    val lastMessage: String,
    val timestamp: String
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(chatName: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(chatName) })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("Chat with $chatName")
            // Add chat messages and input field here
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}