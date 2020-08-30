package cn.xilikeli.skyblog.common;

import cn.xilikeli.skyblog.common.configuration.CodeMessageConfiguration;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.exception.http.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 全局异常处理类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:06
 * @since JDK1.8
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 消息码配置对象
     */
    private final CodeMessageConfiguration codeMessageConfiguration;

    /**
     * 构造函数,注入该类需要的对象
     *
     * @param codeMessageConfiguration 消息码配置对象
     */
    public GlobalExceptionAdvice(CodeMessageConfiguration codeMessageConfiguration) {
        this.codeMessageConfiguration = codeMessageConfiguration;
    }

    /**
     * 处理未知异常
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-Exception: ", e);

        // 通过 code 码得到对应的提示信息
        String info = this.codeMessageConfiguration.getMessageByCode(CodeMessageConstant.SERVER_ERROR);
        return new UnifyResponse(CodeMessageConstant.SERVER_ERROR, info, method + " " + requestUrl);
    }

    /**
     * 处理 Http 自定义异常
     * 处理已知异常
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest request, HttpException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 通过 code 码得到对应的提示信息
        String info = this.codeMessageConfiguration.getMessageByCode(e.getCode());

        // 记录异常信息
        log.error("全局处理异常-HttpException: code: {}, message: {}, request: {}", e.getCode(), info, method + " " + requestUrl);

        UnifyResponse message = new UnifyResponse(e.getCode(), info, method + " " + requestUrl);

        // 这里因为没用 Spring 的注解,所以需要 headers 来设置返回的类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 获取 http 状态码并设置
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        // 使用 ResponseEntity 泛型类来返回包含 Http 状态码、返回结果等内容
        return new ResponseEntity<>(message, headers, httpStatus);
    }

    /**
     * 处理 NoHandlerFoundException
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public UnifyResponse handleHttpMessageNotReadableException(HttpServletRequest request, NoHandlerFoundException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-NoHandlerFoundException: ", e);

        // 通过 code 码得到对应的提示信息
        String info = this.codeMessageConfiguration.getMessageByCode(CodeMessageConstant.NOT_FOUND_ROUTE);
        return new UnifyResponse(CodeMessageConstant.NOT_FOUND_ROUTE, info, method + " " + requestUrl);
    }


    /**
     * 处理 handleHttpMessageNotReadableException
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-HttpMessageNotReadableException: ", e);

        // 通过 code 码得到对应的提示信息
        String info = this.codeMessageConfiguration.getMessageByCode(CodeMessageConstant.REQUEST_BODY_CANNOT_EMPTY);
        return new UnifyResponse(CodeMessageConstant.REQUEST_BODY_CANNOT_EMPTY, info, method + " " + requestUrl);
    }

    /**
     * 处理 HttpMessageConversionException
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageConversionException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-HttpMessageConversionException: ", e);

        // 通过 code 码得到对应的提示信息
        String info = this.codeMessageConfiguration.getMessageByCode(CodeMessageConstant.REQUEST_BODY_CANNOT_EMPTY);
        return new UnifyResponse(CodeMessageConstant.REQUEST_BODY_CANNOT_EMPTY, info, method + " " + requestUrl);
    }

    /**
     * 处理 MethodArgumentNotValidException 参数校验异常
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleBeanValidation(HttpServletRequest request, MethodArgumentNotValidException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-MethodArgumentNotValidException: ", e);

        // 获取错误信息列表
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 组合错误信息
        String errorMessage = formatAllErrorMessages(errors);
        // 参数错误使用统一的错误码
        return new UnifyResponse(CodeMessageConstant.PARAMETER_ERROR, errorMessage, method + " " + requestUrl);
    }

    /**
     * 处理 ConstraintViolationException 参数校验异常
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyResponse handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 记录异常信息
        log.error("全局处理异常-ConstraintViolationException: ", e);

        // 获取错误信息列表
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        // 组合错误信息
        String errorMessage = formatAllErrorMessages(errors);

        return new UnifyResponse(CodeMessageConstant.PARAMETER_ERROR, errorMessage, method + " " + requestUrl);
    }

    /**
     * 将异常错误信息列表中的错误信息拼接到一起并返回
     *
     * @param errors 错误信息列表
     * @return 返回组合后的错误信息字符串
     */
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append("; ")
        );
        return errorMsg.toString();
    }

    /**
     * 将异常错误信息列表中的错误信息拼接到一起并返回
     *
     * @param errors 错误信息列表
     * @return 返回组合后的错误信息字符串
     */
    private String formatAllErrorMessages(Set<ConstraintViolation<?>> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getMessage()).append("; ")
        );
        return errorMsg.toString();
    }

}