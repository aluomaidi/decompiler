package luozj.structure.field;

import luozj.structure.attribute.AttributeInfo;

/**
 * Created by luozj on 2016/11/9.
 */
public class FieldInfo {
    private short access_flags;
    private short name_index;
    private short desc_index;
    private short attr_count;
    private AttributeInfo[] attrs;

    public FieldInfo(short access_flags, short name_index, short desc_index, short attr_count) {
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.desc_index = desc_index;
        this.attr_count = attr_count;
    }

    public short getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(short access_flags) {
        this.access_flags = access_flags;
    }

    public short getName_index() {
        return name_index;
    }

    public void setName_index(short name_index) {
        this.name_index = name_index;
    }

    public short getDesc_index() {
        return desc_index;
    }

    public void setDesc_index(short desc_index) {
        this.desc_index = desc_index;
    }

    public short getAttr_count() {
        return attr_count;
    }

    public void setAttr_count(short attr_count) {
        this.attr_count = attr_count;
    }

    public AttributeInfo[] getAttrs() {
        return attrs;
    }

    public void setAttrs(AttributeInfo[] attrs) {
        this.attrs = attrs;
    }
}
