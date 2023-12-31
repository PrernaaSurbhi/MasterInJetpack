package com.example.jetpackcomposerecyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.DataManger
import com.example.bottom_navigation.MainScreen
import com.example.screens.EmployeeListScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CoroutineScope(Dispatchers.IO).launch {
//            delay(1000)
//            DataManger.loadAssetsFromFile(applicationContext)
//        }

        setContent {
            //Todo initialization code for Navigation between Screen
//            MaterialTheme{
//                navHostController = rememberNavController()
//                setUpNavGraph(navController = navHostController )
//            }

            //Todo initialization code for Bottom Navigation
            MaterialTheme {
                MainScreen()
            }

//           App()

//            val painter = painterResource(id = R.drawable.snow_man)
//            val description = "snow man is playing"
//            val title = "snow man is playing"
//
//            Box(modifier = Modifier
//                .fillMaxWidth(0.5f)
//                .padding(16.dp)
//            ) {
//                ImageCard(painter,description,title, modifier = Modifier.fillMaxWidth())
//            }

//            ListByColumn()

         //   ListByLazyColumn()

          //  ListByLazyColumItemsIndexed()

          //  ConstraintsLayoutFun()

          //  ListItemdisplay()

//            PersonListView()

          //  EditTextCharacterCount()

            //method for initializing the Home Screen Navigation

        }
    }
}


//This method is to add character count on EditText
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditTextCharacterCount(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        val maxChar = 10

        OutlinedTextField(
            value = name,
            onValueChange = { newText ->
                if(newText.length <= maxChar){
                    name = newText
                }
            },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Enter your name") },
            maxLines = 1
            )

    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PersonListView(){
      val sections = listOf("A","B","C","D","E","F","G")
    LazyColumn(
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        sections.forEach {section ->  
            stickyHeader {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(12.dp)
                    ,text = "Section $section")
            }
            items(10){
                Text(modifier = Modifier.padding(12.dp),
                    text = "Item $it from the section $section" )
            }
        }
    }
}

@Composable
private fun ListItemdisplay(){
    val rememberScrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState)
    ) {
        for(i in 1..50){
            Text(
                text ="Item in $i",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
            )
        }

    }
}


@Preview
@Composable
private fun ConstraintsLayoutFun(){
   val constraintsSet = ConstraintSet{
       val cyanBox = createRefFor("cyanBox")
       val redBox = createRefFor("redBox")

       constrain(cyanBox){
           top.linkTo(parent.top)
           start.linkTo(parent.start)
           width = Dimension.value(100.dp)
           height = Dimension.value(100.dp)
       }

       constrain(redBox){
           top.linkTo(parent.top)
           start.linkTo(cyanBox.end)
           width = Dimension.value(100.dp)
           height = Dimension.value(100.dp)
       }
   }

   ConstraintLayout(constraintsSet, modifier = Modifier.fillMaxSize()) {
       Box(modifier = Modifier
           .background(Color.Cyan)
           .layoutId("cyanBox")
       )
       Box(modifier = Modifier
           .background(Color.Red)
           .layoutId("redBox")
       )
   }
}

@Composable
private fun ListByLazyColumItemsIndexed(){
    // lazy column is scrollable by default
    LazyColumn {
        itemsIndexed(
            listOf("This","is","RecyclerView","of","any","item","type")
        ){ index,string ->
            Text(text = " $string",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }
    }
}

@Composable
private fun ListByColumn(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for(i in 1..50)
            Text(text = "Item $i",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
    }
}

@Composable
private fun ListByLazyColumn(){
    // lazy column is scrollable by default
    LazyColumn {
         items(5000){
             Text(text = "Item $it",
                 fontSize = 25.sp,
                 fontWeight = FontWeight.Bold,
                 textAlign = TextAlign.Center,
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(vertical = 24.dp)
             )
         }

    }
}

@Composable
private fun ImageCard(
    painter: Painter,
    contentDescription:String,
    title:String,
    modifier: Modifier){
      Card(
          modifier  = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(15.dp),

      ) {
           Box(modifier = Modifier.height(200.dp)) {
               Image(painter = painter,
                   contentDescription = contentDescription,
               contentScale = ContentScale.Crop)
           }
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(12.dp),
               contentAlignment = Alignment.BottomStart
           ) {
               Text(title,style= TextStyle(color = Color.White, fontSize = 16.sp))
           }
      }
    }

@Composable
fun App(){
    if (DataManger.isDataLoaded.value){
        EmployeeListScreen(data = DataManger.data) {
            
        }
    }else{
        Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)) {
            Text(text ="Loading.....",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

