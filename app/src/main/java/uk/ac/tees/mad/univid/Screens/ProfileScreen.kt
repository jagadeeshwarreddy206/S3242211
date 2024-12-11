package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.jetbrains.annotations.Async
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.R
import uk.ac.tees.mad.univid.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(vm: MainViewModel, navController: NavHostController) {

    val userData = vm.userData.value


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(25.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "My Profile", modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Edit",
                        fontFamily = poppins,
                        fontSize = 15.sp,
                        color = Color.Blue,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 10.dp)
                    )
                }
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            if (userData != null) {
                if (userData.profilePictureUrl.isNotEmpty()) {
                    AsyncImage(
                        model = userData.profilePictureUrl, contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.man), contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = userData.name, fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Column(Modifier.padding(horizontal = 24.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Profile Details", fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Email Address",fontFamily = poppins,
                        fontSize = 12.sp)
                    Text(
                        text = userData.email, fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Contact Number",fontFamily = poppins,
                        fontSize = 12.sp)
                    Text(
                        text = userData.phoneNumber, fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }else{
                Text(text = "Loading...")
            }
            Column(Modifier.padding(horizontal = 24.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(Modifier.fillMaxWidth().clickable {

                }){
                    Text(text = "Favorite Properties")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(Modifier.fillMaxWidth().clickable {

                }){
                    Text(text = "Log Out")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}