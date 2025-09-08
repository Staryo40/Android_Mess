package irk.staryo.androidmess.ui.practice

import android.R.attr.direction
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.TurnLeft
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import irk.staryo.androidmess.enums.Direction
import irk.staryo.androidmess.model.JapaneseButtonContainer
import irk.staryo.androidmess.R

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
        Spacer(modifier = Modifier.height(16.dp))
        JapaneseKeyboard(addCharacter, deleteCharacter)
    }
}

@Composable
fun CharacterInputBox(
    characters: String,
    containerModifier: Modifier = Modifier
) {
    Column (modifier = containerModifier){
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = characters,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@Composable
fun JapaneseKeyboard(addCharacter : (String) -> Unit, deleteCharacter: () -> Unit){
    var chiisaiChar by rememberSaveable { mutableStateOf(false) }
    var chiisaiCharChanger : (String) -> Unit = {text -> chiisaiChar = !chiisaiChar}
    val kanaIcon: Painter = painterResource(id = R.drawable.language_japanese_kana)

    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.TurnLeft,
                    contentDescription = "Turn Left",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable {  },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowLeft,
                    contentDescription = "Arrow Left",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable {  },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.SentimentVerySatisfied,
                    contentDescription = "Arrow Left",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = kanaIcon,
                    contentDescription = "Convert language",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Column (
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["a"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["a"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ta"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ta"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ma"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ma"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["chiisai"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["chiisai"]!!.directionFunction(direction, chiisaiCharChanger)
                }
            )

        }
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ka"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ka"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["na"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["na"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ya"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ya"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["wa"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["wa"]!!.directionFunction(direction, addCharacter)
                }
            )
        }
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["sa"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["sa"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ha"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ha"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["ra"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["ra"]!!.directionFunction(direction, addCharacter)
                }
            )
            JapaneseCharacterButton(
                directionComposable = japaneseButtons["coma"]!!.directionalCompose,
                onDirectionDetected = { direction ->
                    japaneseButtons["coma"]!!.directionFunction(direction, addCharacter)
                }
            )
        }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .size(width = 120.dp, height = 60.dp)
                    .clickable { deleteCharacter() },  // call your function on click
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Backspace, // Material delete/backspace icon
                    contentDescription = "Delete character",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable {  },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowRight,
                    contentDescription = "Arrow Right",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable { addCharacter("　") },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.SpaceBar,
                    contentDescription = "Space",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Box(modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
                .clickable { addCharacter("　") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardReturn,
                    contentDescription = "Enter",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
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
            .size(width = 120.dp, height = 60.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable {
                onDirectionDetected(Direction.CENTER)
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        currentDirection = null
                    }
                ) { change, dragAmount ->
                    val (dx, dy) = dragAmount
                    val threshold = 20f

                    val newDirection = when {
                        dy < -threshold -> Direction.UP
                        dy > threshold -> Direction.DOWN
                        dx < -threshold -> Direction.LEFT
                        dx > threshold -> Direction.RIGHT
                        else -> null
                    }

                    if (newDirection != null && newDirection != currentDirection) {
                        currentDirection = newDirection
                        onDirectionDetected(newDirection)
                        Log.d("DragDetect", "Dragged $newDirection")
                    }
                }
            }
    ) {
        // Center
        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center),
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

@Composable
fun JapaneseSmallText(text: String){
    Text(text=text, fontSize = 12.sp)
}

@Composable
fun JapaneseNormalText(text: String){
    Text(text=text, fontSize = 16.sp)
}

val japaneseButtons = mutableMapOf<String, JapaneseButtonContainer>().apply {
    this["a"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("あ")
                Direction.UP -> JapaneseSmallText("う")
                Direction.DOWN -> JapaneseSmallText("お")
                Direction.RIGHT -> JapaneseSmallText("え")
                Direction.LEFT -> JapaneseSmallText("い")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("あ")
                Direction.UP -> func("う")
                Direction.DOWN -> func("お")
                Direction.RIGHT -> func("え")
                Direction.LEFT -> func("い")
                else -> {}
            }
        }
    )

    this["ta"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("た")
                Direction.UP -> JapaneseSmallText("つ")
                Direction.DOWN -> JapaneseSmallText("と")
                Direction.RIGHT -> JapaneseSmallText("て")
                Direction.LEFT -> JapaneseSmallText("ち")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("た")
                Direction.UP -> func("つ")
                Direction.DOWN -> func("と")
                Direction.RIGHT -> func("て")
                Direction.LEFT -> func("ち")
                else -> {}
            }
        }
    )

    this["ma"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("ま")
                Direction.UP -> JapaneseSmallText("む")
                Direction.DOWN -> JapaneseSmallText("も")
                Direction.RIGHT -> JapaneseSmallText("め")
                Direction.LEFT -> JapaneseSmallText("み")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("ま")
                Direction.UP -> func("む")
                Direction.DOWN -> func("も")
                Direction.RIGHT -> func("め")
                Direction.LEFT -> func("み")
                else -> {}
            }
        }
    )

    this["ka"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("か")
                Direction.UP -> JapaneseSmallText("く")
                Direction.DOWN -> JapaneseSmallText("こ")
                Direction.RIGHT -> JapaneseSmallText("け")
                Direction.LEFT -> JapaneseSmallText("き")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("か")
                Direction.UP -> func("く")
                Direction.DOWN -> func("こ")
                Direction.RIGHT -> func("け")
                Direction.LEFT -> func("き")
                else -> {}
            }
        }
    )

    this["na"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("な")
                Direction.UP -> JapaneseSmallText("ぬ")
                Direction.DOWN -> JapaneseSmallText("の")
                Direction.RIGHT -> JapaneseSmallText("ね")
                Direction.LEFT -> JapaneseSmallText("に")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("な")
                Direction.UP -> func("ぬ")
                Direction.DOWN -> func("の")
                Direction.RIGHT -> func("ね")
                Direction.LEFT -> func("に")
                else -> {}
            }
        }
    )

    this["ya"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("や")
                Direction.DOWN -> JapaneseSmallText("よ")
                Direction.UP -> JapaneseSmallText("ゆ")
                Direction.RIGHT -> JapaneseSmallText("）")
                Direction.LEFT -> JapaneseSmallText("（")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("や")
                Direction.DOWN -> func("よ")
                Direction.UP -> func("ゆ")
                Direction.RIGHT -> func("）")
                Direction.LEFT -> func("（")
                else -> {}
            }
        }
    )

    this["sa"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("さ")
                Direction.UP -> JapaneseSmallText("す")
                Direction.DOWN -> JapaneseSmallText("そ")
                Direction.RIGHT -> JapaneseSmallText("せ")
                Direction.LEFT -> JapaneseSmallText("し")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("さ")
                Direction.UP -> func("す")
                Direction.DOWN -> func("そ")
                Direction.RIGHT -> func("せ")
                Direction.LEFT -> func("し")
                else -> {}
            }
        }
    )

    this["ha"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("は")
                Direction.UP -> JapaneseSmallText("ふ")
                Direction.DOWN -> JapaneseSmallText("ほ")
                Direction.RIGHT -> JapaneseSmallText("へ")
                Direction.LEFT -> JapaneseSmallText("ひ")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("は")
                Direction.UP -> func("ふ")
                Direction.DOWN -> func("ほ")
                Direction.RIGHT -> func("へ")
                Direction.LEFT -> func("ひ")
                else -> {}
            }
        }
    )

    this["ra"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("ら")
                Direction.UP -> JapaneseSmallText("る")
                Direction.DOWN -> JapaneseSmallText("ろ")
                Direction.RIGHT -> JapaneseSmallText("れ")
                Direction.LEFT -> JapaneseSmallText("り")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("ら")
                Direction.UP -> func("る")
                Direction.DOWN -> func("ろ")
                Direction.RIGHT -> func("れ")
                Direction.LEFT -> func("り")
                else -> {}
            }
        }
    )

    this["wa"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("わ")
                Direction.UP -> JapaneseSmallText("を")
                Direction.DOWN -> JapaneseSmallText("～")
                Direction.RIGHT -> JapaneseSmallText("―")
                Direction.LEFT -> JapaneseSmallText("ん")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("わ")
                Direction.UP -> func("を")
                Direction.DOWN -> func("～")
                Direction.RIGHT -> func("―")
                Direction.LEFT -> func("ん")
                else -> {}
            }
        }
    )

    this["chiisai"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("小")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("")
                else -> {}
            }
        }
    )

    this["coma"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> JapaneseNormalText("、")
                Direction.UP -> JapaneseSmallText("？")
                Direction.DOWN -> JapaneseSmallText("…")
                Direction.RIGHT -> JapaneseSmallText("！")
                Direction.LEFT -> JapaneseSmallText("。")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("、")
                Direction.UP -> func("？")
                Direction.DOWN -> func("…")
                Direction.RIGHT -> func("！")
                Direction.LEFT -> func("。")
                else -> {}
            }
        }
    )
}
