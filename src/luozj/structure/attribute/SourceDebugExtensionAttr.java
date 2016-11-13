package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 16/11/12.
 */
public class SourceDebugExtensionAttr extends AttributeInfo {

    private byte[] debug_extension;

    public static SourceDebugExtensionAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        SourceDebugExtensionAttr attr = new SourceDebugExtensionAttr(attrName_index, attr_length, info);
        attr.debug_extension = new byte[attr_length];
        stream.read(attr.debug_extension);
        return attr;
    }
    public SourceDebugExtensionAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
}
