package bbs.api.common.lib;

import java.util.Optional;

public class CommonFunction {
    /**
     * 判断对象Optional是否为空
     */
    public static boolean optionalIsNull(Optional<?> optional) {
        if (!optional.isPresent() || optional == Optional.empty()) {
            return true;
        }

        return false;
    }
}
