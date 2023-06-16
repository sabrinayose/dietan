package com.example.healthyapp.screens.recommendation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun RecommendationScreen() {
    val recommendedFoods = remember { mutableStateListOf<Food>() }

    recommendFoods(recommendedFoods)

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            items(recommendedFoods) { food ->
                FoodItem(food)
            }
        }
    }
}

@Composable
fun FoodItem(food: Food) {
    Card(
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .width(275.dp)
            .height(325.dp)
            .background(Color.White)
            .padding(
                horizontal = 8.dp,
                vertical = 12.dp,
            )
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ){
                AsyncImage(
                    model = food.image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White)
                    ){
                Text(text = food.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                Text(text = "${food.calories} cal",
                fontSize = 12.sp, color= Color.LightGray
                    )
                Text(text = food.description,
                    fontSize = 12.sp, maxLines = 3, overflow = TextOverflow.Ellipsis, color = Color.Black
                    , fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 10.dp),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

data class Food(
    val name: String,
    val calories: Int,
    val image: String,
    val description: String
)

fun recommendFoods(recommendedFoods: MutableList<Food>) {
    recommendedFoods.clear()

    val foodList = listOf(
        Food(
            "Apple",
            52,
            "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Nnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
            "Apples are a popular fruit known for their crisp texture and sweet-tart flavor. They are low in calories and high in fiber, making them a healthy snack choice. Apples are also a good source of antioxidants and vitamin C."
        ),
        Food(
            "Banana",
            96,
            "https://images.everydayhealth.com/images/diet-nutrition/all-about-bananas-nutrition-facts-health-benefits-recipes-and-more-rm-722x406.jpg",
            "Bananas are a tropical fruit with a creamy texture and a mildly sweet taste. They are rich in potassium, vitamin B6, and dietary fiber. Bananas are often consumed as a quick and nutritious snack."
        ),
        Food(
            "Carrot",
            41,
            "https://a-z-animals.com/media/2022/09/shutterstock_440493100-e1666516567945-1024x683.jpg",
            "Carrots are root vegetables that are known for their vibrant orange color. They are low in calories and high in vitamins, particularly vitamin A, which is essential for eye health. Carrots can be eaten raw or cooked and are a popular ingredient in various dishes."
        ),
        Food(
            "Almonds",
            160,
            "https://i0.wp.com/post.healthline.com/wp-content/uploads/2023/02/Almonds-Table-Bowl-1296x728-Header.jpg?w=1155&h=1528",
            "Almonds are nuts that are packed with nutrients. They are a good source of healthy fats, protein, fiber, and various vitamins and minerals. Almonds are known to have several health benefits, including promoting heart health and aiding in weight management."
        ),
        Food(
            "Yogurt",
            59,
            "https://static01.nyt.com/images/2018/07/18/dining/18YOGURT1/18YOGURT1-superJumbo.jpg",
            "Yogurt is a dairy product that is created by fermenting milk with bacterial cultures. It is a good source of protein, calcium, and probiotics, which are beneficial for gut health. Yogurt comes in various flavors and can be enjoyed as a snack or used in cooking and baking."
        )
    )

    for (food in foodList) {
        recommendedFoods.add(food)
    }
}