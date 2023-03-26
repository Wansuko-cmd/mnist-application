package com.wsr.mnist.repository

import com.wsr.mnist.RepositoryException

data class ClassifyResult(
    val zero: Float,
    val one: Float,
    val two: Float,
    val three: Float,
    val four: Float,
    val five: Float,
    val six: Float,
    val seven: Float,
    val eight: Float,
    val nine: Float,
) {
    fun max(): String =
        listOf(
            zero,
            one,
            two,
            three,
            four,
            five,
            six,
            seven,
            eight,
            nine,
        )
            .maxIndex()
            .toString()
}

private fun <T : Comparable<T>> List<T>.maxIndex(): Int =
    this.foldIndexed(null) { index: Int, acc: Pair<Int, T>?, element: T ->
        when {
            acc == null -> index to element
            acc.second > element -> acc
            else -> index to element
        }
    }?.first ?: throw RepositoryException.SystemException("maxIndex error")
