package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class MethodExceptionAttr extends AttributeInfo {
    private short number;
    private short[] indexs;

    public MethodExceptionAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static MethodExceptionAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        MethodExceptionAttr attr = new MethodExceptionAttr(attrName_index, attr_length, info);
        attr.number = stream.readShort();
        attr.indexs = new short[attr.number];
        for (short i = 0; i < attr.number; i++) {
            attr.indexs[i] = stream.readShort();
        }
        return attr;
    }
}
