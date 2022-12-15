package com.liaudev.jetstories.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liaudev.jetstories.R


/**
 * Created by Budiliauw87 on 2022-11-29.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun AboutScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        HeaderContent(modifier)
    }
}

@Composable
fun HeaderContent(modifier: Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(4.dp)
                    .size(70.dp)
                    .background(color = Color.Gray, CircleShape)
                    .clip(CircleShape)

            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp)
            ) {
                Text(
                    stringResource(R.string.my_name),
                    style = MaterialTheme.typography.h4,
                )
                Text(
                    stringResource(R.string.my_headline),
                    style = MaterialTheme.typography.h6,
                    modifier = modifier.padding(top = 8.dp)
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "icontime",
                modifier = Modifier.size(24.dp),
            )
            Text(
                stringResource(R.string.my_join_year),
                modifier = modifier.padding(start = 8.dp, end = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "icontime",
                modifier = Modifier.size(24.dp)
            )
            Text(
                stringResource(R.string.my_city),
                modifier = modifier.padding(start = 8.dp)
            )
        }
        // About text
        Text(
            stringResource(R.string.about_me),
            style = MaterialTheme.typography.caption,
            modifier = modifier.padding(top = 8.dp, bottom = 16.dp),
        )
        Divider(thickness = 1.dp, color = Color.Gray)
        //contact
        Text(
            stringResource(R.string.contact),
            style = MaterialTheme.typography.h6,
            modifier = modifier.padding(top = 8.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "iconphone",
                modifier = Modifier.size(24.dp),
            )
            Text(
                stringResource(R.string.my_phone),
                modifier = modifier
                    .padding(16.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    val phone = "+6282111153753"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                    context.startActivity(intent)
                }) {
                Text(text = "Phone")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "iconmail",
                modifier = Modifier.size(24.dp),
            )
            Text(
                stringResource(R.string.my_email),
                modifier = modifier
                    .padding(16.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    val emailIntent = Intent(
                        Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "budiliauw87@gmail.com", null
                        )
                    )
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hired me")
                    emailIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Hai budi, i want hired to google company"
                    )
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
                }) {
                Text(text = "Mail")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "iconaccount",
                modifier = Modifier.size(24.dp),
            )
            Text(
                stringResource(R.string.my_github),
                modifier = modifier
                    .padding(16.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    val intentSite = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://budiliauw87.github.io")
                    )
                    context.startActivity(intentSite)
                }) {
                Text(text = "Visit")
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AboutScreenPreview() {
    AboutScreen(modifier = Modifier)
}


