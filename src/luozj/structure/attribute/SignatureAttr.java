package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 16/11/12.
 */
public class SignatureAttr extends AttributeInfo {

    private short signature_index;

    public static SignatureAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        SignatureAttr attr = new SignatureAttr(attrName_index, attr_length, info);
        attr.signature_index = stream.readShort();
        return attr;
    }
    public SignatureAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
}
