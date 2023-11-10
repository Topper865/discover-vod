package dev.sajidali.gflix.utils

fun <T> listOf(size: Int, block: (position: Int) -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until size) {
        list.add(block(i))
    }
    return list
}