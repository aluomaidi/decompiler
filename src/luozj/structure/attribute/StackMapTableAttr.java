package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class StackMapTableAttr extends AttributeInfo {
    private short num;
    private StackMapFrame[] entries;

    public StackMapTableAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static StackMapTableAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        StackMapTableAttr attr = new StackMapTableAttr(attrName_index, attr_length, info);
        attr.num = stream.readByte();
        attr.entries = new StackMapFrame[attr.num];
        for (short i = 0; i < attr.num; i++) {
            attr.entries[i] = parseStackMapFrame(stream);
        }
        return attr;
    }
    private static StackMapFrame parseStackMapFrame(DataInputStream stream) throws IOException {
        StackMapFrame frame = null;
        byte frame_type = stream.readByte();
        if (frame_type >= 0 && frame_type <= 63) {
            frame = new SameFrame(frame_type);
        } else if (frame_type >= 64 && frame_type <= 127) {
            frame = new SameLocals1StackItemFrame(frame_type, parseVerificationTypeInfo(stream));
        } else if (frame_type == 247) {
            frame = new SameLocals1StackItemFrameExtended(frame_type, stream.readShort(), parseVerificationTypeInfo(stream));
        } else if (frame_type >= 248 && frame_type <= 250) {
            frame = new ChopFrame(frame_type, stream.readShort());
        } else if (frame_type == 251) {
            frame = new SameFrameExtended(frame_type, stream.readShort());
        }  else if (frame_type >= 252 && frame_type <= 254) {
            short offset_delta = stream.readShort();
            int length = frame_type - 251;
            frame = new AppendFrame(frame_type, offset_delta, parseVerificationTypeInfos(length, stream));
        } else if (frame_type == 255) {
            short offset_delta = stream.readShort();
            short number_of_locals  = stream.readShort();
            VerificationTypeInfo[] locals = parseVerificationTypeInfos(number_of_locals, stream);
            short number_of_stack_items  = stream.readShort();
            VerificationTypeInfo[] stack = parseVerificationTypeInfos(number_of_stack_items, stream);
            frame = new FullFrame(frame_type, offset_delta, number_of_locals, locals, number_of_stack_items, stack);
        }
        return frame;
    }
    private static VerificationTypeInfo[] parseVerificationTypeInfos(int length, DataInputStream stream) throws IOException {
        VerificationTypeInfo[] verificationTypeInfos = new VerificationTypeInfo[length];
        for (int i = 0;i < length ;i++) {
            verificationTypeInfos[i] = parseVerificationTypeInfo(stream);
        }
        return verificationTypeInfos;
    }
    private static VerificationTypeInfo parseVerificationTypeInfo(DataInputStream stream) throws IOException {
        byte tag = stream.readByte();
        switch (tag) {
            case 0 :
                return new TopVariableInfo(tag);
            case 1 :
                return new IntegerVariableInfo(tag);
            case 2 :
                return new FloatVariableVnfo(tag);
            case 3 :
                return new DoubleVariableInfo(tag);
            case 4 :
                return new LongVariableInfo(tag);
            case 5 :
                return new NullVariableInfo(tag);
            case 6 :
                return new UninitializedThisVariableInfo(tag);
            case 7 :
                return new ObjectVariableInfo(tag, stream.readShort());
            case 8 :
                return new UninitializedVariableInfo(tag, stream.readShort());
            default:
                return null;
        }
    }
    private static class StackMapFrame {
        protected byte frame_type;

        public StackMapFrame(byte frame_type) {
            this.frame_type = frame_type;
        }
    }
    private static class SameFrame extends StackMapFrame {
        /* 0-63 */
        public SameFrame(byte frame_type) {
            super(frame_type);
        }
    }
    private static class SameLocals1StackItemFrame extends StackMapFrame {
       /* 64-127 */
        private VerificationTypeInfo[] stack =  new VerificationTypeInfo[1];
        public SameLocals1StackItemFrame(byte frame_type, VerificationTypeInfo verification_type_info) {
            super(frame_type);
            stack[0] = verification_type_info;
        }
    }
    private static class SameLocals1StackItemFrameExtended extends StackMapFrame {
        /* 247 */
        private short offset_delta;
        private VerificationTypeInfo[] stack = new VerificationTypeInfo[1];
        public SameLocals1StackItemFrameExtended(byte frame_type, short offset_delta, VerificationTypeInfo verification_type_info) {
            super(frame_type);
            this.offset_delta = offset_delta;
            stack[0] = verification_type_info;
        }
    }
    private static class ChopFrame extends StackMapFrame {
        /* 248-250 */
        private short offset_delta;
        public ChopFrame(byte frame_type, short offset_delta) {
            super(frame_type);
            this.offset_delta = offset_delta;
        }
    }
    private static class SameFrameExtended extends StackMapFrame {
        /* 251 */
        private short offset_delta;
        public SameFrameExtended(byte frame_type, short offset_delta) {
            super(frame_type);
            this.offset_delta = offset_delta;
        }
    }
    private static class AppendFrame extends StackMapFrame {
        /* 252-254 */
        private short offset_delta;
        private VerificationTypeInfo[] locals; // length = frame_type - 251
        public AppendFrame(byte frame_type, short offset_delta, VerificationTypeInfo[] locals) {
            super(frame_type);
            this.offset_delta = offset_delta;
            this.locals = locals;
        }
    }
    private static class FullFrame extends StackMapFrame {
        /* 255 */
        private short offset_delta;
        private short number_of_locals;
        private VerificationTypeInfo[] locals;
        private short number_of_stack_items;
        private VerificationTypeInfo[] stack;

        public FullFrame(byte frame_type, short offset_delta, short number_of_locals,
                          VerificationTypeInfo[] locals, short number_of_stack_items, VerificationTypeInfo[] stack) {
            super(frame_type);
            this.offset_delta = offset_delta;
            this.number_of_locals = number_of_locals;
            this.locals = locals;
            this.number_of_stack_items = number_of_stack_items;
            this.stack = stack;
        }
    }
    private static class VerificationTypeInfo {
        protected byte tag;
        public VerificationTypeInfo(byte tag) {
            this.tag = tag;
        }
    }

    private static class TopVariableInfo extends VerificationTypeInfo {
        public TopVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class IntegerVariableInfo extends VerificationTypeInfo {
        public IntegerVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class FloatVariableVnfo extends VerificationTypeInfo {
        public FloatVariableVnfo(byte tag) {
            super(tag);
        }
    }
    private static class DoubleVariableInfo extends VerificationTypeInfo {
        public DoubleVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class LongVariableInfo extends VerificationTypeInfo {
        public LongVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class NullVariableInfo extends VerificationTypeInfo {
        public NullVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class UninitializedThisVariableInfo extends VerificationTypeInfo {
        public UninitializedThisVariableInfo(byte tag) {
            super(tag);
        }
    }
    private static class ObjectVariableInfo extends VerificationTypeInfo {
        private short cpool_index;
        public ObjectVariableInfo(byte tag, short cpool_index) {
            super(tag);
            this.cpool_index = cpool_index;
        }
    }
    private static class UninitializedVariableInfo extends VerificationTypeInfo {
        private short offset;
        public UninitializedVariableInfo(byte tag, short offset) {
            super(tag);
            this.offset = offset;
        }
    }

    private static enum ITEM {
        ITEM_Top((byte)0),  ITEM_Integer((byte)1), ITEM_Float((byte)2), ITEM_Double((byte)3), ITEM_Long((byte)4),
        ITEM_Null((byte)5), ITEM_UninitializedThis((byte)6), ITEM_Object((byte)7), ITEM_Uninitialized((byte)8);
        private byte tag;
        ITEM(byte tag){
           this.tag = tag;
        }
    }

}
