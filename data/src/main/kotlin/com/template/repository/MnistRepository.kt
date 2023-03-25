package com.template.repository

import android.graphics.Bitmap
import com.template.RepositoryException
import com.wsr.result.ApiResult

interface MnistRepository {
    suspend fun classify(bitmap: Bitmap): ApiResult<ClassifyResult, RepositoryException>
}
