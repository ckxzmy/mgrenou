package com.example.mgdoll.conf;

public class CommonConf {
    public static enum SALE_TYPE{
        money("money", 1),point("point", 2),free("free", 2);

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

    public static enum WATER_STATUS{
        //-1.删除 0.正常 1.审核中(待审) 2.审核不通过
        delete("删除", -1),valid("正常", 0),pending("审核中", 1),no_pass("审核不通过", 1);

        private String text;
        private int value;

        WATER_STATUS(String text, int value) {
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }

        public static String getName(Integer value){
            for(WATER_STATUS s:WATER_STATUS.values()){
                if(s.getValue() == value){
                    return s.text;
                }
            }
            return null;
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
//    public static final String NO_INTERCEPTOR_PATH =".*/((.css)|(.js)|(images)|(login)|(anon)).*";//正式版 swagger不可用
    public static final String NO_INTERCEPTOR_PATH =".*/((.css)|(.js)|(images)|(login)|(anon)|(swagger-ui.html)|(swagger-resources)|(webjars)|(v2)|(register)|(error)).*";

    public static final String MANAGE_FLAG = "manage";
    public static final String APP_FLAG = "app";
    public static final String MANAGE_ACTION = "MANAGE_ACTION";
    public static final String APP_ACTION = "APP_ACTION";
    public static final String LOGIN_TYPE_MESSAGE = "message";
    public static final String LOGIN_TYPE_PASSWORD = "password";
}
