package com.evan.homemaking.handler;

import com.evan.homemaking.common.exception.*;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.evan.homemaking.common.consts.Message.EXCEPTION_DETAIL_HINT;

/**
 * @ClassName GlobalExceptionHandler
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/15 23:14
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handle a exception and response a result.
     * If you not give a Exception.class to @ExceptionHandler,It will handle exceptions of the type of the method parameter it is labeling.
     *
     * @param e exception
     * @return Response result(ResponseVO) from ResponseUtil.
     */
    @ExceptionHandler
    public ResponseEntity<ResponseVO> authenticateFailedException(AuthenticateFailedException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.authenticateFailedResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> badRequestException(BadRequestException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.notFoundResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> internalException(InternalException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.internalErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> notFoundException(NotFoundException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.notFoundResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> notNoSuchAnnotationException(NoSuchAnnotationException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.internalErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> unAuthorizedException(UnAuthorizedException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.authenticateFailedResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseVO> notLoginException(NotLoginException e) {
        log.error(EXCEPTION_DETAIL_HINT, e.toString());
        return ResponseUtil.notLoginRequestResponse(e.getMessage());
    }

}
