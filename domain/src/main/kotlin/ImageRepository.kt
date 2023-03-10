import com.wsr.result.ApiResult

interface ImageRepository {
    suspend fun classify(target: Image): ApiResult<ClassifyResult, DomainException>
}
