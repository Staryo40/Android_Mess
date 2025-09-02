package irk.staryo.androidmess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import irk.staryo.androidmess.model.SegmentedOption
import irk.staryo.androidmess.ui.common.BottomBar
import irk.staryo.androidmess.ui.common.TopBar
import irk.staryo.androidmess.ui.theme.AndroidMessTheme

class MainActivity : ComponentActivity() { // Compose version of activity
    val bottomOptions = listOf(
        SegmentedOption("Home", {}),
        SegmentedOption("Option2", {}),
        SegmentedOption("Option3", {})
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // Equivalent to setContentView(R.layout.activity_main)
            AndroidMessTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(bottomOptions)
                    },
                    topBar = {
                        TopBar()
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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