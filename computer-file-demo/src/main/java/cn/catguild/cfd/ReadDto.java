package cn.catguild.cfd;

/**
 * @author liu.zhi
 * @date 2020/12/28 14:42
 */
public class ReadDto {

    private String prefix;
    private String text;
    private int titleLevel;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(int titleLevel) {
        this.titleLevel = titleLevel;
    }

}
