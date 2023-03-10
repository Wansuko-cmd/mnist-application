package com.template

sealed class DomainException : Exception() {
    class SystemException() : DomainException()
}
