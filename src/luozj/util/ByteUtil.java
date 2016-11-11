package luozj.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by luozj on 2016/11/7.
 */
public final class ByteUtil {
    public static final String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length * 2); // one byte two hex string
        String sTemp;
        for (int i = 0, length = bArray.length; i < length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    /*public static final Short bytesToShort(byte[] bArray) throws IOException {

        return dis.readShort();
    }*/
    public static final byte[] getBytes(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        return buffer;
    }
}
