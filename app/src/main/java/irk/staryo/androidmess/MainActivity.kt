package irk.staryo.androidmess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import irk.staryo.androidmess.model.SegmentedOption
import irk.staryo.androidmess.ui.common.BottomBar
import irk.staryo.androidmess.ui.common.MainContent
import irk.staryo.androidmess.ui.common.TopBar
import irk.staryo.androidmess.ui.theme.AndroidMessTheme

class MainActivity : ComponentActivity() { // Compose version of activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // Equivalent to setContentView(R.layout.activity_main)
            AndroidMessTheme {
                var selected by rememberSaveable { mutableIntStateOf(0) }
                val bottomOptions = listOf(
                    SegmentedOption("Home", { selected = 0 }),
                    SegmentedOption("Option2", { selected = 1 }),
                    SegmentedOption("Option3", { selected = 2 })
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(innerPadding)
                    ){
                        TopBar()
                        MainContent(modifier = Modifier.weight(1f)) {
                            when (selected) {
                                0 -> Greeting("Home")
                                1 -> Greeting("Option2")
                                2 -> Greeting("Option3")
                            }
                        }
                        BottomBar(selected, bottomOptions)
                    }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMessTheme {
        Greeting("Android")
    }
}