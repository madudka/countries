package com.madudka.countries.model

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
){
    override fun toString() = "$symbol - $code ($name)"
}