package com.go.marvel.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
[TopAppBar] function for the design of the app bar what we uses in all views
[navToBackView] high order function what make the back action when is clicked
[title] title what we want to appear in the appbar for have a descriptive screen
TopAppBar component for used in differents screens

@author Usiel Garcia Jimenez
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navToBackView: () -> Unit, title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navToBackView.invoke() }) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}