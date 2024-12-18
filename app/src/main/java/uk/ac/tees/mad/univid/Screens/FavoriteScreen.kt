package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import uk.ac.tees.mad.univid.Data.PropertyData
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(vm: MainViewModel, navController: NavHostController) {
    vm.getProperties()
    val propertyData = vm.propertyData.observeAsState(emptyList())
    Scaffold(topBar = { TopAppBar(title = { Row(modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(25.dp)
                .clickable {
                    navController.popBackStack()
                })
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "Favorite Properties", modifier = Modifier.align(Alignment.CenterVertically))
        
    }
    }) }) {
    Column(Modifier.padding(it)) {
        LazyColumn {
            items(propertyData.value) { items ->
                favoriteItemView(favItem = items)
            }
        }
        }
    }
}

@Composable
fun favoriteItemView(favItem : PropertyData){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)){
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(model = favItem.propertyImages.mainImageSrc, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = favItem.propertyTypeFullDescription,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = favItem.summary,
                fontFamily = poppins,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Price: ${favItem.price.currencyCode} ${favItem.price.amount}",
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold)
        }
    }
}