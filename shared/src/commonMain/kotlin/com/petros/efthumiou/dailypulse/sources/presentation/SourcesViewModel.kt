package com.petros.efthumiou.dailypulse.sources.presentation
import com.petros.efthumiou.dailypulse.BaseViewModel
import com.petros.efthumiou.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val sourcesUseCase: SourcesUseCase
) : BaseViewModel() {

    private var _sourcesState: MutableStateFlow<SourcesState> = MutableStateFlow(SourcesState(loading = true))
    val state: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false) {
        scope.launch {
            _sourcesState.emit(SourcesState(loading = true, sources = _sourcesState.value.sources))
            try {
                val sources = sourcesUseCase.getSources(forceFetch)
                _sourcesState.emit(SourcesState(sources = sources))
            } catch (e: Exception) {
                _sourcesState.emit(SourcesState(error = e.message))
            }
        }
    }
}