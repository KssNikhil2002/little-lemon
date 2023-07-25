package com.example.capstoneproject

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capstoneproject.ui.theme.Black
import com.example.capstoneproject.ui.theme.Yellow

@Composable
fun  ProfileScreen(sharedPreferences: SharedPreferences, navController: NavController){
    val  Context= LocalContext.current;

    var firstName by remember { mutableStateOf(sharedPreferences.getString("firstname", "").toString()) }
    var lastName by remember { mutableStateOf(sharedPreferences.getString("lastname", "").toString()) }
    var email by remember { mutableStateOf(sharedPreferences.getString("email", "").toString()) }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        Row( Modifier.height(40.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillWidth,
            )

        }

        Row( Modifier.padding(top= 40.dp)) {
            Text(
                text = "Personal information",
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF495E57),
            )
        }


        OutlinedTextField(value = firstName,
            readOnly = true,
            onValueChange = { it -> firstName = it },
            label = ({ Text(text = "First Name") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )

        OutlinedTextField(value = lastName,
            readOnly = true,
            onValueChange = { it -> lastName = it },
            label = ({ Text(text = "Last Name") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )
        OutlinedTextField(value = email,
            readOnly = true,
            onValueChange = { it -> email = it },
            label = ({ Text(text = "Email") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    sharedPreferences.edit().clear().apply()
                    Toast.makeText(Context, "logout successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(OnBoarding.route)
                },
                colors = ButtonDefaults.buttonColors(Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Log Out", color = Black)
            }

        }
    }


}



