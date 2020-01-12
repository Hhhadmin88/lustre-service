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
     * Build ResponseVO
     *
     * @param data   The data need to return
     * @param status status code
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> constructResponse(Object data, String message, HttpStatus status) {
        return new ResponseEntity<>(new ResponseVO(status.value(), message, data), status);
    }

    /**
     * Build entity
     *
     * @param vo ResponseVO
     * @return ResponseVO and status
     */
    public static ResponseEntity<ResponseVO> constructEntity(ResponseVO vo) {
        return new ResponseEntity<>(vo, HttpStatus.valueOf(vo.getCode()));
    }

    /**
     * Build successful ResponseVO
     * getReasonPhrase() is explanation of the status code.
     *
     * @param data The data need to return
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> successResponse(Object data) {
        return constructResponse(data, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    /**
     * Build successful ResponseVO
     *
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> successResponse() {
        return constructResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    /**
     * Build 500 internal error ResponseVO
     *
     * @param message Error message.
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> internalErrorResponse(String message) {
        return constructResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Build 404 not found ResponseVO
     *
     * @param message error message
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> notFoundResponse(String message) {
        return constructResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), message, HttpStatus.NOT_FOUND);
    }

    /**
     * Build 401 unauthorized ResponseVO.
     *
     * @param message error message.
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> authenticateFailedResponse(String message) {
        return constructResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(), message, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Build 400 badRequest ResponseVO.
     *
     * @param message error message.
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> badRequestResponse(String message) {
        return constructResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Build 403 notLoginRequest ResponseVO.
     *
     * @param message error message.
     * @return ResponseVO
     */
    public static ResponseEntity<ResponseVO> notLoginRequestResponse(String message) {
        return constructResponse(HttpStatus.FORBIDDEN, message, HttpStatus.FORBIDDEN);
    }
}
