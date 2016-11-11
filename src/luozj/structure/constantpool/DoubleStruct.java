package luozj.structure.constantpool;

/**
 * Created by luozj on 2016/11/9.
 */
public class DoubleStruct extends CPInfo {
    private final byte tag = 6;
    private double value;

    public DoubleStruct(double value) {
        this.value = value;
    }
    public byte getTag() {
        return tag;
    }

    public double getValue() {
        return value;
    }
}
