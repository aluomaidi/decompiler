package luozj.parser;

import luozj.structure.ClassInfo;
import luozj.structure.attribute.*;
import luozj.structure.constantpool.*;
import luozj.structure.field.FieldInfo;
import luozj.structure.method.MethodInfo;
import luozj.util.Constants;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/10.
 */
public class ClassParser {
    private byte[] source;
    private DataInputStream inputStream;
    private ClassInfo classInfo;
    private CPInfo[] cpInfos;
    public ClassParser(byte[] source, ClassInfo classInfo) {
        this.source = source;
        inputStream = new DataInputStream(new ByteArrayInputStream(source));
        this.classInfo = classInfo;
    }
    public ClassInfo parse() throws IOException {
        classInfo.setMagic(parseMagic());
        short minor = parseMinorVersion();
        short major = parseMajorVersion();
        classInfo.setMinor_version(minor);
        classInfo.setMajor_version(major);
        classInfo.setVersion(parseVersion(minor, major));
        short cp_count = parseCpCount();
        classInfo.setCp_count(cp_count);
        cpInfos = parseConstantPool(cp_count);
        classInfo.setCpInfos(cpInfos);
        classInfo.setAccess_flags(parseAccessFlag());
        classInfo.setThis_class(parseThisClass());
        classInfo.setSuper_class(parseSuperClass());
        short interface_count = parseInterfaceCount();
        classInfo.setInterfaces_count(interface_count);
        classInfo.setInterfaces(parseInterfacs(interface_count));
        short field_count = parseFieldCount();
        classInfo.setFields_count(field_count);
        classInfo.setFields(parseFileds(field_count));
        short method_count = parseMethodCount();
        classInfo.setMethods_count(method_count);
        classInfo.setMethods(parseMethods(method_count));
        short attr_cocunt = parseAttrCount();
        classInfo.setAttrs_count(attr_cocunt);
        classInfo.setAttrs(parseAttrs(attr_cocunt));
        return classInfo;
    }
    private int parseMagic() throws IOException {
        return inputStream.readInt();
    }
    private short parseMinorVersion() throws IOException {
        return inputStream.readShort();
    }
    private short parseMajorVersion() throws IOException {
        return inputStream.readShort();
    }
    private float parseVersion(short minor, short major) throws IOException {
        return  Float.parseFloat(major+ "." + minor);
    }
    private short parseCpCount() throws IOException {
        return  inputStream.readShort();
    }
    private CPInfo[] parseConstantPool(short cp_count) throws IOException {
        CPInfo[] cpInfos = new CPInfo[cp_count];
        // 常量池第一项留空，有些结构的索引指向这一个位置来表示不引用任何常量池信息
        for (int i = 1; i < cp_count; i++) {
            cpInfos[i] = parseCpItem();
        }
        return cpInfos;
    }

    private CPInfo parseCpItem() throws IOException {
        byte tag = inputStream.readByte(); // cp item type
        CPInfo cpInfo = null;
        switch (tag) {
            case 1: { // utf-8 info
                short length = inputStream.readShort();
                byte[] bytes = new byte[length];
                inputStream.read(bytes);
                return new Utf8Struct(length, bytes);
            }
            case 3: // integer info
                return new IntegerStruct(inputStream.readInt());
            case 4:
                return new FloatStruct(inputStream.readFloat());
            case 5:
                return new LongStruct(inputStream.readLong());
            case 6:
                return new DoubleStruct(inputStream.readDouble());
            case 7:
                return new ClassStruct(inputStream.readShort());
            case 8:
                return new StringStruct(inputStream.readShort());
            case 9:
                return new FieldStruct(inputStream.readShort(), inputStream.readShort());
            case 10:
                return new MethodStruct(inputStream.readShort(), inputStream.readShort());
            case 11:
                return new InterfaceMethodStruct(inputStream.readShort(), inputStream.readShort());
            case 12:
                return new NameAndTypeStruct(inputStream.readShort(), inputStream.readShort());
            case 15:
                return new MethodHandleStruct(inputStream.readByte(), inputStream.readShort());
            case 16:
                return new MethodTypeStruct(inputStream.readShort());
            case 18:
                return new InvokeDynamicStruct(inputStream.readShort(), inputStream.readShort());
            default: return null;
        }
    }
    private short parseAccessFlag() throws IOException {
        return  inputStream.readShort();
    }
    private short parseThisClass() throws IOException {
        return  inputStream.readShort();
    }
    private short parseSuperClass() throws IOException {
        return  inputStream.readShort();
    }
    private short parseInterfaceCount() throws IOException {
        return  inputStream.readShort();
    }
    private short[] parseInterfacs(short count) throws IOException {
        short[] interfaces = new short[count];
        for (int i = 0; i < count; i++) {
            interfaces[i] = inputStream.readShort();
        }
        return interfaces;
    }
    private short parseFieldCount() throws IOException {
        return  inputStream.readShort();
    }
    private FieldInfo parseFiled(short count) throws IOException {
        FieldInfo fieldInfo = new FieldInfo(inputStream.readShort(), inputStream.readShort(), inputStream.readShort(), inputStream.readShort());
        short attr_count = fieldInfo.getAttr_count();
        if (attr_count == 0) {
            return fieldInfo;
        }
        fieldInfo.setAttrs(parseAttrs(attr_count));
        return fieldInfo;
    }
    private FieldInfo[] parseFileds(short count) throws IOException {
        FieldInfo[] fields = new FieldInfo[count];
        for (int i = 0; i < count; i++) {
            fields[i] = parseFiled(count);
        }
        return fields;
    }
    private short parseMethodCount() throws IOException {
        return  inputStream.readShort();
    }
    private MethodInfo parseMethod(short count) throws IOException {
        MethodInfo methodInfo = new MethodInfo(inputStream.readShort(), inputStream.readShort(), inputStream.readShort(), inputStream.readShort());
        short attr_count = methodInfo.getAttr_count();
        if (attr_count == 0) {
            return methodInfo;
        }
        methodInfo.setAttrs(parseAttrs(attr_count));
        return methodInfo;
    }
    private MethodInfo[] parseMethods(short count) throws IOException {
        MethodInfo[] methods = new MethodInfo[count];
        for (int i = 0; i < count; i++) {
            methods[i] = parseMethod(count);
        }
        return methods;
    }
    public AttributeInfo parseAttr(DataInputStream inputStream) throws IOException {
        short attrNameIndex = inputStream.readShort();
        int attrLength = inputStream.readInt();
        byte[] info = new byte[attrLength];
        inputStream.read(info);
        return parseAttrInfo(attrNameIndex, attrLength, info);
    }
    private AttributeInfo parseAttrInfo(short attrNameIndex, int attrLength, byte[] info) throws IOException {
        String attrName = ((Utf8Struct)cpInfos[attrNameIndex]).getValue();
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(info));
        AttributeInfo attrInfo = null;
        if (Constants.ATTR_CODE.equals(attrName)) {
            return CodeAttr.init(attrNameIndex, attrLength, info, stream, this);
        } else if (Constants.ATTR_CONSTANTVALUE.equals(attrName)) {
            return ConstantValueAttr.init(attrNameIndex, attrLength, info, stream);
        }  else if (Constants.ATTR_DEPRECATED.equals(attrName)) {
            return DeprecatedAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_EXCEPTIONS.equals(attrName)) {
            return MethodExceptionAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_ENCLOSINGMETHOD.equals(attrName)) {
            return EnclosingMethodAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_INNERCLASSES.equals(attrName)) {
            return InnerClassesAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_LINENUMBERTABLE.equals(attrName)) {
            return LineNumberTableAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_LOCALVARIABLETABLE.equals(attrName)) {
            return LocalVariableTableAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_STACKMAPTABLE.equals(attrName)) {

        } else if (Constants.ATTR_SIGNATURE.equals(attrName)) {
            return SignatureAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_SOURCEFILE.equals(attrName)) {
            return SourceFileAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_SOURCEDEBUGEXTENSION.equals(attrName)) {
            return SourceDebugExtensionAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_SYNTHETIC.equals(attrName)) {
            return SyntheticAttr.init(attrNameIndex, attrLength, info, stream);
        } else if (Constants.ATTR_LOCALVARIABLETYPETABLE.equals(attrName)) {
            return LocalVariableTypeTableAttr.init(attrNameIndex, attrLength, info, stream);
        }
        return attrInfo;
    }
    private short parseAttrCount() throws IOException {
        return  inputStream.readShort();
    }
    private AttributeInfo[] parseAttrs(short count) throws IOException {
        AttributeInfo[] attrs = new AttributeInfo[count];
        for (int i = 0 ;i < count; i++) {
            attrs[i] = parseAttr(inputStream);
        }
        return attrs;
    }
}
