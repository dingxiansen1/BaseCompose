package com.dd.base.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow

fun <T : Any> ViewModel.simplePager(
    config: AppPagingConfig = AppPagingConfig(),
    callAction: suspend (page: Int) -> List<T>
): Flow<PagingData<T>> {
    return pager(config, 0) {
        val page = it.key ?: 0
        val response = try {
            HttpResult.Success(callAction.invoke(page))
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
        when (response) {
            is HttpResult.Success -> {
                try {
                    val data = response.result
                    PagingSource.LoadResult.Page(
                        data = response.result,
                        prevKey = if (page == 0) null else page.minus(1),
                        nextKey = if (data.isEmpty()) null else page.plus(1)
                    )
                }catch (e: Exception){
                    PagingSource.LoadResult.Error(e)
                }

            }
            is HttpResult.Error -> {
                PagingSource.LoadResult.Error(response.exception)
            }
        }
    }
}

fun <K : Any, V : Any> ViewModel.pager(
    config: AppPagingConfig = AppPagingConfig(),
    initialKey: K? = null,
    loadData: suspend (PagingSource.LoadParams<K>) -> PagingSource.LoadResult<K, V>
): Flow<PagingData<V>> {
    val baseConfig = PagingConfig(
        config.pageSize,
        initialLoadSize = config.initialLoadSize,
        prefetchDistance = config.prefetchDistance,
        maxSize = config.maxSize,
        enablePlaceholders = config.enablePlaceholders
    )
    return Pager(
        config = baseConfig,
        initialKey = initialKey
    ) {
        object : PagingSource<K, V>() {
            override suspend fun load(params: LoadParams<K>): LoadResult<K, V> {
                return loadData.invoke(params)
            }

            override fun getRefreshKey(state: PagingState<K, V>): K? {
                return initialKey
            }

        }
    }.flow.cachedIn(viewModelScope)
}