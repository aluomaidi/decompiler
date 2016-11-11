package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class LocalVariableTableAttr extends AttributeInfo {
    private short length;
    private LocalVariable[] table;

    public LocalVariableTableAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static LocalVariableTableAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        LocalVariableTableAttr attr = new LocalVariableTableAttr(attrName_index, attr_length, info);
        attr.length = stream.readShort();
        attr.table = new LocalVariable[attr.length];
        for (short i = 0; i < attr.length; i++) {
            attr.table[i] = new LocalVariable(stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort());
        }
        return attr;
    }

    private static class LocalVariable {
        private short start_pc;
        private short length;
        private short name_index;
        private short descriptor_index;
        private short index;

        public LocalVariable(short start_pc, short length, short name_index, short descriptor_index, short index) {
            this.start_pc = start_pc;
            this.length = length;
            this.name_index = name_index;
            this.descriptor_index = descriptor_index;
            this.index = index;
        }
    }
}
