package com.template

sealed class RepositoryException : Exception() {
    class SystemException(override val cause: Exception) : RepositoryException()
}
