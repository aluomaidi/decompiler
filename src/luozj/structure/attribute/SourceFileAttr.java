package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 16/11/12.
 */
public class SourceFileAttr extends AttributeInfo {

    private short sourcefile_index;

    public static SourceFileAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        SourceFileAttr attr = new SourceFileAttr(attrName_index, attr_length, info);
        attr.sourcefile_index = stream.readShort();
        return attr;
    }
    public SourceFileAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
}
