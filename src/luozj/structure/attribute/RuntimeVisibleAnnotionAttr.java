package luozj.structure.attribute;

/**
 * Created by luozj on 16/11/13.
 */
public class RuntimeVisibleAnnotionAttr extends AttributeInfo {
    private short num_annotations;
    private Annotation[] annotations;

    public RuntimeVisibleAnnotionAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
    private static class Annotation {
        private short type_index;
        private short num_pairs;
        private ElementValuePair[] pairs;
    }
    private static class ElementValuePair {
        private short element_name_index;
        private ElementValue elementValue;
    }
    private static class ElementValue {
        private byte tag;
    }
}
