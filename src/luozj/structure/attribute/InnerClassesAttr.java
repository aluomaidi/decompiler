package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/11.
 */
public class InnerClassesAttr extends  AttributeInfo {

    private short number;
    private InnerClass[] classes;

    public InnerClassesAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static InnerClassesAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        InnerClassesAttr attr = new InnerClassesAttr(attrName_index, attr_length, info);
        attr.number = stream.readShort();
        attr.classes = new InnerClass[attr.number];
        for (short i = 0;i < attr.number; i++) {
            attr.classes[i] = new InnerClass(stream.readShort(), stream.readShort(), stream.readShort(), stream.readShort());
        }
        return attr;
    }
    private static class InnerClass {
        private short inner_class_info_index;
        private short outer_class_info_index;
        private short inner_name_index;
        private short inner_class_access_flags;

        public InnerClass(short inner_class_info_index, short outer_class_info_index, short inner_name_index, short inner_class_access_flags) {
            this.inner_class_info_index = inner_class_info_index;
            this.outer_class_info_index = outer_class_info_index;
            this.inner_name_index = inner_name_index;
            this.inner_class_access_flags = inner_class_access_flags;
        }
    }
}
