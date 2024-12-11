package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.univid.MainViewModel

@Composable
fun FavoriteScreen(vm: MainViewModel, navController: NavHostController) {
    vm.getProperties()
    val propertyData = vm.propertyData.observeAsState(emptyList())
    Column(Modifier.padding(12.dp)) {
        LazyColumn {
            items(propertyData.value){ items->
                Text(text = items.summary)
            }
        }
    }
}