package com.template

sealed class RepositoryException : Exception() {
    class SystemException() : RepositoryException()
}
