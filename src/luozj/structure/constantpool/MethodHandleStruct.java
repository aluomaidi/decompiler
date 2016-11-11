package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class MethodHandleStruct extends CPInfo {
    private final byte tag = 15;
    private byte ref_kind;
    private short ref_index;

    public MethodHandleStruct(byte ref_kind, short ref_index) {
        this.ref_kind = ref_kind;
        this.ref_index = ref_index;
    }
    public byte getTag() {
        return tag;
    }

    public byte getRef_kind() {
        return ref_kind;
    }

    public void setRef_kind(byte ref_kind) {
        this.ref_kind = ref_kind;
    }

    public short getRef_index() {
        return ref_index;
    }

    public void setRef_index(short ref_index) {
        this.ref_index = ref_index;
    }
}
