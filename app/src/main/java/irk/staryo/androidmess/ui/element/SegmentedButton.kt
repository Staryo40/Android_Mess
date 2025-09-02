package irk.staryo.androidmess.ui.element

import android.R.attr.label
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import irk.staryo.androidmess.model.SegmentedOption

// Default Single Choice Example
@Composable
fun SingleChoiceSegmentedButtonExample(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Day", "Month", "Week")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

// Custom Single Choice
@Composable
fun SingleChoiceSegmentedButtonCustom(options: List<SegmentedOption>, rowModifier: Modifier = Modifier, buttonModifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    SingleChoiceSegmentedButtonRow(modifier = rowModifier) {
        options.forEachIndexed { index, opt ->
            SegmentedButton(
                modifier = buttonModifier,
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selectedIndex = index
                    opt.action()
                          },
                selected = index == selectedIndex,
                label = { Text(opt.label) }
            )
        }
    }
}

// Default Multiple Choice Example
@Composable
fun MultiChoiceSegmentedButtonExample(modifier: Modifier = Modifier) {
    val selectedOptions = remember {
        mutableStateListOf(false, false, false)
    }
    val options = listOf("Walk", "Ride", "Drive")

    MultiChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                checked = selectedOptions[index],
                onCheckedChange = {
                    selectedOptions[index] = !selectedOptions[index]
                },
                icon = { SegmentedButtonDefaults.Icon(selectedOptions[index]) },
                label = {
                    when (label) {
                        "Walk" -> Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.DirectionsWalk,
                            contentDescription = "Directions Walk"
                        )
                        "Ride" -> Icon(
                            imageVector =
                                Icons.Default.DirectionsBus,
                            contentDescription = "Directions Bus"
                        )
                        "Drive" -> Icon(
                            imageVector =
                                Icons.Default.DirectionsCar,
                            contentDescription = "Directions Car"
                        )
                    }
                }
            )
        }
    }
}

// Custom Multiple Choice
@Composable
fun MultiChoiceSegmentedButtonCustom(options : List<SegmentedOption>, rowModifier: Modifier = Modifier, buttonModifier : Modifier = Modifier) {
    val selectedOptions = remember {
        mutableStateListOf(*List(options.size) { false }.toTypedArray())
    }

    MultiChoiceSegmentedButtonRow(rowModifier) {
        options.forEachIndexed { index, opt ->
            SegmentedButton(
                modifier = buttonModifier,
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                checked = selectedOptions[index],
                onCheckedChange = {
                    selectedOptions[index] = !selectedOptions[index]
                    if (selectedOptions[index]){
                        opt.action()
                    }
                },
                icon = { SegmentedButtonDefaults.Icon(selectedOptions[index]) },
                label = { Text(opt.label) }
            )
        }
    }
}