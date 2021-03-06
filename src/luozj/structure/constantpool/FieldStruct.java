package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/7.
 */
public class FieldStruct extends CPInfo {
    private final byte tag = 9;
    private short classInfo_index;
    private short nameAndType_index;

    public FieldStruct(short classInfo_index, short nameAndType_index) {
        this.classInfo_index = classInfo_index;
        this.nameAndType_index = nameAndType_index;
    }
    public byte getTag() {
        return tag;
    }

    public short getClassInfo_index() {
        return classInfo_index;
    }

    public void setClassInfo_index(short classInfo_index) {
        this.classInfo_index = classInfo_index;
    }

    public short getNameAndType_index() {
        return nameAndType_index;
    }

    public void setNameAndType_index(short nameAndType_index) {
        this.nameAndType_index = nameAndType_index;
    }
}
