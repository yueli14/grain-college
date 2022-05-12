package com.wcl.utils;


public enum ResultCode {
    SUCCESS("成功", 20000),
    ERROR("失败", 20001);
    private String desc;//文字描述
    private Integer code; //对应的代码
    private ResultCode(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 定义方法,返回代码,跟常规类的定义没区别
     *
     * @return
     */
    public int getCode() {
        return code;
    }
}
