package jv.item;

public class INVALID_ITEM implements BaseItem {
    private static final INVALID_ITEM instance = new INVALID_ITEM();
    public static INVALID_ITEM getInstance() { return instance; }
    private INVALID_ITEM() {}
    public short getItemId() { return -1; }
    public int getItemAmount() { return -1; }
    public String toString() { return "INVALID_ITEM"; }
}
