package jv.item;

import java.util.Random;

public class Item implements BaseItem {
    private static Random itemRandom = new Random();
    public static BaseItem[] randomItemArray(int arraySize) {
        final BaseItem[] finalArray = new BaseItem[arraySize];
        for (int i = 0; i < arraySize; i++) {
            finalArray[i] = itemRandom.nextBoolean() ?
                    new Item((short )itemRandom.nextInt(15_000), itemRandom.nextInt(250_000) + 1)
                    : INVALID_ITEM.getInstance();
        }
        return finalArray;
    }

    private final short itemId;
    private final int itemAmount;

    public Item(short itemId, int itemAmount) {
        this.itemId = itemId;
        this.itemAmount = itemAmount;
    }

    public short getItemId() { return itemId; }
    public int getItemAmount() { return this.itemAmount; }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BaseItem)) return false;
        return (this.itemId == ((BaseItem) obj).getItemId()) && (this.itemAmount == ((BaseItem) obj).getItemAmount());
    }

    @Override
    public String toString() { return "Item(" + itemId + ", " + itemAmount + ")"; }
}
