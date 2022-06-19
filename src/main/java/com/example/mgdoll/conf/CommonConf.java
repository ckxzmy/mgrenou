package com.example.mgdoll.conf;

public class CommonConf {
    public static enum SALE_TYPE{
        money("money", 1),point("point", 2);

        private String text;
        private int value;

        SALE_TYPE(String text, int value) {
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum FORM_TYPE{
        normal("normal", 1),sale("sale", 2),suit("suit", 3);

        private String text;
        private int value;

        FORM_TYPE(String text, int value) {
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }
    }

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String FIALL = "FIALL";
    /**********************对象和个体****************************/
    public static final String SESSION_USER = "loginedAgent"; // 用户对象
    public static final String SESSION_LOGINID = "sessionLoginID"; // 登录ID
    public static final String SESSION_USERID = "sessionUserID"; // 当前用户对象ID编号

    public static final String SESSION_USERNAME = "sessionUserName"; // 当前用户对象ID编号
    public static final Integer PAGE = 10; // 默认分页数
    public static final String SESSION_URL = "sessionUrl"; // 被记录的url
    public static final String SESSION_SECURITY_CODE = "sessionVerifyCode"; // 登录页验证码
    // 时间 缓存时间
    public static final int TIMEOUT = 1800;// 秒
    public static final String ON_LOGIN = "/logout.htm";
    public static final String LOGIN_OUT = "/toLogout";
    // 不验证URL anon：不验证/authc：受控制的
    public static final String NO_INTERCEPTOR_PATH =".*/((.css)|(.js)|(images)|(login)|(anon)).*";

    public static final String MANAGE_FLAG = "manage";
    public static final String APP_FLAG = "app";
}
