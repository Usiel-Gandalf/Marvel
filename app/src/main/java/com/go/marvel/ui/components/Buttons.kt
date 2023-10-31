package com.go.marvel.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
[FloatingButton] Button what we reused in differents views, this button floating in the bottom of the screen
[icon] icon what we passed for show in the button for be more descriptive
[textButtonDescription] is te text what appear in the floating button with the icon for be more informative
[actionFloatButton] high order function what make somethig when we clicked the button
Floating button for uses in differents views

@author Usiel Garcia Jimenez
 **/
@Composable
fun FloatingButton(
    icon: ImageVector? = null,
    textButtonDescription: String? = null,
    actionFloatButton: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(16.dp),
        icon = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        text = {
            textButtonDescription?.let {
                Text(text = textButtonDescription)
            }

        },
        onClick = { actionFloatButton.invoke() }
    )
}
