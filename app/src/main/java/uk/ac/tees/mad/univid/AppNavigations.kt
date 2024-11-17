package uk.ac.tees.mad.univid

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.univid.Screens.LoginScreen
import uk.ac.tees.mad.univid.Screens.RegisterScreen
import uk.ac.tees.mad.univid.Screens.SplashScreen

sealed class AppNavigations(val route : String){
    object SplashScreen : AppNavigations("splash_screen")
    object LoginScreen : AppNavigations("login_screen")
object RegisterScreen : AppNavigations("register_screen")
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    Surface{
    NavHost(navController = navController, startDestination = AppNavigations.SplashScreen.route ) {
        composable(AppNavigations.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(AppNavigations.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(AppNavigations.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
    }
    }
}