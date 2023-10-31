package com.go.marvel.ui.navigation.graph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.go.marvel.domain.models.Comic
import com.go.marvel.ui.detail.DetailComicViewModel
import com.go.marvel.ui.detail.DetailListView
import com.go.marvel.ui.favorites.FavoritesComicView
import com.go.marvel.ui.favorites.FavoritesComicViewModel
import com.go.marvel.ui.main.ComicViewModel
import com.go.marvel.ui.main.MainComicView

/**
[RootNavGraph] is the graph component for make a navigation compose betwen views
[comicViewModel] is the main view model
[favoritesComicViewModel] is the favorites view model
[navController] is the instance of the navigation controller
This component is for the navigation logic

@author Usiel Garcia Jimenez
 **/
@Composable
fun RootNavGraph(
    comicViewModel: ComicViewModel,
    favoritesComicViewModel: FavoritesComicViewModel,
    detailComicViewModel: DetailComicViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationViews.MainScreen.route,
    ) {

        composable(NavigationViews.MainScreen.route) {
            MainComicView(comicViewModel = comicViewModel,
                navigateToDetailComicList = {
                    idComic, comic ->
                    detailComicViewModel.setComicForDetail(comic = comic, idComic = idComic)
                    navController.navigate(NavigationViews.DetailListScreen.route)
                },
                navigateToFavoritesComic = {
                    navController.navigate(NavigationViews.FavoriteComicsScreen.route)
                }
                )
        }

        composable(NavigationViews.DetailListScreen.route) {
            DetailListView(
                detailComicViewModel = detailComicViewModel,
                navToBackView = {
                    navController.navigate(NavigationViews.MainScreen.route) {
                        popUpTo(NavigationViews.DetailListScreen.route) { inclusive = true }
                    }
                }
                )
        }

        composable(NavigationViews.FavoriteComicsScreen.route) {
            FavoritesComicView(favoritesComicViewModel = favoritesComicViewModel, navToBackView = {
                navController.navigate(NavigationViews.MainScreen.route) {
                    popUpTo(NavigationViews.DetailListScreen.route) { inclusive = true }
                }
            })
        }
    }
}