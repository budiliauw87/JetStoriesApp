package com.liaudev.jetstories.ui.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.liaudev.jetstories.MainActivity
import com.liaudev.jetstories.R
import com.liaudev.jetstories.di.Injector
import com.liaudev.jetstories.di.ViewModelFactory
import com.liaudev.jetstories.state.UiState
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel


/**
 * Created by Budiliauw87 on 2022-12-11.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun LoginScreen(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: StoryViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository(LocalContext.current))
    )
) {
    var emailText by remember { mutableStateOf("") }
    var isErrorEmail by remember { mutableStateOf(false) }

    var passwordText by remember { mutableStateOf("") }
    var isErrorPassword by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    val mContext = LocalContext.current as Activity
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp, 24.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_outline_library_books_24),
                contentDescription = "Avatar",
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }
        Text(stringResource(id = R.string.subtitle_app), style = MaterialTheme.typography.h6)
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
                .padding(top = 24.dp)
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
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = !isLoading,
            onClick = {
                isErrorEmail = emailText.length == 0
                isErrorPassword = passwordText.length == 0
                isLoading = (!isErrorEmail && !isErrorPassword)

            }) {
            Text(text = "Login")
        }
        if (isLoading){
            viewModel.uiStateLogin.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        Log.e("LOGINSCREEN","LOADING")
                        viewModel.login(emailText,passwordText)
                    }
                    is UiState.Success -> {
                        Log.e("LOGINSCREEN","SUCESSS $uiState.data.toString()")
                        Toast.makeText(mContext, "Berhasil login", Toast.LENGTH_SHORT).show()
                        mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        mContext.finish()

                    }
                    is UiState.Error -> {
                        Toast.makeText(mContext, "Gagal login", Toast.LENGTH_SHORT).show()
                        isLoading = false
                    }
                }
            }

        }


    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen(modifier = Modifier)
}