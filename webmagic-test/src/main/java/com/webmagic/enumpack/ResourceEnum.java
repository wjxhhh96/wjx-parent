package com.webmagic.enumpack;

/**
 * <p>
 * <b>ResourceEnum</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/28
 */
public enum  ResourceEnum {
    ZHI_HU("知乎"),
    PI_PI_XIA("皮皮虾"),

    ;
    String label;

    ResourceEnum(String label) {
        this.label = label;
    }
}
