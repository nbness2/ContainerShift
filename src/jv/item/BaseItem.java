package jv.item;

public interface BaseItem {
    short getItemId();
    int getItemAmount();
    default boolean isInvalidItem() {
        if (this instanceof INVALID_ITEM) return true;
        return getItemId() < 0;
    }
}
