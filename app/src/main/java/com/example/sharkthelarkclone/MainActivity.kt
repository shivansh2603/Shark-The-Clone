package com.example.sharkthelarkclone

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharkthelarkclone.screen.CalendarScreen
import com.example.sharkthelarkclone.screen.ChatScreen
import com.example.sharkthelarkclone.screen.HomeScreen
import com.example.sharkthelarkclone.screen.WorkSpaceScreen
import com.example.sharkthelarkclone.ui.theme.SharkTheLarkCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharkTheLarkCloneTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        CustomBottomNavigationBar(navController)
                    },
                    contentWindowInsets = WindowInsets(0, 0, 0, 0)
                ) { innerPadding ->
                    Navigation(
                        navController = navController, innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("HomeScreen") { HomeScreen(navController) }
        composable("chat/{chatName}") { backStackEntry ->
            ChatScreen(backStackEntry.arguments?.getString("chatName") ?: "")
        }
        composable("CalendarScreen") { CalendarScreen(navController) }
        composable("WorkSpaceScreen") { WorkSpaceScreen(navController) }
    }
}

@Composable
fun CustomBottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem("HomeScreen", R.drawable.home_24px, "Home"),
        NavigationItem("CalendarScreen", R.drawable.calendar_month_24px, "Calendar"),
        NavigationItem("WorkSpaceScreen", R.drawable.workspaces_24px, "WorkSpace")
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = Color(0xFFdd855f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    icon = {
                        Box(modifier = Modifier
                                .drawBehind {
                                    if (selectedIndex == index) {
                                        drawRoundRect(
                                            color = Color(0x33FFFFFF),
                                            cornerRadius = CornerRadius(8.dp.toPx()),
                                            size = size
                                        )
                                    }
                                }
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(24.dp)
                            )
                        }
                    },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(item.route)
                    },
                    label = {
                        Text(
                            text = item.label,
                            color = Color(0xFF23321d),
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
            }
        }
    }
}

data class NavigationItem(
    val route: String,
    val icon: Int,
    val label: String

)

@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    SharkTheLarkCloneTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { CustomBottomNavigationBar(navController) }
        ) { innerPadding ->
            Navigation(navController = navController, innerPadding = innerPadding)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenPreview()
}