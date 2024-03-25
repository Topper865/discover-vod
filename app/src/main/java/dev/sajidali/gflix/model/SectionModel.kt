package dev.sajidali.gflix.model

data class SectionModel<T : Any>(val title: String, val content: List<T>)
