package com.erenwu.common.enums;

/**
 * 通用响应枚举
 * @author eren
 */
public enum RespCodeEnum implements BaseEnum<String> {

    /**
     * 一切 ok
     */
    S0000("处理成功"),
    E0500("处理异常"),

    /**
     * 用户端的通用错误
     */
    A0001("用户端错误"),
    A0100("用户注册错误"),
    A0101("用户名校验失败"),
    A0102("用户名已存在"),
    A0103("密码校验失败"),
    A0104("短信校验码输入错误"),
    /**
     * 用户登录异常
     */
    A0200("用户登录异常"),
    A0201("用户账户不存在"),
    A0202("用户账户被冻结"),
    A0203("用户账户已作废"),

    /**
     * 访问权限异常
     */
    A0300("访问权限异常"),
    A0301("访问未授权"),
    A0302("无效的token"),
    A0303("权限枚举错误"),

    /**
     * 用户端其他异常
     */
    A0400("参数校验失败"),

    A0500("上传文件失败"),
    A0501("保存照片失败"),
    A0502("校验调研信息失败"),
    A0503("文件导出失败"),
    A0504("json解析失败"),

    A0601("状态异常"),

    A0701("feign调用异常"),
    A0702("调用外部系统异常"),
    A0703("数据重复")
    ;

    RespCodeEnum(String displayName){
        this.displayName = displayName;
    }

    private final String displayName;


    /**
     * 枚举value
     *
     * @return
     */
    @Override
    public String getValue() {
        return this.name();
    }

    /**
     * 枚举的显示名字
     *
     * @return
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }
}
