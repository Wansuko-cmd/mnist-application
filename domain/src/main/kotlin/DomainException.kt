sealed class DomainException : Exception() {
    class SystemException() : DomainException()
}
