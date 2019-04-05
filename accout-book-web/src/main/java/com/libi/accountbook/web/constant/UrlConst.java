package com.libi.accountbook.web.constant;

/**
 * @author libi
 * =====这里存放各种URL常量=====
 */
public class UrlConst {
    /**登陆URL */
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_SUCCESS_URL = "/login/success";
    public static final String LOGIN_FAIL_URL = "/login/fail";
    /**登陆时传入的用户名和密码的参数*/
    public static final String USERNAME_PARAM = "userName";
    public static final String PASSWORD_PARAM = "password";

    /**登出URL*/
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGOUT_SUCCESS_URL = "/logout/success";

    /**注册URL*/
    public static final String REGISTER_URL = "/register";

    /**测试用URL*/
    public static final String TEST_URL = "/test";

    /**其他API的根URI*/
    public static final String TEST_ROOT = "/test";
    public static final String USER_ROOT = "/user";
    public static final String ASSETS_ROOT = "/assets";
    public static final String ACCOUNT_ROOT = "/account";
    public static final String FAMILY_ROOT = "/family";
    public static final String TREASURY_ROOT = "/treasury";
    public static final String TYPE_ROOT = "/type";
}
