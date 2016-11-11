package luozj.structure;

import luozj.structure.attribute.AttributeInfo;
import luozj.structure.constantpool.CPInfo;
import luozj.structure.field.FieldInfo;
import luozj.structure.method.MethodInfo;

/**
 * Created by luozj on 2016/11/9.
 */
public class ClassInfo {
    private int magic;
    private short minor_version;
    private short major_version;
    private float version;
    private short cp_count;
    private CPInfo[] cpInfos;
    private short access_flags;
    private short this_class;
    private short super_class;
    private short interfaces_count;
    private short[] interfaces;
    private short fields_count;
    private FieldInfo[] fields;
    private short methods_count;
    private MethodInfo[] methods;
    private short attrs_count;
    private AttributeInfo[] attrs;

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public short getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(short minor_version) {
        this.minor_version = minor_version;
    }

    public short getMajor_version() {
        return major_version;
    }

    public void setMajor_version(short major_version) {
        this.major_version = major_version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public short getCp_count() {
        return cp_count;
    }

    public void setCp_count(short cp_count) {
        this.cp_count = cp_count;
    }

    public CPInfo[] getCpInfos() {
        return cpInfos;
    }

    public void setCpInfos(CPInfo[] cpInfos) {
        this.cpInfos = cpInfos;
    }

    public short getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(short access_flags) {
        this.access_flags = access_flags;
    }

    public short getThis_class() {
        return this_class;
    }

    public void setThis_class(short this_class) {
        this.this_class = this_class;
    }

    public short getSuper_class() {
        return super_class;
    }

    public void setSuper_class(short super_class) {
        this.super_class = super_class;
    }

    public short getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(short interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public short[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(short[] interfaces) {
        this.interfaces = interfaces;
    }

    public short getFields_count() {
        return fields_count;
    }

    public void setFields_count(short fields_count) {
        this.fields_count = fields_count;
    }

    public FieldInfo[] getFields() {
        return fields;
    }

    public void setFields(FieldInfo[] fields) {
        this.fields = fields;
    }

    public short getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(short methods_count) {
        this.methods_count = methods_count;
    }

    public MethodInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInfo[] methods) {
        this.methods = methods;
    }

    public short getAttrs_count() {
        return attrs_count;
    }

    public void setAttrs_count(short attrs_count) {
        this.attrs_count = attrs_count;
    }

    public AttributeInfo[] getAttrs() {
        return attrs;
    }

    public void setAttrs(AttributeInfo[] attrs) {
        this.attrs = attrs;
    }
}
