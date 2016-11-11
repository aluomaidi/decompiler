package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class ConstantValueAttr extends AttributeInfo {
    private short constantvalue_index;

    public ConstantValueAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }

    public static ConstantValueAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        ConstantValueAttr attr = new ConstantValueAttr(attrName_index, attr_length, info);
        attr.constantvalue_index = stream.readShort();
        return attr;
    }
    public void setConstantvalue_index(short constantvalue_index) {
        this.constantvalue_index = constantvalue_index;
    }
}
