package com.example.twellthelarkclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twellthelarkclone.ui.theme.TwellTheLarkCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwellTheLarkCloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetStartedScreen(
                        onSkipClick = {
                            // Action to perform when Skip is clicked
                        },
                        onGetStartedClick = {
                            // Action to perform when Get Started is clicked
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GetStartedScreen(onSkipClick: () -> Unit, onGetStartedClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffdfebff))
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_screeen_background),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 115.dp)
                .alpha(0.7f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(8.dp)
                ) {
                    Circle(
                        color = Color(0xff241e2f), // Filled dot
                        radius = 8.dp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Circle(
                        color = Color(0xff474747), // Unfilled dot
                        radius = 8.dp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Circle(
                        color = Color(0xff474747), // Unfilled dot
                        radius = 8.dp
                    )
                }
                TextButton(
                    onClick = onSkipClick,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .offset(x = 16.dp)
                        .size(width = 100.dp, height = 40.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xff241e2f)
                    )
                ) {
                    Text("Skip >>")
                }
            }

            Text(
                text = "TWELL",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 65.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color(0xff241e2f)
                ),
                textAlign = TextAlign.Center
            )

            Text(
                "All-in-One Workspace for Seamless Collaboration",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    color = Color(0xff241e2f)
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff241e2f))
            ) {
                Text("Get Started", color = Color.White)
            }

            val agreementText =
                "By signing up you confirm you have read the agreement to Twell Privacy Policy, Cookies and Terms."
            val underlinedWords = listOf("Privacy Policy", "Cookies", "Terms")

            Text(
                text = buildAnnotatedString {
                    var remainingText = agreementText
                    underlinedWords.forEach { word ->
                        val startIndex = remainingText.indexOf(word)
                        if (startIndex != -1) {
                            val endIndex = startIndex + word.length
                            append(remainingText.substring(0, startIndex))
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff241e2f)
                                )
                            ) {
                                append(word)
                            }
                            remainingText = remainingText.substring(endIndex)
                        }
                    }
                    append(remainingText)
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xff241e2f)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun Circle(color: Color, radius: Dp) {
    Box(
        modifier = Modifier
            .size(radius * 1)
            .background(color, CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun GetStartedScreenPreview() {
    TwellTheLarkCloneTheme {
        GetStartedScreen(
            onSkipClick = { /*TODO*/ },
            onGetStartedClick = { /*TODO*/ }
        )
    }
}