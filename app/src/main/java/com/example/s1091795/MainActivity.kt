package com.example.s1091795

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.s1091795.ui.theme.S1091795Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            S1091795Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                     */
                    Main()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    var showMenu by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "圖片",
                    alpha = 0.7f,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black)
                )
            }

        )


        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
    IconButton(
        onClick = { showMenu = true }
    ) {
        Icon(Icons.Default.MoreVert, contentDescription = "More")
    }

    DropdownMenu(
        expanded = showMenu, onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text("畫面1") },
            onClick = { navController.navigate("JumpFirst")})

        DropdownMenuItem(
            text = { Text("畫面2") },
            onClick = { navController.navigate("JumpSecond")})
    }


}


@Composable
fun FirstScreen(navController: NavController){
    Animation()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "簡介",
            fontFamily = FontFamily(Font(R.font.kai)),
            fontSize = 25.sp,
            color = Color.Blue
        )
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()){
        Text(
            text = "主要機構",
            fontFamily = FontFamily(Font(R.font.kai)),
            fontSize = 25.sp,
            color = Color.Red
        )

    }
}

@Composable
fun Animation() {
    var showFirstImage by remember { mutableStateOf(true) } // 第一张图片显示状态
    var showSecondImage by remember { mutableStateOf(false) } // 第二张图片显示状态

    Column {
        Text(
            text = if (showFirstImage) "關於App作者\n" else "瑪利亞基金會服務總覽\n",
            modifier = Modifier.fillMaxWidth()
        )
        // 第一张图片
        AnimatedVisibility(
            visible = showFirstImage,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 3000)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 3000)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = "作者"
            )
        }

        // 第二张图片
        AnimatedVisibility(
            visible = showSecondImage,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 3000)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 3000)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.service), // 替换成你的第二张图片资源
                contentDescription = "第二张图片"
            )
        }

        // 按钮切换图片显示状态
        Button(
            onClick = {
                showFirstImage = !showFirstImage
                showSecondImage = !showSecondImage
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (showFirstImage) Text(text = "服務總覽")
            else Text(text = "作者:資管四B周立哲")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S1091795Theme {
        Greeting("Android")
    }
}