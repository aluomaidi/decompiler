package luozj.structure.attribute;

/**
 * Created by luozj on 2016/11/9.
 */
public class CodeExceptionAttr {
    private short start_pc;
    private short end_pc;
    private short handler_pc;
    private short catch_type;

    public CodeExceptionAttr(short start_pc, short end_pc, short handler_pc, short catch_type) {
        this.start_pc = start_pc;
        this.end_pc = end_pc;
        this.handler_pc = handler_pc;
        this.catch_type = catch_type;
    }

    public short getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(short start_pc) {
        this.start_pc = start_pc;
    }

    public short getEnd_pc() {
        return end_pc;
    }

    public void setEnd_pc(short end_pc) {
        this.end_pc = end_pc;
    }

    public short getHandler_pc() {
        return handler_pc;
    }

    public void setHandler_pc(short handler_pc) {
        this.handler_pc = handler_pc;
    }

    public short getCatch_type() {
        return catch_type;
    }

    public void setCatch_type(short catch_type) {
        this.catch_type = catch_type;
    }
}
