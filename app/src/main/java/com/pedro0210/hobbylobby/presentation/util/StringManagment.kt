package com.pedro0210.hobbylobby.presentation.util

//Dude, it's just a funciton that cuts the string LMAO
fun stringCutter(string: String, length: Int): String {
    if (string.length >= length) {
        return string.substring(0, length) + "..."
    }
    return string
}

//TODO: temporal
fun getRandomString(length: Int): String {
    val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return List(length) { chars.random() }.joinToString("")
}
