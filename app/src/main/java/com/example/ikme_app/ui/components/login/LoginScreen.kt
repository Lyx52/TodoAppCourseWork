package com.example.ikme_app.ui.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ikme_app.AppViewModelProvider
import com.example.ikme_app.R
import com.example.ikme_app.data.Routes
import com.example.ikme_app.ui.components.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginScreenBody(
            viewModel.loginState,
            onValueChange = { viewModel.updateState(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (viewModel.loginState.errorMessage.isEmpty()) {
                    coroutineScope.launch {
                        val user = viewModel.tryLogin()
                        authViewModel.updateState(authViewModel.authState.copy(
                            username = user?.username ?: "",
                            userId = user?.id ?: 0,
                            loggedIn = user != null
                        ))
                        if (authViewModel.authState.loggedIn) {
                            navHostController.navigate(Routes.TodoListScreen.routeId)
                        }
                    }

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login_title))
        }
        if (viewModel.loginState.errorMessage.isNotEmpty()) {
            Text(
                text = viewModel.loginState.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
        TextButton(
            modifier = Modifier.align(alignment = AbsoluteAlignment.Right),
            onClick = {
                navHostController.navigate(Routes.RegisterScreen.routeId)
            }) {
            Text(stringResource(R.string.register_title))
        }
    }
}

@Composable
fun LoginScreenBody(state: LoginState, onValueChange: (LoginState) -> Unit)
{
    Text(
        text = stringResource(R.string.login_title),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(24.dp))
    OutlinedTextField(
        value = state.username,
        onValueChange = { onValueChange(state.copy(username = it)) },
        label = {
            Text(stringResource(R.string.prompt_username))
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = state.password,
        onValueChange = { onValueChange(state.copy(password = it)) },
        label = {
            Text(stringResource(R.string.prompt_password))
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}
