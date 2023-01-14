package com.madudka.countries.view.adapter

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}