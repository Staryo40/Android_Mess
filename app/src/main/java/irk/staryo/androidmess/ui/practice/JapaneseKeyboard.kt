package irk.staryo.androidmess.ui.practice

import android.R.attr.direction
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import irk.staryo.androidmess.enums.Direction
import irk.staryo.androidmess.model.JapaneseButtonContainer

@Composable
fun JapaneseInputLayout(){
    var currentCharacters by rememberSaveable { mutableStateOf<String>("") }
    val addCharacter: (String) -> Unit = { text -> currentCharacters += text }
    val deleteCharacter: () -> Unit = {
        if (currentCharacters.isNotEmpty()) {
        currentCharacters = currentCharacters.dropLast(1)
    } }

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(12.dp))
    {
        CharacterInputBox(currentCharacters, containerModifier = Modifier.weight(1f))
        JapaneseKeyboard(addCharacter, deleteCharacter)
    }
}

@Composable
fun CharacterInputBox(
    characters: String,
    containerModifier: Modifier = Modifier
) {
    var currentCharacters by remember { mutableStateOf<String>(characters) }

    Column (modifier = containerModifier){
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = currentCharacters,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@Composable
fun JapaneseKeyboard(addCharacter : (String) -> Unit, deleteCharacter: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
        }
        Column (
            modifier = Modifier.weight(3f).border(2.dp, Color.Black).background(Color.White, RoundedCornerShape(8.dp))
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(5), // 5 items per row (adjust as needed)
                modifier = Modifier.fillMaxSize()
            ) {
                items(japaneseButtons.size) { index ->
                    val button = japaneseButtons[index]
                    JapaneseCharacterButton(
                        directionComposable = button.directionalCompose,
                        onDirectionDetected = { direction ->
                            button.directionFunction(direction, addCharacter)
                        }
                    )
                }
            }
        }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 60.dp, height = 30.dp)
            )
        }
    }
}

@Composable
fun JapaneseCharacterButton(
    directionComposable: @Composable (Direction) -> Unit,
    onDirectionDetected: (Direction) -> Unit
){
    var currentDirection by remember { mutableStateOf<Direction?>(null) }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
//                        onDirectionDetected(null)
//                        currentDirection = null
                    }
                ) { change, dragAmount ->
                    val (dx, dy) = dragAmount
                    val threshold = 20f

                    val newDirection = when {
                        dy < -threshold -> Direction.UP
                        dy > threshold -> Direction.DOWN
                        dx < -threshold -> Direction.LEFT
                        dx > threshold -> Direction.RIGHT
                        else -> Direction.CENTER
                    }

                    if (newDirection != currentDirection) {
                        currentDirection = newDirection
                        onDirectionDetected(newDirection)
                        Log.d("DragDetect", "Dragged $newDirection")
                    }
                }
            }
    ) {
        // Center
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) { directionComposable(Direction.CENTER) }

        // Up
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 4.dp),
            contentAlignment = Alignment.Center
        ) { directionComposable(Direction.UP) }

        // Down
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) { directionComposable(Direction.DOWN) }

        // Left
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 4.dp),
            contentAlignment = Alignment.Center
        ) { directionComposable(Direction.LEFT) }

        // Right
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 4.dp),
            contentAlignment = Alignment.Center
        ) { directionComposable(Direction.RIGHT) }
    }
}

val japaneseButtons = listOf(
    JapaneseButtonContainer(
        directionalCompose = {direction ->
            when(direction){
                Direction.CENTER -> Text("あ")
                Direction.UP -> Text("う")
                Direction.DOWN -> Text("お")
                Direction.RIGHT -> Text("え")
                Direction.LEFT -> Text("い")
                else -> {}
            }
        },
        directionFunction = {direction, func ->
            when(direction){
                Direction.CENTER -> func("あ")
                Direction.UP -> func("う")
                Direction.DOWN -> func("お")
                Direction.RIGHT -> func("え")
                Direction.LEFT -> func("い")
                else -> {}
            }
        }
    )
)