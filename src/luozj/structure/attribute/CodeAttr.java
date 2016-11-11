package luozj.structure.attribute;

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

    public short getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(short max_stack) {
        this.max_stack = max_stack;
    }

    public short getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(short max_locals) {
        this.max_locals = max_locals;
    }

    public int getCode_length() {
        return code_length;
    }

    public void setCode_length(int code_length) {
        this.code_length = code_length;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public short getException_table_length() {
        return exception_table_length;
    }

    public void setException_table_length(short exception_table_length) {
        this.exception_table_length = exception_table_length;
    }

    public CodeExceptionAttr[] getException_table() {
        return exception_table;
    }

    public void setException_table(CodeExceptionAttr[] exception_table) {
        this.exception_table = exception_table;
    }

    public short getAttr_count() {
        return attr_count;
    }

    public void setAttr_count(short attr_count) {
        this.attr_count = attr_count;
    }

    public AttributeInfo[] getAttrs() {
        return attrs;
    }

    public void setAttrs(AttributeInfo[] attrs) {
        this.attrs = attrs;
    }
}
