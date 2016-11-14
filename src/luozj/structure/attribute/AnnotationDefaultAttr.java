package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/14.
 */
public class AnnotationDefaultAttr extends AttributeInfo {
    private ElementValue default_value;
    public AnnotationDefaultAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    public static AnnotationDefaultAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        AnnotationDefaultAttr attr = new AnnotationDefaultAttr(attrName_index, attr_length, info);
        attr.default_value = parseElementValue(stream);
        return attr;
    }

    protected static class Annotation {
        private short type_index;
        private short num_pairs;
        private ElementValuePair[] pairs;

        public Annotation(short type_index, short num_pairs, ElementValuePair[] pairs) {
            this.type_index = type_index;
            this.num_pairs = num_pairs;
            this.pairs = pairs;
        }
    }
    protected static class ParameterAnnotation {
        private short num_annotations;
        private Annotation[] annotations;

        public ParameterAnnotation(short num_annotations, Annotation[] annotations) {
            this.num_annotations = num_annotations;
            this.annotations = annotations;
        }
    }
    protected static class ElementValuePair {
        private short name_index;
        private ElementValue value;

        public ElementValuePair(short name_index, ElementValue value) {
            this.name_index = name_index;
            this.value = value;
        }
    }
    protected static class ElementValue {
        private byte tag;
        private short const_value_index;
        private EnumConstValue enum_const_value;
        private short class_info_index;
        private Annotation annotation_value;
        private ArrayValue array_value;

        public byte getTag() {
            return tag;
        }

        public void setTag(byte tag) {
            this.tag = tag;
        }

        public short getConst_value_index() {
            return const_value_index;
        }

        public void setConst_value_index(short const_value_index) {
            this.const_value_index = const_value_index;
        }

        public EnumConstValue getEnum_const_value() {
            return enum_const_value;
        }

        public void setEnum_const_value(EnumConstValue enum_const_value) {
            this.enum_const_value = enum_const_value;
        }

        public short getClass_info_index() {
            return class_info_index;
        }

        public void setClass_info_index(short class_info_index) {
            this.class_info_index = class_info_index;
        }

        public Annotation getAnnotation_value() {
            return annotation_value;
        }

        public void setAnnotation_value(Annotation annotation_value) {
            this.annotation_value = annotation_value;
        }

        public ArrayValue getArray_value() {
            return array_value;
        }

        public void setArray_value(ArrayValue array_value) {
            this.array_value = array_value;
        }
    }
    protected static class EnumConstValue {
        private short type_name_index;
        private short const_name_index;

        public EnumConstValue(short type_name_index, short const_name_index) {
            this.type_name_index = type_name_index;
            this.const_name_index = const_name_index;
        }
    }
    protected static class ArrayValue {
        private short num_values;
        private ElementValue[] values;

        public ArrayValue(short num_values, ElementValue[] values) {
            this.num_values = num_values;
            this.values = values;
        }
    }
    protected static Annotation parseAnnotation(DataInputStream stream) throws IOException {
        short type_index = stream.readShort();
        short num_pairs = stream.readShort();
        ElementValuePair[] pairs = new ElementValuePair[num_pairs];
        for (short j = 0 ; j < num_pairs; j++ ) {
            short name_index = stream.readShort();
            pairs[j] = new ElementValuePair(name_index, parseElementValue(stream));
        }
        return new Annotation(type_index, num_pairs, pairs);
    }

    protected static ElementValue parseElementValue(DataInputStream stream) throws IOException {
        ElementValue value = new ElementValue();
        byte tag = stream.readByte();
        if (tag == 'e') {
            value.setEnum_const_value(new EnumConstValue(stream.readShort(), stream.readShort()));
        } else if (tag == 'c') {
            value.setClass_info_index(stream.readShort());
        } else if (tag == '@') {
            value.setAnnotation_value(parseAnnotation(stream));
        } else if (tag == '[') {
            short num = stream.readShort();
            ElementValue[] elementValues = new ElementValue[num];
            for (short i = 0 ; i < num; i++) {
                elementValues[i] = parseElementValue(stream);
            }
            value.setArray_value(new ArrayValue(num, elementValues));
        } else {
            value.setConst_value_index(stream.readShort());
        }
        return value;
    }
    protected static ParameterAnnotation parseParameterAnnotation(DataInputStream stream) throws IOException {
        short num = stream.readShort();
        Annotation[] annotations = new Annotation[num];
        for (short j = 0 ; j < num; j++ ) {
            annotations[j] = parseAnnotation(stream);
        }
        return new ParameterAnnotation(num, annotations);
    }
}
