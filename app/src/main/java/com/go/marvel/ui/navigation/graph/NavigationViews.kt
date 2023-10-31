package com.go.marvel.ui.navigation.graph

import com.go.marvel.utils.BuildConfig.Companion.DETAIL_LIST_NAVIGATION_ROUTE
import com.go.marvel.utils.BuildConfig.Companion.FAVORITES_NAVIGATION_ROUTE
import com.go.marvel.utils.BuildConfig.Companion.MAIN_NAVIGATION_ROUTE

/**
[NavigationViews] is the sealed class where we define the differents routes what we have
[route]: instance for the route specific
This class is for have the differents routes for our navigation logic

@author Usiel Garcia Jimenez
 **/
sealed class NavigationViews(val route: String) {
    object MainScreen : NavigationViews(route = MAIN_NAVIGATION_ROUTE)
    object DetailListScreen : NavigationViews(route = DETAIL_LIST_NAVIGATION_ROUTE)
    object FavoriteComicsScreen : NavigationViews(route = FAVORITES_NAVIGATION_ROUTE)

}