package com.template

import com.template.data.ml.MnistModel
import com.wsr.di.DefaultDispatcher
import com.wsr.di.IODispatcher
import com.wsr.result.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
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
            .outputFeature0AsTensorBuffer
            .floatArray
            .let { buffer ->
                ClassifyResult(
                    zero = buffer[0],
                    one = buffer[1],
                    two = buffer[2],
                    three = buffer[3],
                    four = buffer[4],
                    five = buffer[5],
                    six = buffer[6],
                    seven = buffer[7],
                    eight = buffer[8],
                    nine = buffer[9],
                )
            }.let { ApiResult.Success(it) }
    }

    private fun Image.toTensorBuffer() = TensorBuffer
        .createFixedSize(intArrayOf(1, 28, 28), DataType.FLOAT32)
        .also { buffer -> buffer.loadArray(this.pixels.toFloatArray()) }
}
