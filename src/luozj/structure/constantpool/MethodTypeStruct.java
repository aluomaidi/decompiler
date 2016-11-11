package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class MethodTypeStruct extends CPInfo {
    private final byte tag = 16;
    private short desc_index;

    public MethodTypeStruct(short desc_index) {
        this.desc_index = desc_index;
    }
    public byte getTag() {
        return tag;
    }

    public short getDesc_index() {
        return desc_index;
    }

    public void setDesc_index(short desc_index) {
        this.desc_index = desc_index;
    }

}
