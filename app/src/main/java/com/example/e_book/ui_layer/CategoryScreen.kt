package com.example.e_book.ui_layer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_book.ui_layer.navigation.NavigationItem
import com.example.e_book.ui_layer.viewmodel.viewModel


@Composable
fun CategoryScreen(modifier: Modifier = Modifier, viewModel: viewModel = hiltViewModel(),navController: NavController) {
    LaunchedEffect(key1 = true) {
        viewModel.getBookCategory()
    }

    Box(modifier.fillMaxSize()) {
        val res = viewModel.stste.value
        if (res.isLoading) {
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        if(res.error.isNotEmpty()){
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               Text(text = "${res.error}")
            }
        }
        if(res.category.isNotEmpty()){
            LazyColumn(
                modifier
                    .fillMaxSize()
                    .padding(10.dp)) {
                items(res.category){
                    Card(
                        onClick = {
                            navController.navigate(NavigationItem.BookByCategory(it.category))

                    },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                    ) {
                        Spacer(modifier = modifier.height(10.dp))
                        Text(text =it.category, modifier.padding(top = 30.dp, bottom = 30.dp, start = 10.dp))
                        Spacer(modifier = modifier.height(10.dp))
                    }
                }
            }
        }
    }

}