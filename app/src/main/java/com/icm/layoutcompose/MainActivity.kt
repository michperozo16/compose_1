package com.icm.layoutcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icm.layoutcompose.ui.theme.LayoutComposeTheme
import java.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent(
                    names = listOf("Pepe", "Juana", "Ricardo"),
                    ages = listOf(18, 25, 32)
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    val context = LocalContext.current

    LayoutComposeTheme {
        Surface(color = Color.White) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("App de Miguel") },
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        Text("Barra inferior", modifier = Modifier.padding(start = 32.dp))
                        Spacer(Modifier.weight(1f, true))
                        IconButton(onClick = { Toast.makeText(context, "Corazón pulsado", Toast.LENGTH_SHORT).show() }) {
                            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { Toast.makeText(context, "Estrella pulsada", Toast.LENGTH_SHORT).show() }) {
                            Icon(Icons.Filled.Star, contentDescription = "Localized description")
                        }
                    }
                }
            ) {
                content()
            }


        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hola $it " }, ages: List<Int>) {
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = {
                counterState.value = it
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(onClick = { updateCount(count + 1) }) {
        Text("Has clicado $count veces")
    }
}

@Composable
fun Greeting(name: String) {

    Text(
        text = "Hola que tal $name!", modifier = Modifier
            .padding(12.dp)
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent(
            names = listOf("Pepe", "Juana", "Ricardo"),
            ages = listOf(18, 25, 32)
        )
    }
}