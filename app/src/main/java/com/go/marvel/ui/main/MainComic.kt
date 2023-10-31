package com.go.marvel.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.go.marvel.ui.components.FloatingButton
import com.go.marvel.ui.components.LoadingScreen

/**
[MainComicView] is a main view for the app where all the comics are show it
[viewModel]: is a view model what work with this view
[navigateToDetailComicList]: is a high order function for go to detail ui of the comics
[navigateToFavoritesComic]: is a high order function for navigate to favorites view
This class show all the comics what it is consumed in the API

@author Usiel Garcia Jimenez
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComicView(
    comicViewModel: ComicViewModel,
    navigateToDetailComicList: (idComic: Int, comic: Comic) -> Unit,
    navigateToFavoritesComic: () -> Unit
) {
    val loading by comicViewModel.loading.observeAsState(initial = false)
    val error by comicViewModel.error.observeAsState(initial = true)

    LaunchedEffect(true) {
        comicViewModel.getComicList()
    }
    val comicList: MutableList<Comic> = comicViewModel.filterComicList

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingButton(
                icon = Icons.Filled.Star,
                textButtonDescription = stringResource(id = R.string.favorites_description)
            ) {
                navigateToFavoritesComic.invoke()
            }
        },
        topBar = {
            SearchComic(
                searchValue = comicViewModel.search.value,
                updateSearchText = {
                    comicViewModel.updateSearchText(it)
                },
                searchComic = {
                    comicViewModel.searchComic()
                })
        },
        content = { it ->
            if (loading){
                    LoadingScreen()
                }else{
                    when(error){
                        true ->{
                            ErrorMessage(message = stringResource(id = R.string.error_carga))
                        }

                        false ->{
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it)
                                    .padding(all = 8.dp)
                            ) {
                                LazyCardsForComics(comicList = comicList, onClickGoToDetailComic = {
                                    idComic, comic ->
                                    navigateToDetailComicList(idComic, comic)
                                })
                            }
                        }
                    }
                }
        }
    )
}

/**
[SearchComic] is a view where is the textfield for search comics
[searchValue]: is the value what we introduce in the textfield
[updateSearchText]: is a high order function for update the text of the textfield in real time
[searchComic]: is a high order function for make a petition to de view model for search a comic
This component is the section where the search textfield is it

@author Usiel Garcia Jimenez
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComic(
    searchValue: String,
    updateSearchText: (text: String) -> Unit,
    searchComic: () -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onValueChange = { text ->
                updateSearchText(text)
            },
            placeholder = { Text(text = stringResource(id = R.string.search_text)) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        searchComic.invoke()
                    }
                )
            })
    }
}

/**
[LazyCardsForComics] is a view where all the comics are show, here is the main design for show it
[comicList]: is the List of the comics what are show it in the ui
[onClickGoToDetailComic]: is a high order function what when we clicked to one comic go to detail view
[setComicSelected]: is a high order function for save the comic in the view model for show it in the detail view
This component is the section where all the comics are show it with a LazyColumn

@author Usiel Garcia Jimenez
 **/
@Composable
fun LazyCardsForComics(
    comicList: List<Comic>,
    onClickGoToDetailComic: (idComic: Int, comic: Comic) -> Unit,
) {
    lateinit var comic: Comic

    if (comicList.isNotEmpty()) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(comicList) {

                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(360.dp)
                        .fillMaxSize()
                        .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clickable {
                                comic = Comic(id = it.id, title = it.title, imageURL = it.imageURL, variant = it.variant)
                                onClickGoToDetailComic(it.id, comic)
                            },
                        painter = rememberAsyncImagePainter(it.imageURL),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                        Text(
                            text = it.title,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxSize()
                                .height(60.dp)
                                .verticalScroll(rememberScrollState(0))
                                .background(Color.Black)

                        )
                }
            }
        }
    } else {
        Text(
            text = stringResource(id = R.string.no_result_text), color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)

        )
    }

}






