package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class LongStruct extends CPInfo {
    private final byte tag = 5;
    private long value;

    public LongStruct(long value) {
        this.value = value;
    }
    public byte getTag() {
        return tag;
    }

    public long getValue() {
        return value;
    }
}
