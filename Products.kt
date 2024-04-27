package com.example.myapplication

import android.icu.text.ListFormatter.Width
import android.media.Image
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme

class Products : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
                    ProductsGrid()

                }
            }
        }
    }
}

@Composable
fun ProductItem(
    title:String,
    imageUrl:String,
    price:String
){
    var visible by remember {
        mutableStateOf(true)
    }
    Card(
        modifier=Modifier.padding(8.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier=Modifier
                .clickable{
                    visible=!visible
                }
        ){
            AnimatedVisibility(visible = !visible) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription =imageUrl
                )

            }
//    Image(
            //painter=painterResources(id=R.drawable.category_default),
            //contentDescription="",
            //modifier=Modifier
            //.width(200.dp)
            //.height(200.dp)
            //)

            Text(
                text=title,
                modifier=Modifier.padding(4.dp)
            )
        }
    }
}
data class Product(
    val title:String,
    val imageUrl:String,
    val Price:String
)



fun generateProducts():List<Product>{
    return listOf(
        Product("Classic Black Tee - Timeless Style and Comfort","https://i.imgur.com/9DqEOV5.jpeg","73"),
        Product("Sleek White & Orange Wireless Gaming Controller","https://i.imgur.com/ZANVnHE.jpeg","69"),
        Product("Electronics","https://i.imgur.com/ZANVnHE.jpeg","28"),
        Product("Sleek Wireless Headphone & Inked Earbud Set","https://i.imgur.com/yVeIeDa.jpeg","220"),
        Product("Sleek Comfort-Fit Over-Ear Headphones","https://i.imgur.com/SolkFEB.jpeg","222"),
        Product("Efficient 2-Slice Toaster","https://i.imgur.com/keVCVIa.jpeg","123"),
        Product("Sleek Wireless Computer Mouse","https://i.imgur.com/WJFOGIC.jpeg","877"),
        Product("Sleek Modern Laptop with Ambient Lighting","https://i.imgur.com/Z9oKRVJ.jpeg","298"),
        Product("Stylish Red & Silver Over-Ear Headphones","https://i.imgur.com/YaSqa06.jpeg","900"),
        Product("Sleek Mirror Finish Phone Case","https://i.imgur.com/yb9UQKL.jpeg","9000")
    )
}

@Composable
fun ProductsGrid() {
    Column {
        Text(
            "ALL PRODUCTS"
        )
        val products = generateProducts()
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(products) { product ->
                ProductItem(product.title, product.imageUrl,product.Price)
            }
            }
        }
}
