package com.template

import com.template.data.ml.MnistModel
import com.wsr.di.DefaultDispatcher
import com.wsr.result.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val mnistModel: MnistModel,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ImageRepository {

    override suspend fun classify(
        target: Image,
    ): ApiResult<ClassifyResult, DomainException> = withContext(dispatcher) {
        mnistModel
            .process(target.toTensorBuffer())
            .outputFeature0AsTensorBuffer.floatArray
            .toClassifyResult()
            .let { ApiResult.Success(it) }
    }

    private fun Image.toTensorBuffer() = TensorBuffer
        .createFixedSize(intArrayOf(1, 28, 28), DataType.FLOAT32)
        .also { buffer -> buffer.loadArray(this.pixels.toFloatArray()) }

    private fun FloatArray.toClassifyResult() =
        ClassifyResult(
            zero = this[0],
            one = this[1],
            two = this[2],
            three = this[3],
            four = this[4],
            five = this[5],
            six = this[6],
            seven = this[7],
            eight = this[8],
            nine = this[9],
        )
}
