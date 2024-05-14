package com.example.ikme_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ikme_app.AppViewModelProvider
import com.example.ikme_app.ui.components.login.LoginScreen
import com.example.ikme_app.ui.components.todo.TodoListScreen
import com.example.ikme_app.data.Routes
import com.example.ikme_app.ui.components.AuthViewModel
import com.example.ikme_app.ui.components.register.RegisterScreen

@Composable
fun AppNavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(navController = navController, startDestination = Routes.LoginScreen.routeId) {
        composable(Routes.LoginScreen.routeId) { LoginScreen(navController, authViewModel) }
        composable(Routes.RegisterScreen.routeId) { RegisterScreen(navController) }
        composable(Routes.TodoListScreen.routeId) { TodoListScreen(navController, authViewModel) }
    }
}