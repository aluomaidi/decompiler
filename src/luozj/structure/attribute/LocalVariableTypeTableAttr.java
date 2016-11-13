package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 16/11/12.
 */
public class LocalVariableTypeTableAttr extends AttributeInfo {

    private short length;
    private LocalVariableType[] table;

    public static LocalVariableTypeTableAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        LocalVariableTypeTableAttr attr = new LocalVariableTypeTableAttr(attrName_index, attr_length, info);
        attr.length = stream.readShort();
        attr.table = new LocalVariableType[attr.length];
        for (short i = 0; i < attr.length; i++) {
            attr.table[i] = new LocalVariableType(stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort());
        }
        return attr;
    }
    public LocalVariableTypeTableAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }

    private static class LocalVariableType {
        private short start_pc;
        private short length;
        private short name_index;
        private short signature_index;
        private short index;

        public LocalVariableType(short start_pc, short length, short name_index, short signature_index, short index) {
            this.start_pc = start_pc;
            this.length = length;
            this.name_index = name_index;
            this.signature_index = signature_index;
            this.index = index;
        }
    }
}
