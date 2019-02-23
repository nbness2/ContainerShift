package kt

import kotlin.random.Random

interface BaseItem {
    val itemId: Short
    val itemAmount: Int
    val isInvalidItem: Boolean
        get() = this is INVALID_ITEM || (itemId.toInt() == -1)
}

data class Item(override val itemId: Short, override val itemAmount: Int): BaseItem

object INVALID_ITEM: BaseItem {
    override val itemId: Short = -1
    override val itemAmount: Int = 0
    override fun toString() = "INVALID_ITEM"
}

fun randomItemArray(size: Int, maxId: Int = 15_000, maxAmount: Int = 250_000): Array<BaseItem> = Array(size) {
    if (Random.nextBoolean())
        Item(Random.nextInt(0, maxId).toShort(), Random.nextInt(1, maxAmount))
    else
        INVALID_ITEM
}