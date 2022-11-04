package com.micheldr.spendingtracker.view.element.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItem()
    fun reset()
}