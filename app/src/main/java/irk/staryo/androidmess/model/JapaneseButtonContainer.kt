package irk.staryo.androidmess.model

import androidx.compose.runtime.Composable
import irk.staryo.androidmess.enums.Direction

class JapaneseButtonContainer(
    val directionalCompose: @Composable (Direction) -> Unit,
    val directionFunction: (Direction, (String) -> Unit) -> Unit
)