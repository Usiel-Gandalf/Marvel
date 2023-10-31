package com.go.marvel.utils

class BuildConfig {
    companion object {

        // GENERIC API FOR GET COMIC
        const val GENERIC_API_CREDENTIALS = "ts=1689045771781&apikey=0a5545b0111527363a28a64f53b7002f&hash=946aef0d7a1eb9e8f92b0278945bc192"

        //GENERIC IMAGE FOR COMICS
        const val GENERIC_IMAGE_FOR_COMICS = "https://wallpaperaccess.com/full/4834549.jpg"

        //GENERIC IMAGE FOR CREATORS
        const val GENERIC_IMAGE_FOR_CREATORS = "https://m.media-amazon.com/images/I/71vntClRfjL._AC_UF1000,1000_QL80_.jpg"

        //GENERIC IMAGE FOR INDIVIDUAL COMICS
        const val GENERIC_IMAGE_FOR_INDIVIDUAL_COMICS = "https://wallpaperaccess.com/full/4834549.jpg"

        //GENERIC IMAGE FOR CREATORS
        const val GENERIC_IMAGE_FOR_CREATORS_VIEW = "https://images6.alphacoders.com/488/thumb-1920-488158.jpg"

        //GENERIC IMAGE FOR VARIANTS COMICS
        const val GENERIC_IMAGE_FOR_VARIANTS_COMICS = "https://wallpaperaccess.com/full/4834549.jpg"

        //MAIN API URL BASE
        const val API_BASE_URL = "https://gateway.marvel.com:443/v1/public/"

        // NAME DATABASE
        const val DATABASE_NAME = "comic_database"

        // NAME MAIN TABLE OF DATABASE
        const val MAIN_TABLE_NAME = "ComicEntity"

        //ERROR MESSAGE
        const val ERROR_DATABASE_MESSAGE = "ERROR"

        //NO TITLE REFACTOR
        const val NO_TITLE_MESSAGE = "NO TITLE"

        // MAIN NAVIGATION ROUTE
        const val MAIN_NAVIGATION_ROUTE = "MAIN"

        // DETAIL LIST NAVIGATION ROUTE
        const val DETAIL_LIST_NAVIGATION_ROUTE = "DETAIL_LIST"

        // FAVORITES NAVIGATION ROUTE
        const val FAVORITES_NAVIGATION_ROUTE = "FAVORITES_COMIC"
    }
}