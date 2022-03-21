package com.icm.layoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icm.layoutcompose.ui.theme.LayoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}
@Composable
fun MyScreenContent(names: List<String> = List(1000){ "Hola $it "}){
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
fun NameList(names: List<String>, modifer: Modifier = Modifier){

    LazyColumn(modifier = modifer){
        items(items = names) {name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}
@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit){

    Button(onClick = { updateCount(count+1)}){
        Text("Has clicado $count veces")
    }
}
@Composable
fun MyApp(content: @Composable ()-> Unit) {
    LayoutComposeTheme {
        Surface(color = Color.Yellow){
        content()

        }
    }
}
@Composable
fun Greeting(name: String) {

      Text(
          text = "Hola que tal $name!", modifier = Modifier
        .padding(12.dp))

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}