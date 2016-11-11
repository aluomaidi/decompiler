package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/7.
 */
public class ClassStruct extends CPInfo {
    private final byte tag = 7;
    private short index;

    public ClassStruct(short index) {
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
