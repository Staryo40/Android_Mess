package irk.staryo.androidmess.ui.practice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import irk.staryo.androidmess.model.MataKuliahItem
import irk.staryo.androidmess.model.SegmentedComposeOption

@Composable
fun TwoOptionList(
    option1: SegmentedComposeOption,
    option2: SegmentedComposeOption,
    rowModifier : Modifier = Modifier,
    defaultModifier : Modifier = Modifier,
    selectedModifier: Modifier = Modifier
) {
    Column {
        var selectedIndex by remember { mutableIntStateOf(0) }

        SingleChoiceSegmentedButtonRow(modifier = rowModifier) {
            SegmentedButton(
                modifier = if (selectedIndex == 0) selectedModifier else defaultModifier,
                shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                onClick = { selectedIndex = 0 },
                selected = selectedIndex == 0,
                label = { option1.label }
            )
            SegmentedButton(
                modifier = if (selectedIndex == 1) selectedModifier else defaultModifier,
                shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                onClick = { selectedIndex = 1 },
                selected = selectedIndex == 1,
                label = { option1.label }
            )
        }

        if (selectedIndex == 0){
            option1.layout()
        } else {
            option2.layout()
        }
    }
}

@Composable
fun MataKuliahLayout(materialList : List<MataKuliahItem>){
    Column (modifier = Modifier.fillMaxHeight()){
        var searchQuery by remember { mutableStateOf("") }

        Text("Reference: xxx.com")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { newValue -> searchQuery = newValue },
                placeholder = { Text("Search...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
            )
            IconButton(
                onClick = { Log.d("UI", "Filter clicked!") },
                modifier = Modifier
                    .size(56.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = Color.White
                )
            }
        }
        LazyColumn (
            Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(16.dp)
        ) {
            itemsIndexed(materialList) { index, material ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { material.selectFun() }
                    .padding(16.dp)
                ) {
                    Column (modifier = Modifier.weight(1f)){
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = "Subject:",
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(end = 8.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = material.subject)
                        }
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = "Material:",
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(end = 8.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = material.materialTitle)
                        }
                    }
                    IconButton(
                        onClick = {
                            Log.d("Download", "${material.materialTitle} has been saved!")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download"
                        )
                    }
                }
            }
        }
    }
}