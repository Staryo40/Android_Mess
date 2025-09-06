package irk.staryo.androidmess

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import irk.staryo.androidmess.model.MataKuliahItem
import irk.staryo.androidmess.model.SegmentedComposeOption
import irk.staryo.androidmess.ui.practice.MataKuliahLayout

class MainViewModel : ViewModel() {
    // Mata Kuliah attributes
    val list1 = listOf(
        MataKuliahItem("Math Algebraic awesomenss you wont believe it omg", "Algebra: whatever this is it must be goated since its named after some mathmetician, dont really know though") { Log.d("MataKuliah", "Algebra has been pressed!") },
        MataKuliahItem("Math", "Calculus") { Log.d("MataKuliah", "Calculus has been pressed!") },
        MataKuliahItem("Math", "Statistics") { Log.d("MataKuliah", "Statistics has been pressed!") },
        MataKuliahItem("Math", "Algebra") { Log.d("MataKuliah", "Algebra has been pressed!") },
        MataKuliahItem("Math", "Calculus") { Log.d("MataKuliah", "Calculus has been pressed!") },
        MataKuliahItem("Math", "Statistics") { Log.d("MataKuliah", "Statistics has been pressed!") },
        MataKuliahItem("Math", "Algebra") { Log.d("MataKuliah", "Algebra has been pressed!") },
        MataKuliahItem("Math", "Calculus") { Log.d("MataKuliah", "Calculus has been pressed!") },
        MataKuliahItem("Math", "Statistics") { Log.d("MataKuliah", "Statistics has been pressed!") }
    )

    val list2 = listOf(
        MataKuliahItem("Physics", "Mechanics") { Log.d("MataKuliah", "Mechanics has been pressed!") },
        MataKuliahItem("Physics", "Optics") { Log.d("MataKuliah", "Optics has been pressed!") },
        MataKuliahItem("Physics", "Thermodynamics") { Log.d("MataKuliah", "Thermodynamics has been pressed!") }
    )

    val option1 = SegmentedComposeOption(label = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = "Book",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("Mata Kuliah 1")
        }
    }) {
        MataKuliahLayout("matkul 1", list1)
    }

    val option2 = SegmentedComposeOption(label = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = "Book",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("Mata Kuliah 2")
        }
    }) {
        MataKuliahLayout("matkul 2", list2)
    }
}
