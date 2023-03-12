package com.template

import javax.inject.Inject

class ClassifyImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(pixels: List<Float>) =
        imageRepository.classify(Image(pixels))
}
