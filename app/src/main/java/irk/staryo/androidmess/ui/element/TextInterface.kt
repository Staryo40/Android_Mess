package irk.staryo.androidmess.ui.element

import android.R.attr.text
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlainText(text : String, modifier : Modifier){
    Text(
        text = text,
        modifier = modifier
    )
}