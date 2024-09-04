package com.example.sharkthelarkclone.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextOverflow
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
}

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(username: String = "User") {
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Surface( // Add Surface to apply background color
                color = Color(0xFFd59f35),
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
                        Icon(
                            painter = painterResource(R.drawable.account_box_24px),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            tint = MaterialTheme.colorScheme.onSurface
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
        Chat("George", "Happy Birthday!", "Yesterday"),
        Chat("Helen", "Can you review the document?", "3 days ago"),
        Chat("Ivy", "See you at the party!", "Last week"),
        Chat("Jack", "Let's catch up soon.", "10 minutes ago"),
        Chat("Karen", "Finished the report.", "4 hours ago"),
        Chat("Leo", "Are you free tomorrow?", "2 days ago"),
        Chat("Mona", "Dinner at 7?", "6 hours ago"),
        Chat("Nathan", "Congrats on the promotion!", "A week ago"),
        Chat("Olivia", "Miss you!", "5 minutes ago"),
        Chat("Paul", "Check your email.", "3 hours ago"),
        Chat("Quinn", "Running late, sorry!", "30 minutes ago"),
        Chat("Rachel", "Can we reschedule?", "Yesterday"),
        Chat("Sam", "I'm outside your place.", "Just now"),
        Chat("Tina", "Thank you for your help!", "Last night"),
        Chat("Uma", "Good morning!", "7 hours ago"),
        Chat("Victor", "Got your message.", "2 days ago"),
        Chat("Wendy", "Let's discuss this tomorrow.", "4 days ago"),
        Chat("Xander", "Are we still on for lunch?", "Yesterday"),
        Chat("Yara", "Just landed, call you soon.", "An hour ago"),
        Chat("Zane", "Had a great time today!", "2 minutes ago")
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(chats.size) { index ->
            ChatListItem(chats[index], navController) {
                navController.navigate("chat/${chats[index].name}")
            }
            if(index < chats.size -1){
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ChatListItem(chat: Chat, navController: NavHostController, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.account_circle_24px),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = chat.timestamp,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF053630)
        )
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