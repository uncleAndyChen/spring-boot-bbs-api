package bbs.api.common.model.response;

/**
 * 返回码枚举
 */
public enum ResponseCodeEnum {
    failed(0, "失败"),
    success(1, "成功"),
    error(-10, "异常"),
    unknownException(-20, "未知异常"),
    noSuchMethodException(-30, "调用了不存在的方法，请联系管理员。"),
    noRecord(-40, "暂无记录"),
    ;

    private int index;
    private String name;

    ResponseCodeEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ResponseCodeEnum getEnumByIndex(int code) {
        for (ResponseCodeEnum result : ResponseCodeEnum.values()) {
            if (code == result.index) {
                return result;
            }
        }

        return null;
    }
}
