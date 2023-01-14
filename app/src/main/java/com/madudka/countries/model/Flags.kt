package com.madudka.countries.model

data class Flags(
    val png: String,
    @Transient private val svg: String
)