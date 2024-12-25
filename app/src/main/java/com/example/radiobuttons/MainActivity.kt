package com.example.radiobuttons

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.radiobuttons.ui.theme.RadioButtonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButtonsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RadioButtonColors(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RadioButtonColors(name: String, modifier: Modifier = Modifier) {
    val myBackgroundColor = remember {
        mutableStateOf(Color.White)
    }

    val selectedIndex = remember {
        mutableIntStateOf(0)
    }

    val buttonTexts = listOf("Red", "Green", "Yellow", "Gray")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myBackgroundColor.value).padding(top = 30.dp),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        buttonTexts.forEachIndexed { index, s ->
            Row(
                modifier = Modifier
                    .width(100.dp).clickable {
                        selectedIndex.value = index
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedIndex.value == index,
                    onClick = {
                        selectedIndex.value = index
                    },
                    colors = RadioButtonDefaults.colors(Color.Blue)
                )
                Text(text = s)
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            shape = RoundedCornerShape(5.dp),
            onClick = {
                when (selectedIndex.value) {
                    0 -> myBackgroundColor.value = Color.Red
                    1 -> myBackgroundColor.value = Color.Green
                    2 -> myBackgroundColor.value = Color.Yellow
                    3 -> myBackgroundColor.value = Color.Gray
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF8850EC))
        ) {
            Text(
                text = "Change Background Color",
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RadioButtonsTheme {
        RadioButtonColors("Android")
    }
}