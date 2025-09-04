package irk.staryo.androidmess.model

import androidx.compose.runtime.Composable

class SegmentedComposeOption(
val label : String,
val layout : @Composable () -> Unit
)