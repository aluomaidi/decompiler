package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/14.
 */
public class RuntimeInvisibleAnnotionAttr extends AnnotationDefaultAttr {
    private short num;
    private Annotation[] annotations;
    public RuntimeInvisibleAnnotionAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static RuntimeInvisibleAnnotionAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        RuntimeInvisibleAnnotionAttr attr = new RuntimeInvisibleAnnotionAttr(attrName_index, attr_length, info);
        attr.num = stream.readShort();
        attr.annotations = new Annotation[attr.num];
        for (short i = 0; i < attr.num; i++) {
            attr.annotations[i] = parseAnnotation(stream);
        }
        return attr;
    }
}
