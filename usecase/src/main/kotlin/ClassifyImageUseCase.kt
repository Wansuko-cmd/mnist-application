import javax.inject.Inject

class ClassifyImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(image: Image) = imageRepository.classify(image)
}
