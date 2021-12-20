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
}
