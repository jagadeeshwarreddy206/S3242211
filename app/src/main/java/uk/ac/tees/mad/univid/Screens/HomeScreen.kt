package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uk.ac.tees.mad.univid.AppNavigations
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.Models.Api.Data
import uk.ac.tees.mad.univid.Models.Api.Location
import uk.ac.tees.mad.univid.Models.Api.Price
import uk.ac.tees.mad.univid.Models.Api.ProductLabel
import uk.ac.tees.mad.univid.Models.Api.PropertyImages
import uk.ac.tees.mad.univid.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: MainViewModel, navController: NavController) {

    val properties = vm.response.collectAsState()
    val searchedItem = remember {
        mutableStateOf<List<Data>>(emptyList())
    }
    val searchItem = remember {
        mutableStateOf("")
    }
    val screenState = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Text(text = "Home")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 18.dp)
                            .size(30.dp)
                            .clickable { navController.navigate(AppNavigations.ProfileScreen.route) }
                    )
                }
            })
        }
    ) {
        Column(Modifier.padding(it)) {
            Text(
                text = "Find the \nBest Properties",
                fontFamily = poppins,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                OutlinedTextField(
                    value = searchItem.value,
                    onValueChange = { searchItem.value = it },
                    label = {
                        Icon(
                            imageVector = Icons.Rounded.Search, contentDescription = "search",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "search",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(50.dp)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(Color(0xFF9BB86A))
                        .clickable {
                            searchedItem.value = vm.searchProperty(searchItem.value)
                            screenState.value = true
                        }
                )
            }
            if (screenState.value){
                Column(Modifier.padding(horizontal = 12.dp)) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Button(onClick = { screenState.value = false }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "Search Results",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(searchedItem.value) { items ->
                            propertyView(
                                imageUrl = items.propertyImages,
                                price = items.price,
                                propertyLabel = items.productLabel,
                                description = items.propertyTypeFullDescription,
                                onNavigate = {
                                    navController.navigate(AppNavigations.DetailScreen.createRoute(items.id))
                                }
                            )
                    }
                }
                }
            }
            else {
                Column(Modifier.padding(horizontal = 12.dp)) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "Popular Properties",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    if (properties.value != null) {
                        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                            items(properties.value!!.data) { items ->
                                propertyView(
                                    imageUrl = items.propertyImages,
                                    price = items.price,
                                    propertyLabel = items.productLabel,
                                    description = items.propertyTypeFullDescription,
                                    onNavigate = {
                                        navController.navigate(AppNavigations.DetailScreen.createRoute(items.id))
                                    }
                                )
                            }
                        }
                    } else {
                        Text(text = "Loading...")
                    }

                }
            }
        }
    }
}

@Composable
fun propertyView(
    imageUrl: PropertyImages?,
    price: Price?,
    propertyLabel: ProductLabel?,
    description : String,
    onNavigate:() -> Unit
) {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable {
                    onNavigate()
                }
        ) {
            AsyncImage(
                model = imageUrl?.mainImageSrc ?: "",
                contentDescription = "imageUrl",
                modifier = Modifier.clip(
                    RoundedCornerShape(10.dp)
                )
            )
            Text(text = description)
            Text(text = "$${price?.amount?.toString() ?: "N/A"}")
            Text(text = propertyLabel?.productLabelText ?: "N/A")
        }
    }
}