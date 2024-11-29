package uk.ac.tees.mad.univid

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.univid.Screens.DetailScreen
import uk.ac.tees.mad.univid.Screens.HomeScreen
import uk.ac.tees.mad.univid.Screens.LoginScreen
import uk.ac.tees.mad.univid.Screens.RegisterScreen
import uk.ac.tees.mad.univid.Screens.SplashScreen

sealed class AppNavigations(val route : String){
    object SplashScreen : AppNavigations("splash_screen")
    object LoginScreen : AppNavigations("login_screen")
    object RegisterScreen : AppNavigations("register_screen")
    object HomeScreen : AppNavigations("home_screen")
    object DetailScreen : AppNavigations("detail_screen/{id}") {
        fun createRoute(id: Int): String {
            return "detail_screen/$id"
        }
    }
}

@Composable
fun AppNavigation(){
    val vm : MainViewModel = viewModel()
    val navController = rememberNavController()
    Surface{
    NavHost(navController = navController, startDestination = AppNavigations.SplashScreen.route ) {
        composable(AppNavigations.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(AppNavigations.LoginScreen.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(AppNavigations.RegisterScreen.route) {
            RegisterScreen(navController = navController, vm = vm)
        }
        composable(AppNavigations.HomeScreen.route){
            HomeScreen(vm, navController)
        }
        composable(
            route = AppNavigations.DetailScreen.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""

            DetailScreen(id, vm, navController)
        }
    }
    }
}