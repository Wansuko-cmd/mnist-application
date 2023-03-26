package com.template

sealed class RepositoryException : Exception() {
    class SystemException(
        override val message: String? = null,
        override val cause: Exception? = null,
    ) : RepositoryException()
}
