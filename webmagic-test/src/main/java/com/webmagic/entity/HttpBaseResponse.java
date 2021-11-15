package com.webmagic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * <b>HttpBaseResponse</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/11/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpBaseResponse<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

}

