package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/14.
 */
public class RuntimeInvisibleParameterAnnotationAttr extends AnnotationDefaultAttr {
    private byte num;
    private ParameterAnnotation[] annotations;
    public RuntimeInvisibleParameterAnnotationAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static RuntimeInvisibleParameterAnnotationAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        RuntimeInvisibleParameterAnnotationAttr attr = new RuntimeInvisibleParameterAnnotationAttr(attrName_index, attr_length, info);
        attr.num = stream.readByte();
        attr.annotations = new ParameterAnnotation[attr.num];
        for (short i = 0; i < attr.num; i++) {
            attr.annotations[i] = parseParameterAnnotation(stream);
        }
        return attr;
    }
}
