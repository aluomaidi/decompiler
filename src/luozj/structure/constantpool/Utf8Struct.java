package luozj.structure.constantpool;

import java.nio.charset.Charset;

/**
 * Created by luozj on 2016/11/9.
 */
public class Utf8Struct extends CPInfo {
    private final byte tag = 1;
    private short length;
    private byte[] bytes;
    private String value;

    public Utf8Struct(short length, byte[] bytes) {
        this.length = length;
        this.bytes = bytes;
        this.value = new String(bytes, Charset.forName("utf-8"));
    }
    public byte getTag() {
        return tag;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
