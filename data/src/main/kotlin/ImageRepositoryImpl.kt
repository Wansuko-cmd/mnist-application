import com.wsr.di.IODispatcher
import com.wsr.result.ApiResult
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ImageRepository {
    override suspend fun classify(
        target: Image,
    ): ApiResult<ClassifyResult, DomainException> = withContext(dispatcher) {
        ClassifyResult(
            zero = 0.0f,
            one = 1.0f,
            two = 0.0f,
            three = 1.0f,
            four = 1.0f,
            five = 1.0f,
            six = 1.0f,
            seven = 1.0f,
            eight = 1.0f,
            nine = 1.0f,
        ).let { ApiResult.Success(it) }
    }
}