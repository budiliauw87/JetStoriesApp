package com.liaudev.jetstories.ui.screen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.liaudev.jetstories.R
import com.liaudev.jetstories.data.network.response.BaseResponse
import com.liaudev.jetstories.di.Injector
import com.liaudev.jetstories.di.ViewModelFactory
import com.liaudev.jetstories.state.UiState
import com.liaudev.jetstories.ui.viewmodel.AuthViewModel


/**
 * Created by Budiliauw87 on 2022-12-14.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val mContext = LocalContext.current as Activity
    viewModel.uiStateRegister.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Idle -> {
                Log.e("REGISTER", "IDLE")
            }
            is UiState.Loading -> {
                Log.e("REGISTER", "LOADING")
            }
            is UiState.Success -> {
                Toast.makeText(mContext, "Berhasil REGISTER", Toast.LENGTH_SHORT).show()
                navController.navigateUp()

            }
            is UiState.Error -> {
                Toast.makeText(mContext, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        RegisterContent(viewModel, uiState, navController)
    }
}


@Composable
fun RegisterContent(
    viewModel: AuthViewModel,
    uiState: UiState<BaseResponse>,
    navController: NavHostController,
) {
    var nameText by remember { mutableStateOf("") }
    var isErrorName by remember { mutableStateOf(false) }
    var emailText by remember { mutableStateOf("") }
    var isErrorEmail by remember { mutableStateOf(false) }
    var passwordText by remember { mutableStateOf("") }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    when (uiState) {
        is UiState.Loading -> {
            isLoading = true
        }
        else -> {
            isLoading = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp, 24.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(stringResource(id = R.string.register), style = MaterialTheme.typography.h6)
        OutlinedTextField(
            value = nameText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "personIcon"
                )
            },
            isError = isErrorName,

            onValueChange = {
                nameText = it
                isErrorName = nameText.length == 0
            },
            label = { Text(text = "Username") },
            singleLine = true,
            placeholder = { Text(text = "Nama penguna") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .focusRequester(focusRequester)
        )
        OutlinedTextField(
            value = emailText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "emailIcon"
                )
            },
            isError = isErrorEmail,

            onValueChange = {
                emailText = it
                isErrorEmail = emailText.length == 0
            },
            label = { Text(text = "Email") },
            singleLine = true,
            placeholder = { Text(text = "Masukan Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusRequester(focusRequester)
        )

        OutlinedTextField(
            value = passwordText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passIcon"
                )
            },
            singleLine = true,
            isError = isErrorPassword,
            onValueChange = {
                passwordText = it
                isErrorPassword = passwordText.length == 0
            },
            label = { Text(text = "Kata sandi") },
            placeholder = { Text(text = "Masukan Kata sandi") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusRequester(focusRequester)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = !isLoading,
            onClick = {
                isErrorPassword = passwordText.length == 0
                isErrorEmail = emailText.length == 0
                if (!isErrorEmail && !isErrorPassword) {
                    focusManager.clearFocus()  //clear focus
                    viewModel.register(nameText, emailText, passwordText)
                }
            }) {
            Text(text = "Submit")
        }
        Text(
            stringResource(
                R.string.already_have_account
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 8.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            enabled = true,
            onClick = { navController.navigateUp() }) {
            Text(text = "To Login")
        }
    }
}

