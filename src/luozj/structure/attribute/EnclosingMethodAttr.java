package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class EnclosingMethodAttr extends AttributeInfo {
    private short class_index;
    private short method_index;
    public EnclosingMethodAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static EnclosingMethodAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        EnclosingMethodAttr attr = new EnclosingMethodAttr(attrName_index, attr_length, info);
        attr.class_index = stream.readShort();
        attr.method_index = stream.readShort();
        return attr;
    }
}
