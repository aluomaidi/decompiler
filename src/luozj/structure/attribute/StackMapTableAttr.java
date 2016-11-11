package luozj.structure.attribute;

/**
 * Created by luozj on 2016/11/11.
 */
public class StackMapTableAttr extends AttributeInfo {
    private short number;

    public StackMapTableAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }
}
