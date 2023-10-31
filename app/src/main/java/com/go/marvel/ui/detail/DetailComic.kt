package com.go.marvel.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.go.marvel.R
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.models.Creator
import com.go.marvel.ui.components.FloatingButton
import com.go.marvel.ui.components.TopAppBar
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_IMAGE_FOR_CREATORS_VIEW
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_IMAGE_FOR_VARIANTS_COMICS

/**
[DetailListView] is the main view where the details of one comic selected are show it
[navToBackView]: is a high order function for back to main view when we clicked a back scaffold button
This component is the main section where details of one comic in particular are show it

@author Usiel Garcia Jimenez
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailListView(
    detailComicViewModel: DetailComicViewModel,
    navToBackView: () -> Unit,
) {
    LaunchedEffect(true) {
        detailComicViewModel.getStatusComicIntoDataBase()
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            when (!detailComicViewModel.comicStatusIntoDataBase.value) {
                true -> {
                    FloatingButton(
                        icon = Icons.Filled.Delete,
                        textButtonDescription = stringResource(id = R.string.delete_favorites_button_description)
                    ) {
                        detailComicViewModel.removeComicFromFavorites(
                            comic = detailComicViewModel.comic
                        )
                    }
                }

                false -> {
                    FloatingButton(
                        icon = Icons.Filled.Add,
                        textButtonDescription = stringResource(id = R.string.add_favorites_button_description)
                    ) {
                        detailComicViewModel.addComicToFavorites(
                            comic = detailComicViewModel.comic
                        )
                    }
                }
            }
        },

        topBar = {
            TopAppBar(
                title = stringResource(id = R.string.details_description),
                navToBackView = navToBackView
            )
        },
        content = { it ->

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                ComicDetailSection(
                    comic = detailComicViewModel.comic
                )
                Spacer(modifier = Modifier.height(8.dp))
                ComicCreator(listOfComicCreator = detailComicViewModel.creatorList)
                Spacer(modifier = Modifier.height(8.dp))
                ComicSuggestion(comic = detailComicViewModel.comic)

            }
        }
    )
}

/**
[ComicDetailSection] This is the section where the comic selected are show it, it name, descripton and image
[comic]: comic what are descripted in this section
This component is the secton where the detail of the comic in particular are show it

@author Usiel Garcia Jimenez
 **/
@Composable
fun ComicDetailSection(comic: Comic) {
    val scroll = rememberScrollState(0)

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = "Detalles del comic" ,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Image(
            painter = rememberAsyncImagePainter(comic.imageURL),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(300.dp)
                .fillMaxSize()

        )

        Text(
            text = stringResource(id = R.string.titulo_descripcion) + comic.title,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)
        )

        if (!comic.description?.trim().isNullOrEmpty()) {
            comic.description?.let {
                Text(
                    text = stringResource(id = R.string.description_title) + it,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .verticalScroll(scroll)
                        .padding(all = 16.dp)
                )
            }
        } else {
            Text(
                text = stringResource(id = R.string.description_title) + stringResource(id = R.string.comic_without_description),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(8.dp)

            )
        }

    }
}

/**
[ComicCreator] is the main view where the details of the creator of the comic are show it
[modifier]: is the modifier what we need to configurate and pass to this component
[border]: is the border in specific for this component
[shape] is the shape in specific for the image for the profile photo
[listOfComicCreator] list of the creators of the comic for can show their information
This component is for show the creator information of comic

@author Usiel Garcia Jimenez
 **/
@Composable
fun ComicCreator(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    listOfComicCreator: List<Creator>,
) {
    Card(
        shape = shape,
        border = border,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .height(72.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = CircleShape
                    )
                    .size(62.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        if (listOfComicCreator.isNullOrEmpty()) GENERIC_IMAGE_FOR_CREATORS_VIEW else listOfComicCreator[0].imageURL
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

            }
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = if (listOfComicCreator.isNullOrEmpty()) stringResource(id = R.string.no_information_creator) else stringResource(id = R.string.creator_title) + listOfComicCreator[0].nameCreator,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    }
}

/**
[ComicSuggestion] this is the section where we show it the others comic suggestion
[comic]: is the comic where the suggestion are contained
This component is for show the suggestion for the comic selected

@author Usiel Garcia Jimenez
 **/
@Composable
fun ComicSuggestion(comic: Comic) {
    if (!comic.variant.isNullOrEmpty()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(comic.variant) { item ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.comic_sugestion),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(8.dp)
                    )

                    Image(
                        painter = rememberAsyncImagePainter(
                            if (comic.imageURL.isNullOrEmpty()) GENERIC_IMAGE_FOR_VARIANTS_COMICS else
                                comic.imageURL
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxSize()
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    if (item != null) {
                        Text(
                            text = item.name,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(Color.Black)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    } else {
        Text(
            text = stringResource(id = R.string.no_comic_suggestion),
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)
        )
    }
}