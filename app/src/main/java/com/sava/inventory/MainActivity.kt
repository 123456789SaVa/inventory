package com.sava.inventory

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetronomeApp()
        }
    }
}

@Composable
fun MetronomeApp() {
    var isPlaying by remember { mutableStateOf(false) }
    var bpm by remember { mutableStateOf(60) } // Установим стандартный темп 60 BPM
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.sound) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Метроном",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { if (bpm > 40) bpm -= 5 }) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$bpm BPM",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { if (bpm < 300) bpm += 5 }) {
                Text(text = "+")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { isPlaying = !isPlaying }) {
            Text(text = if (isPlaying) "Стоп" else "Старт")
        }
        if (isPlaying) Metronome(bpm = bpm)
        Button(onClick = {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }) {
            Text("Play Sound")
        }
    }
}

@Composable
fun Metronome(bpm: Int) {
    val delayMillis = 60000 / bpm // Рассчитываем задержку в миллисекундах между ударами

    LaunchedEffect(bpm) {
        val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        try {
            while (true) {
                toneGenerator.startTone(
                    ToneGenerator.TONE_CDMA_PIP,
                    150
                ) // Воспроизводим звук на 150 мс
                delay(delayMillis.toLong()) // Задержка между ударами, соответствующая текущему BPM
            }
        } catch (e: Exception) {
            // Это ловит исключение, если пользователь остановит метроном
            // или происходит любая другая ошибка, что остановит цикл.
        } finally {
            toneGenerator.release() // Освобождаем ресурсы после завершения работы
        }
    }
}
