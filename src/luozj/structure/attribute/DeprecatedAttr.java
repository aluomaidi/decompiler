package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class DeprecatedAttr extends AttributeInfo {
    public DeprecatedAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static DeprecatedAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        return new DeprecatedAttr(attrName_index, attr_length, info);
    }
}
