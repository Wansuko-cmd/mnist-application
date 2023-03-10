class Image(val pixels: List<Float>) {
    init {
        assert(pixels.size == 28 * 28)
    }
}
