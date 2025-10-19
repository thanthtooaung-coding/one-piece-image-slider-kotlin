package com.vinn.imagesliderapplication

data class ImageUiState(
    val currentImageResId: Int,
    val currentImageTitle: String,
    val currentImageDescription: String,
    val currentIndex: Int,
    val totalImages: Int,
    val isPreviousEnabled: Boolean,
    val isNextEnabled: Boolean,
    val isPlaying: Boolean = false
)
