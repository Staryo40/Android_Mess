package irk.staryo.androidmess.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainContent(modifier: Modifier = Modifier, child: @Composable () -> Unit){
    var currentChild by remember {
        mutableStateOf<@Composable () -> Unit>(child)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        currentChild()
//        child()
    }
}