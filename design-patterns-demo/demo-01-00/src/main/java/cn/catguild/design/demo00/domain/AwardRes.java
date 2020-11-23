package cn.catguild.design.demo00.domain;

/**
 * 接口出参
 *
 * @author liu.zhi
 * @date 2020/10/19 11:59
 */
public class AwardRes {

    private String code; // 编码
    private String info; // 描述

    public AwardRes(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
