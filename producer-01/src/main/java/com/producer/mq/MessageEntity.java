package com.producer.mq;

import lombok.Data;

/**
 * <p>
 * <b>MessageEntity</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/18
 */
@Data
public class MessageEntity {

    private Integer id;

    private String date;

    private String message;
}
