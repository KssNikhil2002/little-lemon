package com.example.capstoneproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import com.example.capstoneproject.R
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.capstoneproject.ui.theme.GreenGrayDark
import com.example.capstoneproject.ui.theme.White
import com.example.capstoneproject.ui.theme.Yellow
import java.util.Locale
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun HomeScreen(navController: NavController) {
    val Context = LocalContext.current;

    var searchText by remember { mutableStateOf("") }
    val database by lazy {
        Room.databaseBuilder(Context, AppDatabase::class.java, "littleLemon.db").build()
    }



    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var menuItems = emptyList<MenuItemRoom>()
    menuItems = databaseMenuItems;

    var categories=menuItems.map {
        it.category
    }

    Column() {

        Header(navController)
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = GreenGrayDark)
        ) {
            Row() {
                Column(Modifier.fillMaxWidth(0.6f)) {

                    Text(
                        text = "Little Lemon",
                        Modifier
                            .padding(start = 11.dp, top = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Yellow,
                        fontSize = 40.sp
                    )
                    Text(
                        text = "Chicago",
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        Modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = 10.dp, top = 13.dp),
                        textAlign = TextAlign.Start,
                        color = White,
                        fontSize = 15.sp
                    )


                }
                Row(modifier = Modifier.padding(top = 50.dp, end = 35.dp, start = 20.dp, bottom=20.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",

                        )
                }

            }

            TextField(shape = RoundedCornerShape(10.dp),
                value = searchText,
                onValueChange = { it -> searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 15.dp)
                    .background(color = White, shape = RoundedCornerShape(10.dp)),

                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent),
                placeholder = { Text("Enter Search Phrase") },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }
            )

        }
        Card(modifier = Modifier.fillMaxWidth()) {

            Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                Text(text = "ORDER FOR DELIVERY", fontWeight = FontWeight.Bold)

                Row(modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    menuItems.map{
                        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                            contentColor = GreenGrayDark,
                            backgroundColor = Yellow) )
                        {
                            Text(text = it.category)
                        }
                    }
                }
            }
        }


        if (searchText.isNotEmpty()) {
            val searchedItems = menuItems.filter {
                it.title.lowercase(Locale.getDefault()).contains(searchText)
            }
            MenuItems(searchedItems)

        } else {
            MenuItems(menuItems)

        }

    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}


@Composable
fun Header(navController: NavController){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Spacer(modifier = Modifier.width(50.dp))
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .size(40.dp))

        Box(modifier = Modifier
            .size(50.dp)
            .clickable { navController.navigate(Profile.route) }){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 2.dp))
        }


    }
}
@Composable
fun MenuItems(menuItemsRoom: List<MenuItemRoom>) {
    LazyColumn {
        items(menuItemsRoom) { Dish ->
            MenuItems(Dish)
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(dish: MenuItemRoom) {
    Card(Modifier.padding(12.dp), elevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column {
                Text(
                    text = dish.title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = dish.description,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = "$" + dish.price + ".99", color = Color.Gray, fontWeight = FontWeight.Bold
                )
            }
            GlideImage(
                model = dish.image,
                contentDescription = dish.title,
                Modifier.padding(top = 12.dp).clip(RoundedCornerShape(10.dp))
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )

}