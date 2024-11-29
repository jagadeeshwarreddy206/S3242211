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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id: String, vm: MainViewModel, navController: NavController) {
    val property = vm.getPropertyById(id)
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = "back",
                        modifier = Modifier
                            .padding(2.dp)
                            .size(40.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Details", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "back",
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .size(30.dp)
                            .align(Alignment.CenterVertically))
                }
            })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(24.dp)) {
                AsyncImage(model = property?.propertyImages?.mainImageSrc?:"", contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)))
                Spacer(modifier = Modifier.height(10.dp))
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.displayAddress ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Price : ${property?.price?.currencyCode} ${property?.price?.amount.toString()}",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = property?.summary ?: "")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.productLabel?.productLabelText ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.propertyTypeFullDescription ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.propertySubType ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.transactionType ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.propertyUrl ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.channel ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.customer?.branchName ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = property?.customer?.branchDisplayName ?: "N/A",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}