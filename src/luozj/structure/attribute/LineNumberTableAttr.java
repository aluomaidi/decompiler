package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class LineNumberTableAttr extends AttributeInfo {
    private short length;
    private LineNumber[] table;

    public LineNumberTableAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static LineNumberTableAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        LineNumberTableAttr attr = new LineNumberTableAttr(attrName_index, attr_length, info);
        attr.length = stream.readShort();
        attr.table = new LineNumber[attr.length];
        for (short i = 0; i < attr.length; i++) {
            attr.table[i] = new LineNumber(stream.readShort(), stream.readShort());
        }
        return attr;
    }
    private static class LineNumber {
        private short start_pc;
        private short line_number;

        public LineNumber(short start_pc, short line_number) {
            this.start_pc = start_pc;
            this.line_number = line_number;
        }
    }
}
