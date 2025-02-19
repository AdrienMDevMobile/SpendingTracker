package com.micheldr.spendingtracker.view.element.paginator

class PaginatorImpl<Key, Item>(
    initalKey:Key,
    private  val onLoadUpdated : (Boolean) -> Unit,
    private  val onRequest: suspend (nextKey:Key) -> Result<List<Item>>,
    private  val getNextKey: suspend (List<Item>) -> Key,
    private  val onError: suspend (Throwable?) -> Unit,
    private  val onSuccess: suspend (items:List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initalKey
    private var isMakingRequest = false

    override suspend fun loadNextItem() {
        if(isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}