package irk.staryo.androidmess.model

import androidx.compose.runtime.Composable

class SegmentedComposeOption(
    val label : @Composable () -> Unit,
    val layout : @Composable () -> Unit
)