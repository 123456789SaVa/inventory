package com.Sava.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}


@Composable
fun GameScreen() {
    var score by remember { mutableStateOf(0) }
    var buttonPosition by remember { mutableStateOf(IntOffset(0, 0)) }
    var isGameOver by remember { mutableStateOf(false) }

    // Функция для сброса игры
    fun resetGame() {
        score = 0
        isGameOver = false
        buttonPosition = IntOffset(
            Random.nextInt(0, 800), // Ширина экрана (в пикселях)
            Random.nextInt(0, 1600) // Высота экрана (в пикселях)
        )
    }

    // Логика передвижения кнопки и завершения игры
    LaunchedEffect(isGameOver) {
        if (!isGameOver) {
            while (true) {
                delay(1000L)  // Задержка на 1 секунду перед передвижением кнопки
                buttonPosition = IntOffset(
                    Random.nextInt(0, 800),
                    Random.nextInt(0, 1600)
                )
                if (score == 0) {
                    isGameOver = true
                    break
                } else {
                    score = 0
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        if (isGameOver) {
            // Экран проигрыша
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Вы проиграли",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 32.sp  // Увеличение размера шрифта в 2 раза
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { resetGame() }) {
                    Text(text = "Начать заново")
                }
            }
        }
        else {
            // Игра продолжается
            Button(
                onClick = {
                    score += 1
                },
                modifier = Modifier
                    .offset { buttonPosition }
                    .size(100.dp)
            ) {
                Text("Нажми меня!")
            }

            // Отображаем счёт
            Text(
                text = "Счёт: $score",
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Black
            )
        }
    }
}
