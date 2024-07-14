package com.example.e_book.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.e_book.ui_layer.BookByCategory
import com.example.e_book.ui_layer.PdfShowScreen
import com.example.e_book.ui_layer.tabLayout

@Composable
fun NavHostController() {
    val navController = rememberNavController()
     NavHost(navController = navController, startDestination = NavigationItem.HomeScreen) {
         composable<NavigationItem.HomeScreen> {
            tabLayout(navController)
         }
         composable<NavigationItem.BookByCategory> {
             var category = it.toRoute<NavigationItem.BookByCategory>()
             BookByCategory(category = category.category, navController = navController)
         }
         composable<NavigationItem.PdfShowScreen>{
             val url = it.toRoute<NavigationItem.PdfShowScreen>()
            PdfShowScreen(url.PdfUrl)
         }


     }


}