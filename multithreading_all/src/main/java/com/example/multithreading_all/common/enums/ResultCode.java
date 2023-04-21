package com.example.multithreading_all.common.enums;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author: XiaoDong.Yang
 * @date: 2023/3/23 17:53
 */
public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //没有登录
    NO_LOGIN(402),

    //没有权限,
    NO_PERMISSION(403),
    //接口不存在
    NOT_FOUND(404),
    //数据库参数错误
    SQL_ERROR(405),
    //用户状态异常、公司状态异常、产品状态异常
    STATE_ERROR(406),

    //第三方接口报错
    THIRD_PARTY_INTERFACE(410),
    //无库存
    NO_INVENTORY(411),

    //服务器内部错误
    INTERNAL_SERVER_ERROR(500),
    //参数错误
    PARAMETER_ERROR(10001),
    //账号错误
    ACCOUNT_ERROR(20001),
    //登录失败
    LOGIN_FAIL_ERROR(20002),
    //密码错误
    PASSWORD_ERROR(20003),
    //超出推送最大限制
    PUSH_COUNT_TO_LARGE(30001),
    //元保保单状态查询，不符合条件的
    YUAN_BAO_POLICYNO_STATUS(0),
    //超出推送最大限制
    PUSH_COUNT_BEYOND_TODAY_LIMIT(30002),

    //华安调用优加承保接口成功
    HUA_AN_SUCCESS(0),

    HUA_AN_FAIL(1),

    SHUIDI_SUCCESS(0),
    //药企SAAS
    MED_SAAS_SUCCESS(0),

    SHUIDI_FAIL(-10000);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }


    public static void main(String[] args) {
        System.out.println("--"+ResultCode.SUCCESS.code);
    }
}
