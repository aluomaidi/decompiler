package luozj.structure.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by luozj on 2016/11/14.
 */
public class BootstrapMethodAttr extends AttributeInfo {
    private  short num;
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethodAttr(short attrName_index, int attr_length, byte[] info) {
        super(attrName_index, attr_length, info);
    }

    private static class BootstrapMethod {
        private short bootstrap_method_ref;
        private short num_bootstrap_arguments;
        private short[] bootstrap_arguments;

        public BootstrapMethod(short bootstrap_method_ref, short num_bootstrap_arguments, short[] bootstrap_arguments) {
            this.bootstrap_method_ref = bootstrap_method_ref;
            this.num_bootstrap_arguments = num_bootstrap_arguments;
            this.bootstrap_arguments = bootstrap_arguments;
        }
    }
    public static BootstrapMethodAttr init(short attrName_index, int attr_length, byte[] info, DataInputStream stream) throws IOException {
        BootstrapMethodAttr attr = new BootstrapMethodAttr(attrName_index, attr_length, info);
        attr.num = stream.readShort();
        attr.bootstrapMethods = new BootstrapMethod[attr.num];
        for (short i = 0; i < attr.num; i++) {
            attr.bootstrapMethods[i] = parseBootstrapMethod(stream);
        }
        return attr;
    }

    private static BootstrapMethod parseBootstrapMethod(DataInputStream stream) throws IOException {
        short bootstrap_method_ref = stream.readShort();
        short num_bootstrap_arguments = stream.readShort();
        short[] bootstrap_arguments = new short[num_bootstrap_arguments];
        for (short i = 0; i < num_bootstrap_arguments; i++) {
            bootstrap_arguments[i] = stream.readShort();
        }
        return new BootstrapMethod(bootstrap_method_ref, num_bootstrap_arguments, bootstrap_arguments);
    }
}
