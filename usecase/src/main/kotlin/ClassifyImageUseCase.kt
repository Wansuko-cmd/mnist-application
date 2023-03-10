class ClassifyImageUseCase(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(image: Image) = imageRepository.classify(image)
}
