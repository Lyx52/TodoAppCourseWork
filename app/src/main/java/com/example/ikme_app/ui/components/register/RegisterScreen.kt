package com.example.ikme_app.ui.components.register

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RegisterScreenBody(
            viewModel.registerState,
            onValueChange = { viewModel.updateState(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    if (viewModel.createUser()) {
                        navHostController.navigate(Routes.LoginScreen.routeId)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.register_title))
        }
        if (viewModel.registerState.errorMessage.isNotEmpty()) {
            Text(
                text = viewModel.registerState.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun RegisterScreenBody(state: RegisterState, onValueChange: (RegisterState) -> Unit)
{
    Text(
        text = stringResource(R.string.register_title),
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
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = state.confirmPassword,
        onValueChange = { onValueChange(state.copy(confirmPassword = it)) },
        label = {
            Text(stringResource(R.string.prompt_confirm_password))
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}
