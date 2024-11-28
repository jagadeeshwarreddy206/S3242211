package uk.ac.tees.mad.univid.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uk.ac.tees.mad.univid.MainViewModel

@Composable
fun DetailScreen(id: String, vm: MainViewModel) {
    val property = vm.getPropertyById(id)
    Text(text = property.toString())

}