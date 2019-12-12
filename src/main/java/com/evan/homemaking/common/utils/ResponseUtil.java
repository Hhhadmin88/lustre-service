package com.evan.homemaking.common.utils;

import com.evan.homemaking.common.model.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @ClassName ResponseUtil
 * @Description ResponseUtil for construct ResponseVo.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 20:25
 */
public class ResponseUtil {
    private ResponseUtil() {
    }

    /**
     * 构造response对象
     *
     * @param data   返回数据
     * @param status 状态码
     * @return 结果
     */
    public static ResponseEntity<ResponseVO> constructResponse(Object data, String message, HttpStatus status) {
        return new ResponseEntity<>(new ResponseVO(status.value(), message, data), status);
    }

    /**
     * 构建entity
     *
     * @param vo response封装对象
     * @return 实体
     */
    public static ResponseEntity<ResponseVO> constructEntity(ResponseVO vo) {
        return new ResponseEntity<>(vo, HttpStatus.valueOf(vo.getCode()));
    }

    /**
     * 构造成功response对象
     * getReasonPhrase() is explanation of the status code.
     *
     * @param data 数据
     * @return 结果
     */
    public static ResponseEntity<ResponseVO> successResponse(Object data) {
        return constructResponse(data, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    /**
     * 构造成功response对象
     *
     * @return 结果
     */
    public static ResponseEntity<ResponseVO> successResponse() {
        return constructResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    /**
     * 构建内部错误对象
     *
     * @param message 错误信息
     * @return 对象
     */
    public static ResponseEntity<ResponseVO> internalErrorResponse(String message) {
        return constructResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 构建 404 not found 对象
     *
     * @param message 错误信息
     * @return 对象
     */
    public static ResponseEntity<ResponseVO> notFoundResponse(String message) {
        return constructResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), message, HttpStatus.NOT_FOUND);
    }
}
