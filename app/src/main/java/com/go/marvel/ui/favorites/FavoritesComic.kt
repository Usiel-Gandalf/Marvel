package com.go.marvel.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.go.marvel.R
import com.go.marvel.domain.models.Comic
import com.go.marvel.ui.components.ErrorMessage
import com.go.marvel.ui.components.LoadingScreen
import com.go.marvel.ui.components.TopAppBar

/**
[FavoritesComicView] is a view where all our favorites comics saved into database are show it
[favoritesComicViewModel]: is view model created for this view in particular
[navToBackView]: is a high order function for back to main view when we clicked a back scaffold button
This component is the main section where the list of all our comics are show it

@author Usiel Garcia Jimenez
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesComicView(
    favoritesComicViewModel: FavoritesComicViewModel,
    navToBackView: () -> Unit
) {

    val myDataState by favoritesComicViewModel.comicFavoritesList.collectAsState()
    val loading by favoritesComicViewModel.loading.observeAsState(initial = false)
    val error by favoritesComicViewModel.error.observeAsState(initial = true)

    Scaffold(
        topBar = {
            TopAppBar(navToBackView = { navToBackView.invoke() }, title = stringResource(id = R.string.favorites_description))
        },
        content = {
            if(loading){
                LoadingScreen()
            }else{
                when(error){
                    true -> {
                        ErrorMessage(message = stringResource(id = R.string.error_carga))
                    }

                    false -> {
                        Column(modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .padding(all = 8.dp)
                        ) {
                            ContentFavoritesComicSection(listFavoritesComics = myDataState)
                        }
                    }
                }
            }

        }
    )
}

/**
[ContentFavoritesComicSection] is a component where all our favorites comic are show it in the LazyGrid
[listFavoritesComics]: is a list of our favorites comic saved into database
This component is the section where our favorites comic are showen

@author Usiel Garcia Jimenez
 **/
@Composable
fun ContentFavoritesComicSection(
    listFavoritesComics: List<Comic>
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(listFavoritesComics) {

            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(360.dp)
                    .fillMaxSize()
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
            ) {
                Column() {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                        ,
                        painter = rememberAsyncImagePainter(it.imageURL),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        text = stringResource(id = R.string.titulo_descripcion) + it.title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(60.dp)
                            .background(Color.Black)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}