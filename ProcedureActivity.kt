package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.ui.theme.MyApplicationTheme

class ProcedureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra("id")
        val viewModel = ViewModelProvider(this).get(ProcedureViewModel::class.java)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    viewModel.fetchProcedure(idItem = "")
                    val YumYumState= viewModel.procedure.collectAsState()
                    val recipes = YumYumState.value
//                    val recipes = YumYumList()
                    ProcedureScreen(recipes, onItemClicked = {
                        val itemClicked = Intent(this, ProcedureRepository()::class.java)
                        startActivity(itemClicked)
                    })
                }
            }
        }
    }
}
@Preview
@Composable
fun htmlPreview() {
    val html = "<!DOCTYPE html>\n</head>\n<body>\n<li>This is a Heading</li>Method:\n" +
            "<li>1. Heat oil in a pan, add grated carrot, cabbage and chopped french beans, mix well and sauté for 1 min.<br></li>"+
            "2. Add salt, mix well and sauté for 4-5 min. or till vegetable is cooked well.\n" +
            "3. Add vinegar, soya sauce, red chilli powder and green chilli chopped, mix well and sauté for 1 min.\n" +
            "4. Remove in bowl, add chopped green capsicum, grated potato, chaat masala, black pepper crushed, salt, maida and corn flour and mix well.\n" +
            "5. Take one portion of mixture and give shape of bullets. Make similar with remaining mixture.\n" +
            "6. Take corn flour in a plate and roll the prepared bullets on it.\n" +
            "7. Heat oil in kadai and deep fry the bullets till crisp and golden brown in color.\n" +
            "8. Remove in absorbent paper and keep it aside.\n" +
            "9. Take corn flour in a bowl, add 2 tbsp of water, mix well and make slurry and keep it aside.\n" +
            "10. Heat oil in another pan, add chopped garlic, ginger, green chilli and black pepper crushed, mix well and sauté for 1 min.\n<p>This is a paragraph.</p>\n</body>\n</html>"
    Text(html)
    AndroidView(
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Composable
fun ProcedureItemUi(item : Procedure, onItemClicked: ()-> Unit){
    Card (
        modifier = Modifier
            .clickable {
                onItemClicked()
            }
            .padding(16.dp)
    ){
        Column {
            AsyncImage(model = item.imageUrl, contentDescription = item.item)
            Text(item.item)
            Text(text = "Recipes")
        }
    }
}

fun ProcedureList(): List<Recipe> {
    return listOf(
        Recipe("1", imageUrl = "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_240,h_240/rbyimyizfkbcb0rar2kj", item = "pasta"),
        Recipe("2", imageUrl =  "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_240,h_240/f699ac3cef2bb765a36121b1e14a1728",item = "white sauce pasta"),
        Recipe("3", imageUrl =  "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_288,h_360/v1674029848/PC_Creative%20refresh/3D_bau/banners_new/Chinese.png", item = "noodles"),
        Recipe("4", imageUrl =  "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_240,h_240/e9a97bee2a0b9a778a2b214d86a42148", item = "fried rice",),
        Recipe("5", imageUrl =  "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_240,h_240/w4ql9hlgrcxuozsfklqk",item = "Biryani"),
        Recipe("6", imageUrl =  "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_240,h_240/xzass7v1nz8qevfwclkq",item = "manchuria")
    )
}

@Composable
fun ProcedureScreen(procedures: List<Procedure> = emptyList(), recipe : String = "Pr", onItemClicked: () -> Unit) {
    Column(modifier = Modifier.background(Color.White)) {
        Text(text = "All recipes found")
        Text(text = recipe)
        LazyVerticalGrid(columns = GridCells.Fixed(2) ) {
            items(procedures) { item ->
                ProcedureItemUi(item, onItemClicked)
            }
        }
    }
}
