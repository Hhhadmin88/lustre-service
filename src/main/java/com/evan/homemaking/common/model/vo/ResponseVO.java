package com.evan.homemaking.common.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResponseVO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 20:28
 */
@Data
@NoArgsConstructor
public class ResponseVO {
    private Integer code;
    private String message;
    private Object data;
    private Boolean success;

    public ResponseVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = code == 200 ? true : false;
    }
}
