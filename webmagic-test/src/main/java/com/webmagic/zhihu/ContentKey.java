package com.webmagic.zhihu;

/**
 * <p>
 * <b>ContentKey</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/28
 */
public enum ContentKey {

    TITLE("标题"),
    TIME("时间"),
    HOT_COUNT("热度"),
    AUTHOR("作者"),
    CONTENT("内容"),
    URL("地址"),
    RESOURCE("来源"),
    IMGS("图片"),
    ;

    String label;

    ContentKey(String label) {
        this.label = label;
    }
}
