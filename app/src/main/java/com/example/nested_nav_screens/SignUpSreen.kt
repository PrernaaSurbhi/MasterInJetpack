package com.example.nested_nav_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(navigationController: NavController){
    Box(modifier = Modifier.fillMaxSize(),
         contentAlignment = Alignment.Center){
        
           Text(text = "Sign Up",
           modifier = Modifier.clickable {
               navigationController.popBackStack()
           },
           color = Color.Green,
           fontSize = 24.sp,
           fontWeight = FontWeight.Bold
           )
    }
}

@Preview
@Composable
fun SignUpScreen(){
    SignUpScreen(navigationController = rememberNavController())
}