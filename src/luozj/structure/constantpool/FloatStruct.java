package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class FloatStruct extends CPInfo {
    private final byte tag = 4;
    private float value;

    public FloatStruct(float value) {
        this.value = value;
    }
    public byte getTag() {
        return tag;
    }

    public float getValue() {
        return value;
    }
}
