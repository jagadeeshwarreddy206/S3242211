package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.univid.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val searchItem = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Row {
                Text(text = "Home Screen")
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 18.dp)
                    .size(30.dp)
                    )
            } })
        }
    ) {
        Column(Modifier.padding(it)) {
            Text(
                text = "Find the \nBest Properties", fontFamily = poppins, fontSize = 25.sp,
                modifier = Modifier.padding(12.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)) {
                OutlinedTextField(
                    value = searchItem.value,
                    onValueChange = { searchItem.value = it },
                    label = {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")
                    })
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Default.Search, contentDescription = "search",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(50.dp)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(Color(0xFF9BB86A)))
            }
        }
    }
}