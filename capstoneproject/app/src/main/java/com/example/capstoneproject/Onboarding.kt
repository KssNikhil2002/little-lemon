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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capstoneproject.ui.theme.Black
import com.example.capstoneproject.ui.theme.GreenGrayDark
import com.example.capstoneproject.ui.theme.Yellow

@Composable
fun Onboarding(sharedPreferences: SharedPreferences, navController: NavController) {

    val Context = LocalContext.current
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

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

        Row( modifier = Modifier.padding(top = 40.dp)) {
            Text(
                text = "Let's get to know you!!",
                Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = GreenGrayDark,
                fontSize = 25.sp
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

        //textfield 1 for First Name
        OutlinedTextField(value = firstName,
            onValueChange = { it -> firstName = it },
            label = ({ Text(text = "First Name") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        //Text Field 2 for Last name
        OutlinedTextField(value = lastName,
            onValueChange = { it -> lastName = it },
            label = ({ Text(text = "Last Name") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        //Text field 3 for email
        OutlinedTextField(value = email,
            onValueChange = { it -> email = it },
            label = ({ Text(text = "Email") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )



        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    if (firstName.text.isNotBlank() && lastName.text.isNotBlank() && email.text.isNotBlank()) {
                        sharedPreferences.edit()
                            .putString("firstname", firstName.text)
                            .putString("lastname", lastName.text)
                            .putString("email", email.text)
                            .putBoolean("userRegistered", true)
                            .apply()
                        Toast.makeText(Context, "Registration successful", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Home.route) {
                            popUpTo(0)
                        }
                    } else {
                        Toast.makeText(Context, "Registration unsuccessful. Please enter all data",
                            Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register", color = Black, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }

        }
    }


}