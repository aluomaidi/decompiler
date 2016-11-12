package luozj.structure.attribute;

import luozj.parser.ClassParser;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/9.
 */
public class CodeAttr extends AttributeInfo {
    private short max_stack;
    private short max_locals;
    private int code_length;
    private byte[] code;
    private short exception_table_length;
    private CodeExceptionAttr[] exception_table;
    private short attr_count;
    private AttributeInfo[] attrs;

    public CodeAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }

    public static CodeAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream, ClassParser classParser) throws IOException {
        CodeAttr codeAttr = new CodeAttr(attrName_index, attr_length, info);
        codeAttr.max_stack = stream.readShort();
        codeAttr.max_locals = stream.readShort();
        codeAttr.code_length = stream.readInt();
        codeAttr.code = new byte[codeAttr.code_length];
        stream.read(codeAttr.code);
        codeAttr.exception_table_length = stream.readShort();
        codeAttr.exception_table = new CodeExceptionAttr[codeAttr.exception_table_length];
        for (short i = 0; i < codeAttr.exception_table_length; i++) {
            codeAttr.exception_table[i] = new CodeExceptionAttr(stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort());
        }
        codeAttr.attr_count = stream.readShort();
        codeAttr.attrs = new AttributeInfo[codeAttr.attr_count];
        for (short i = 0; i < codeAttr.attr_count; i++) {
            codeAttr.attrs[i] = classParser.parseAttr(stream);
        }
        return codeAttr;
    }


}
