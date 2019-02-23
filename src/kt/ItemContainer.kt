package kt

class ItemContainer(wrapped: Array<BaseItem>) {

    val wrapped = wrapped.copyOf()

    /**
     * This is essentially equal speed until around 500 items in container
     */
    fun memoryFriendlyShift() {
        if (wrapped.isEmpty() || wrapped.size == 1) return
        var nextSlotToFill = -1
        var currentSlot = 0
        var temporaryItem: BaseItem
        while (currentSlot < wrapped.size) {
            temporaryItem = wrapped[currentSlot]
            if (temporaryItem.isInvalidItem) {
                if (nextSlotToFill < 0) {
                    nextSlotToFill = currentSlot
                }
                currentSlot++
                continue
            }
            if (nextSlotToFill >= 0) {
                wrapped[nextSlotToFill] = wrapped[currentSlot]
                wrapped[currentSlot] = INVALID_ITEM
                currentSlot = nextSlotToFill + 1
                nextSlotToFill = -1
                continue
            }
            currentSlot ++
        }
    }

    fun fastShift() {
        if (wrapped.isEmpty() || wrapped.size == 1) return
        wrapped
            .partitionJoin { !it.isInvalidItem }
            .forEachIndexed { slotIndex, item -> wrapped[slotIndex] = item }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemContainer

        if (!wrapped.contentEquals(other.wrapped)) return false

        return true
    }

    override fun hashCode(): Int = wrapped.contentHashCode()

    override fun toString() = wrapped.contentToString()
}