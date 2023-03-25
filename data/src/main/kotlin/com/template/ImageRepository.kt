package com.template

import android.graphics.Bitmap
import com.wsr.result.ApiResult

interface ImageRepository {
    suspend fun classify(bitmap: Bitmap): ApiResult<ClassifyResult, RepositoryException>
}
