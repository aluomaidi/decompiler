package luozj;

import luozj.parser.ClassParser;
import luozj.structure.ClassInfo;
import luozj.util.ByteUtil;

import java.io.IOException;

/**
 * Created by luozj on 2016/11/7.
 */
public class Decompiler {
    public static void main(String[] args) throws IOException {
        String path = "E:\\ideaProject\\decompiler\\out\\production\\decompiler\\org\\fenixsoft\\clazz\\TestClass.class";
        ClassInfo classinfo = Decompiler.decompile(path);
    }
    public static ClassInfo decompile(String path) throws IOException {
        byte[] bytes = ByteUtil.getBytes(path);
        ClassParser classParser = new ClassParser(bytes, new ClassInfo());
        return classParser.parse();
    }
}
