package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class InvokeDynamicStruct extends CPInfo {
    private final byte tag = 18;
    private short bootstrap_method_attr_index;
    private short name_and_type_index;

    public InvokeDynamicStruct(short bootstrap_method_attr_index, short name_and_type_index) {
        this.bootstrap_method_attr_index = bootstrap_method_attr_index;
        this.name_and_type_index = name_and_type_index;
    }
    public byte getTag() {
        return tag;
    }

    public short getBootstrap_method_attr_index() {
        return bootstrap_method_attr_index;
    }

    public void setBootstrap_method_attr_index(short bootstrap_method_attr_index) {
        this.bootstrap_method_attr_index = bootstrap_method_attr_index;
    }

    public short getName_and_type_index() {
        return name_and_type_index;
    }

    public void setName_and_type_index(short name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }
}
