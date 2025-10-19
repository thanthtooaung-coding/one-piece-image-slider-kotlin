package com.vinn.imagesliderapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ImageViewModel(repository: ImageRepository) : ViewModel() {

    private val images: List<ImageItem> = repository.getImages()

    private var slideshowJob: Job? = null

    private val _uiState = MutableStateFlow(
        ImageUiState(
            currentImageResId = images.first().id,
            currentImageTitle = images.first().title,
            currentImageDescription = images.first().description,
            currentIndex = 0,
            totalImages = images.size,
            isPreviousEnabled = images.size > 1,
            isNextEnabled = images.size > 1
        )
    )

    val uiState: StateFlow<ImageUiState> = _uiState.asStateFlow()

    fun showNextImage() {
        pauseSlideshow()
        val nextIndex = (uiState.value.currentIndex + 1) % images.size
        updateStateForIndex(nextIndex)
    }

    fun showPreviousImage() {
        pauseSlideshow()
        val prevIndex = if (uiState.value.currentIndex == 0) {
            images.size - 1
        } else {
            uiState.value.currentIndex - 1
        }
        updateStateForIndex(prevIndex)
    }

    fun toggleSlideshow() {
        if (_uiState.value.isPlaying) {
            pauseSlideshow()
        } else {
            startSlideshow()
        }
    }

    private fun startSlideshow() {
        slideshowJob?.cancel()
        slideshowJob = viewModelScope.launch {
            _uiState.update { it.copy(isPlaying = true) }
            while (isActive) {
                delay(3000)
                val nextIndex = (_uiState.value.currentIndex + 1) % images.size
                updateStateForIndex(nextIndex)
            }
        }
    }

    private fun pauseSlideshow() {
        slideshowJob?.cancel()
        if (_uiState.value.isPlaying) {
            _uiState.update { it.copy(isPlaying = false) }
        }
    }

    private fun updateStateForIndex(newIndex: Int) {
        val imageItem = images[newIndex]
        _uiState.update { currentState ->
            currentState.copy(
                currentIndex = newIndex,
                currentImageResId = imageItem.id,
                currentImageTitle = imageItem.title,
                currentImageDescription = imageItem.description,
                isPreviousEnabled = images.size > 1,
                isNextEnabled = images.size > 1
            )
        }
    }
}
