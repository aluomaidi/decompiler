package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class IntegerStruct extends CPInfo {
    private final byte tag = 3;
    private int value;

    public IntegerStruct(int value) {
        this.value = value;
    }
    public byte getTag() {
        return tag;
    }

    public int getValue() {
        return value;
    }
}
