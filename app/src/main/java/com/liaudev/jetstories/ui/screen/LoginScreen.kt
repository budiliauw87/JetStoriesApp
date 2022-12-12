package com.liaudev.jetstories.ui.screen

import android.app.Activity
import android.content.Intent
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
import com.liaudev.jetstories.MainActivity
import com.liaudev.jetstories.R


/**
 * Created by Budiliauw87 on 2022-12-11.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun LoginScreen(modifier: Modifier) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorPasword by remember { mutableStateOf(false) }
    val mContext = LocalContext.current as Activity
    Column(
        modifier = Modifier
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
            isError = true,
            onValueChange = { emailText = it },
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
            onValueChange = { passwordText = it },
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
            enabled = true,
            onClick = {
                login(mContext,emailText,passwordText)
            }) {
            Text(text = "Login")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

        }


    }
}

fun login(activity: Activity, email: String, password: String) {
    activity.startActivity(Intent(activity, MainActivity::class.java))
    activity?.finish()
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen(modifier = Modifier)
}