package com.vinn.imagesliderapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vinn.imagesliderapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ImageViewModel by viewModels {
        ImageViewModelFactory(ImageRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
        observeViewModelState()
    }

    private fun setupClickListeners() {
        binding.buttonNext.setOnClickListener {
            viewModel.showNextImage()
        }
        binding.buttonPrevious.setOnClickListener {
            viewModel.showPreviousImage()
        }
        binding.buttonPlayPause.setOnClickListener {
            viewModel.toggleSlideshow()
        }
    }

    private fun observeViewModelState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    updateUi(state)
                }
            }
        }
    }

    private fun updateUi(state: ImageUiState) {
        val fadeOut = ObjectAnimator.ofFloat(binding.imageView, "alpha", 1f, 0f).apply {
            duration = 250
            interpolator = AccelerateDecelerateInterpolator()
        }

        fadeOut.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                binding.imageView.setImageResource(state.currentImageResId)
                ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f).apply {
                    duration = 250
                    interpolator = AccelerateDecelerateInterpolator()
                }.start()
            }
        })
        fadeOut.start()

        binding.imageTitle.text = state.currentImageTitle
        binding.imageDescription.text = state.currentImageDescription
        binding.imageCounter.text = getString(
            R.string.image_counter_format,
            state.currentIndex + 1,
            state.totalImages
        )

        binding.buttonNext.isEnabled = state.isNextEnabled
        binding.buttonPrevious.isEnabled = state.isPreviousEnabled

        val playPauseIcon = if (state.isPlaying) R.drawable.ic_pause else R.drawable.ic_play_arrow
        binding.buttonPlayPause.setImageResource(playPauseIcon)
    }
}
