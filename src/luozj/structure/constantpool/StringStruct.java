package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class StringStruct extends CPInfo {
    private final byte tag = 8;
    private short index;

    public StringStruct(short index) {
        this.index = index;
    }
    public byte getTag() {
        return tag;
    }
    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }
}
