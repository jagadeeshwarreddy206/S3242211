package uk.ac.tees.mad.univid.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.univid.AppNavigations
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.R
import uk.ac.tees.mad.univid.ui.theme.poppins

@Composable
fun RegisterScreen(navController: NavHostController, vm : MainViewModel) {
    val isSignedIn = vm.isSignedIn

    val isLoading = vm.isLoading

    if (isSignedIn.value) {
        navController.navigate(AppNavigations.HomeScreen.route) {
            popUpTo(0)
        }
    }

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(40.dp))
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null,
                modifier = Modifier.size(40.dp).clickable {
                    navController.popBackStack()
                })
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.tenant_finder_appicon),
                contentDescription = null, modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Register", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Enter Your Personal Information",
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Name", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = name,
                onValueChange = { name = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = "Enter your name",
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins,
                        color = Color.LightGray
                    )
                })
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Phone Number", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = phoneNumber,
                onValueChange = { phoneNumber = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = "Enter your phone number",
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins,
                        color = Color.LightGray
                    )
                })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Email", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = "Enter your email",
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins,
                        color = Color.LightGray
                    )
                })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Password", fontFamily = poppins, fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            TextField(value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = "Enter password",
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins,
                        color = Color.LightGray
                    )
                })
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { vm.signUp(context, name, phoneNumber, email, password) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Register",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .align(Alignment.Center)
            ) {
                LinearProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}