package com.template

import android.graphics.Bitmap
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
        mnistModel
            .process(bitmap.toTensorBuffer())
            .outputFeature0AsTensorBuffer.floatArray
            .toClassifyResult()
            .let { ApiResult.Success(it) }
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
