package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class NameAndTypeStruct extends CPInfo {
    private final byte tag = 12;
    private short name_index;
    private short type_index;

    public NameAndTypeStruct(short name_index, short type_index) {
        this.name_index = name_index;
        this.type_index = type_index;
    }
    public byte getTag() {
        return tag;
    }

    public short getName_index() {
        return name_index;
    }

    public void setName_index(short name_index) {
        this.name_index = name_index;
    }

    public short getType_index() {
        return type_index;
    }

    public void setType_index(short type_index) {
        this.type_index = type_index;
    }

}
