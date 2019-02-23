package jv;

import java.util.Arrays;
import jv.item.BaseItem;
import jv.item.INVALID_ITEM;

import static jv.ArrayFunctional.*;

public class ItemContainer {
    public final BaseItem[] wrapped;
    public ItemContainer(BaseItem[] wrapped) {
        this.wrapped = new BaseItem[wrapped.length];
        System.arraycopy(wrapped, 0, this.wrapped, 0, wrapped.length);
    }

    public final void fastShift() {
        if (wrapped.length == 0 || wrapped.length == 1) return;
        forEachIndexed(
                partitionJoin(this.wrapped, baseItem -> !baseItem.isInvalidItem()),
                (index, item) -> this.wrapped[index] = item);
    }

    public final void memoryFriendlyShift() {
        if (wrapped.length == 0 || wrapped.length == 1) return;
        int nextSlotToFill = -1;
        int currentSlot = 0;
        BaseItem temporaryItem;
        while (currentSlot < wrapped.length) {
            temporaryItem = wrapped[currentSlot];
            if (temporaryItem.isInvalidItem()) {
                if (nextSlotToFill < 0) {
                    nextSlotToFill = currentSlot;
                }
                currentSlot++;
                continue;
            }
            if (nextSlotToFill >= 0) {
                wrapped[nextSlotToFill] = wrapped[currentSlot];
                wrapped[currentSlot] = INVALID_ITEM.getInstance();
                currentSlot = nextSlotToFill + 1;
                nextSlotToFill = -1;
                continue;
            }
            currentSlot ++;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ItemContainer)) return false;
        return Arrays.equals(wrapped, ((ItemContainer) obj).wrapped);
    }

    @Override
    public String toString() { return Arrays.toString(wrapped); }
}
