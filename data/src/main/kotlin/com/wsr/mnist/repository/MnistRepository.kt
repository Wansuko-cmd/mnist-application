package com.wsr.mnist.repository

import android.graphics.Bitmap
import com.wsr.mnist.RepositoryException
import com.wsr.result.ApiResult

interface MnistRepository {
    suspend fun classify(bitmap: Bitmap): ApiResult<ClassifyResult, RepositoryException>
}
