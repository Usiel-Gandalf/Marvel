package com.go.marvel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.go.marvel.ui.detail.DetailComicViewModel
import com.go.marvel.ui.favorites.FavoritesComicViewModel
import com.go.marvel.ui.main.ComicViewModel
import com.go.marvel.ui.navigation.graph.RootNavGraph
import com.go.marvel.ui.theme.MarvelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelTheme {
                val viewModel: ComicViewModel = hiltViewModel()
                val detailComicViewModel: DetailComicViewModel = hiltViewModel()
                val favoritesComicViewModel: FavoritesComicViewModel = hiltViewModel()
                val navController = rememberNavController()
                RootNavGraph(
                    comicViewModel = viewModel,
                    favoritesComicViewModel = favoritesComicViewModel,
                    detailComicViewModel = detailComicViewModel,
                    navController = navController
                )
            }
        }
    }
}