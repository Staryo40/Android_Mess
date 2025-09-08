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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
        Spacer(modifier = Modifier.height(16.dp))
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
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
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
                    japaneseButtons["chiisai"]!!.directionFunction(direction, addCharacter)
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
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
            )
            Box(modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .size(width = 120.dp, height = 60.dp)
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
            .size(width = 120.dp, height = 60.dp)
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

val japaneseButtons = mutableMapOf<String, JapaneseButtonContainer>().apply {
    this["a"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> Text("あ")
                Direction.UP -> Text("う")
                Direction.DOWN -> Text("お")
                Direction.RIGHT -> Text("え")
                Direction.LEFT -> Text("い")
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
                Direction.CENTER -> Text("た")
                Direction.UP -> Text("つ")
                Direction.DOWN -> Text("と")
                Direction.RIGHT -> Text("て")
                Direction.LEFT -> Text("ち")
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
                Direction.CENTER -> Text("ま")
                Direction.UP -> Text("む")
                Direction.DOWN -> Text("も")
                Direction.RIGHT -> Text("め")
                Direction.LEFT -> Text("み")
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
                Direction.CENTER -> Text("か")
                Direction.UP -> Text("く")
                Direction.DOWN -> Text("こ")
                Direction.RIGHT -> Text("け")
                Direction.LEFT -> Text("き")
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
                Direction.CENTER -> Text("な")
                Direction.UP -> Text("ぬ")
                Direction.DOWN -> Text("の")
                Direction.RIGHT -> Text("ね")
                Direction.LEFT -> Text("に")
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
                Direction.CENTER -> Text("や")
                Direction.DOWN -> Text("よ")
                Direction.LEFT -> Text("ゆ")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("や")
                Direction.DOWN -> func("よ")
                Direction.LEFT -> func("ゆ")
                else -> {}
            }
        }
    )

    this["sa"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> Text("さ")
                Direction.UP -> Text("す")
                Direction.DOWN -> Text("そ")
                Direction.RIGHT -> Text("せ")
                Direction.LEFT -> Text("し")
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
                Direction.CENTER -> Text("は")
                Direction.UP -> Text("ふ")
                Direction.DOWN -> Text("ほ")
                Direction.RIGHT -> Text("へ")
                Direction.LEFT -> Text("ひ")
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
                Direction.CENTER -> Text("ら")
                Direction.UP -> Text("る")
                Direction.DOWN -> Text("ろ")
                Direction.RIGHT -> Text("れ")
                Direction.LEFT -> Text("り")
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
                Direction.CENTER -> Text("わ")
                Direction.DOWN -> Text("を")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("わ")
                Direction.DOWN -> func("を")
                else -> {}
            }
        }
    )

    this["chiisai"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> Text("っ")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("っ")
                else -> {}
            }
        }
    )

    this["coma"] = JapaneseButtonContainer(
        directionalCompose = { direction ->
            when (direction) {
                Direction.CENTER -> Text("、")
                else -> {}
            }
        },
        directionFunction = { direction, func ->
            when (direction) {
                Direction.CENTER -> func("、")
                else -> {}
            }
        }
    )
}
