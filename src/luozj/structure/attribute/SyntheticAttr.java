package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 16/11/12.
 */
public class SyntheticAttr extends AttributeInfo {


    public static SyntheticAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        return new SyntheticAttr(attrName_index, attr_length, info);
    }
    public SyntheticAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
}
