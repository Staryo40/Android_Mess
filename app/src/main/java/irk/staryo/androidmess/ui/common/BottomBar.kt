package irk.staryo.androidmess.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import irk.staryo.androidmess.model.SegmentedOption

@Composable
fun BottomBar(current : Int, options : List<SegmentedOption>){
    var selected by rememberSaveable { mutableIntStateOf(0) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
        horizontalArrangement = Arrangement.SpaceEvenly, // space items evenly
        verticalAlignment = Alignment.CenterVertically
    ){
        options.forEachIndexed{ id, opt ->
            Text(
                text = opt.label,
                color = if (id == selected) Color(0xFF00008B) else Color.Gray,
                modifier = Modifier
                    .background(
                        if (id == selected) Color(0xFFADD8E6) else Color.White
                    )
                    .clickable {
                        selected = id
                        opt.action()
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}