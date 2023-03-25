package com.template.repository

import android.graphics.Bitmap
import com.template.RepositoryException
import com.template.data.ml.MnistModel
import com.wsr.di.DefaultDispatcher
import com.wsr.result.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val mnistModel: MnistModel,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ImageRepository {

    override suspend fun classify(
        bitmap: Bitmap,
    ): ApiResult<ClassifyResult, RepositoryException> = withContext(dispatcher) {
        try {
            mnistModel
                .process(bitmap.toTensorBuffer())
                .outputFeature0AsTensorBuffer
                .toClassifyResult()
                .let { ApiResult.Success(it) }
        } catch (e: Exception) {
            ApiResult.Failure(RepositoryException.SystemException(e))
        }
    }
}

private fun Bitmap.toTensorBuffer(): TensorBuffer =
    this
        .toByteBuffer()
        .toByteArray()
        .map { it.toFloat() }
        .reversedBit()
        .toTensorBuffer()

private fun Bitmap.toByteBuffer(): ByteBuffer {
    val buffer = ByteBuffer.allocate(28 * 28)
    Bitmap.createScaledBitmap(
        this,
        28,
        28,
        true,
    )
        .copyPixelsToBuffer(buffer)
    return buffer
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind() // 最初のバイトの一に戻る
    val data = ByteArray(remaining())
    get(data) // 最後まで読み取り
    return data
}

private fun List<Float>.reversedBit() = this.map { if (it >= 0) 0.0f else 1.0f }

private fun List<Float>.toTensorBuffer() = TensorBuffer
    .createFixedSize(intArrayOf(1, 28, 28), DataType.FLOAT32)
    .also { buffer -> buffer.loadArray(this.toFloatArray()) }

private fun TensorBuffer.toClassifyResult() =
    this.floatArray.let { buffer ->
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
    }
