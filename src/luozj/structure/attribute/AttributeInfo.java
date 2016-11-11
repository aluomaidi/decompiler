package luozj.structure.attribute;

/**
 * Created by luozj on 2016/11/9.
 */
public class AttributeInfo {

    private short attrName_index;
    private int attr_length;
    private byte[] info;

    public AttributeInfo(short attrName_index, int attr_length, byte[] info) {
        this.attrName_index = attrName_index;
        this.attr_length = attr_length;
        this.info = info;
    }

    public short getAttrName_index() {
        return attrName_index;
    }

    public void setAttrName_index(short attrName_index) {
        this.attrName_index = attrName_index;
    }

    public int getAttr_length() {
        return attr_length;
    }

    public void setAttr_length(int attr_length) {
        this.attr_length = attr_length;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }
}
