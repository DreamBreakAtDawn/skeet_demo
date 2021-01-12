/*
 * Copyright (c) 2017 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.modules.project.common.model.domain;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.util.JsonUtils;
import com.skeet.consul.provider.util.ReflectUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 消息响应类
 *
 * @author luoxb
 * @date 2017年3月13日
 */
public class FB4Response implements Serializable {

    public static void main(String[] args) {
        FB4Response obj = new FB4Response();
        ReflectUtil.setDefaultValue(obj);
        obj.setErrors(Lists.newArrayList(ReflectUtil.setDefaultValue(new FB4Error())));
        System.out.println(JsonUtils.beanToJson(obj));
    }

    /**
     * 成功
     */
    public final static String RESULT_SUCCESS = "1";
    /**
     * 失败
     */
    public final static String RESULT_FAIL = "0";
    /**
     * 部分失败
     */
    public final static String RESULT_PART_FAIL = "2";

    /**
     * 系统处理失败
     */
    public final static String UN_KNOW_EXCEPTION = "un_know_exception";
    /**
     * 业务异常失败
     */
    public final static String BUSINESS_EXCEPTION = "business_exception";
    /**
     * 访问的资源不存在
     */
    public final static String NOT_FIND_RESOURCE = "not_find_resource";
    /**
     * 请求参数异常
     */
    public final static String MISS_PARAM_RESOURCE = "miss_param_resource";
    /**
     * 系统处理成功
     */
    public final static String SUCCESS_MSG = "success_msg";
    /**
     * 日志输出模板
     */
    public final static String LOGGER_TEMPLATE = "error:{},params:{}。";
    private static final long serialVersionUID = 1L;
    private final static String DO_ERROR_EXCEPTION = "DoErrorException";
    private final static String DATA_ACCESS_EXCEPTION = "DataAccessException";
    private final static String NULL_POINTER_EXCEPTION = "NullPointerException";
    private final static String IO_EXCEPTION = "IOException";
    private final static String CLASS_NOT_FOUND_EXCEPTION = "ClassNotFoundException";
    private final static String ARITHMETIC_EXCEPTION = "ArithmeticException";
    private final static String ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = "ArrayIndexOutOfBoundsException";
    private final static String ILLEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";
    private final static String CLASS_CAST_EXCEPTION = "ClassCastException";
    private final static String SECURITY_EXCEPTION = "SecurityException";
    private final static String SQL_EXCEPTION = "SQLException";
    private final static String NO_SUCH_METHOD_ERROR = "NoSuchMethodError";
    private final static String INTERNAL_ERROR = "InternalError";
    private final static String SCHEDULER_EXCEPTION = "SchedulerException";
    private final static String DATA_INTEGRITY_VIOLATION_EXCEPTION = "DataIntegrityViolationException";
    private final static String HTTP_CLIENT_ERROR_EXCEPTION = "HttpClientErrorException";
    private final static String RETRYABLE_EXCEPTION = "RetryableException";
    private final static String APIJSON_EXCEPTION = "ApiJsonExceptin";
    private final static String DUPLICATE_KEY_EXCEPTION = "DuplicateKeyException";
    private final static String SYSTEM_EXCEPTION = "SystemException";
    /**
     * 执行代码
     */
    private String result;
    /**
     * 描述
     */
    private String msg;
    /**
     * 错误信息集合
     */
    private List<FB4Error> errors;
    /**
     * 数据结果集
     */
    private Object data;

    public FB4Response() {
    }


    /**
     * 构造指定结果状态响应信息
     */
    public FB4Response(String result) {
        this.result = result;
    }

    /**
     * 以消息和响应码来构造信息
     */
    public FB4Response(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    /**
     * 构建部分成功返回信息
     */
    public static FB4Response buildPartFailResult() {
        FB4Response result = new FB4Response();
        result.setDefPartFailMsg();
        return result;
    }

    /**
     * 构建部分成功返回信息
     */
    public static FB4Response buildPartFailResult(Object data) {
        FB4Response result = new FB4Response();
        result.setDefPartFailMsg();
        result.setData(data);
        return result;
    }

    /**
     * 构建成功的返回信息
     */
    public static FB4Response buildSuccessResult() {
        FB4Response result = new FB4Response();
        result.setDefSucMsg();
        return result;
    }

    /**
     * 构建成功的返回信息
     */
    public static FB4Response buildSuccessResult(Object data) {
        FB4Response result = new FB4Response();
        result.setDefSucMsg();
        result.setData(data);
        return result;
    }

    /**
     * 构建失败的返回信息
     */
    public static FB4Response buildFailResult() {
        FB4Response result = new FB4Response();
        result.setDefFailMsg();
        return result;
    }

    /**
     * 构建失败的返回信息
     *
     * @
     */
    public static FB4Response buildFailResult(String code, String msg, FB4Error... error) {
        FB4Response result = new FB4Response();
        result.setResult(code);
        result.setMsg(msg);

        if (error != null && error.length > 0) {
            List<FB4Error> errors = new ArrayList<FB4Error>(error.length);
            result.setErrors(errors);
            Collections.addAll(errors, error);
        }

        return result;
    }

    /**
     * 构建失败的返回信息
     */
    public static FB4Response buildFailResult(String code, String msg) {
        FB4Response result = new FB4Response();
        result.setResult(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 构建失败的返回信息 自定义msg
     */
    public static FB4Response buildFailResult(String msg) {
        FB4Response result = new FB4Response();
        result.setFailMsg(msg);
        return result;
    }

    /**
     * 构建失败的返回信息 根据异常分类
     */
    public static FB4Response buildFailResult(FB4Error error) {
        FB4Response result = new FB4Response();
        result.setDefFailMsg();
        result.addError(error);
        return result;
    }

    /**
     * 构建失败的返回信息 根据异常分类
     */
    public static FB4Response buildFailResult(List<FB4Error> errors) {
        FB4Response result = new FB4Response();
        result.setDefFailMsg();
        result.setErrors(errors);
        return result;
    }

    /**
     * 构建失败的返回信息 自定义msg 根据异常分类
     */
    public static FB4Response buildFailResult(String msg, List<FB4Error> errors) {
        FB4Response result = new FB4Response();
        result.setFailMsg(msg);
        result.setErrors(errors);
        return result;
    }

    /**
     * 构建失败的返回信息 根据异常分类
     */
    public static FB4Response buildFailResult(String msgCode, Exception e) {
        FB4Response result = new FB4Response();
        result.setExceptionMsg(msgCode, e);
        return result;
    }

    /**
     * 构建失败的返回信息 根据异常分类
     */
    public static FB4Response buildFailResult(Exception e) {
        FB4Response result = new FB4Response();
        result.setExceptionMsg(e);
        return result;
    }

    /**
     * 构建业务异常的返回信息
     */
    public static FB4Response buildBusinessFailResult(String errorMsg) {
        FB4Response result = new FB4Response();
        result.setResult(RESULT_FAIL);
        result.setPureMsg(BUSINESS_EXCEPTION);
        result.addError(null, errorMsg);
        return result;
    }

    public static String getErrorMsg(FB4Response response) {
        List<FB4Error> errors = response.getErrors();
        FB4Error fb4Error = errors.get(0);
        return fb4Error.getErrorMsg();
    }

    @Override
    public String toString() {
        return "FB4Response [result=" + result + ", msg=" + msg + ", errors="
                + errors + ", data=" + data + "]";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 设置默认的失败标志
     */
    public void setDefPartFailMsg() {
        setResult(RESULT_PART_FAIL);
        setMsg(SUCCESS_MSG);
    }

    /**
     * 设置默认的失败标志
     */
    public void setDefFailMsg() {
        setResult(RESULT_FAIL);
        setMsg(UN_KNOW_EXCEPTION);
    }

    public void setFailMsg(String msg) {
        setResult(RESULT_FAIL);
        setMsg(msg);
    }

    /**
     * 设置异常信息
     *
     * @param e
     */
    public void setExceptionMsg(String msgCode, Exception e) {
        setResult(RESULT_FAIL);
        setMsg(msgCode);
        addError(buildError(e));
    }

    /**
     * 设置异常信息
     *
     * @param e
     */
    public void setExceptionMsg(Exception e) {
        setResult(RESULT_FAIL);
        setMsg(UN_KNOW_EXCEPTION);
        addError(buildError(e));
    }


    /**
     * 设置默认的成功标志
     */
    public void setDefSucMsg() {
        setResult(RESULT_SUCCESS);
        setMsg(SUCCESS_MSG);
    }

    /**
     * 增加错误信息
     */
    public void addError(FB4Error error) {
        if (this.errors == null) {
            errors = new ArrayList<FB4Error>();
        }
        errors.add(error);
    }

    /**
     * 增加错误信息
     */
    public void addError(String errorCode, String errorMsg) {
        FB4Error error = new FB4Error(errorCode, errorMsg);
        addError(error);
    }

    /**
     * 增加错误信息
     */
    public void addError(String errorCode, String errorMsg, String errorData) {
        FB4Error error = new FB4Error(errorCode, errorMsg, errorData);
        addError(error);
    }


    public FB4Error buildError(Exception e) {
        FB4Error error = null;
        String exceptionName = e.getClass().getSimpleName();
        if (DO_ERROR_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, e.getMessage());
        } else if (DATA_ACCESS_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "数据库操作失败！");
        } else if (NULL_POINTER_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "调用了未经初始化的对象或者是不存在的对象！");
        } else if (IO_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "IO异常！");
        } else if (CLASS_NOT_FOUND_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "指定的类不存在！");
        } else if (ARITHMETIC_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "数学运算异常！");
        } else if (ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "数组下标越界！");
        } else if (ILLEGAL_ARGUMENT_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "方法的参数错误！");
        } else if (CLASS_NOT_FOUND_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "类型强制转换错误！");
        } else if (SECURITY_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "违背安全原则异常！");
        } else if (SQL_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "操作数据库异常！");
        } else if (NO_SUCH_METHOD_ERROR.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "方法末找到异常！");
        } else if (INTERNAL_ERROR.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "Java虚拟机发生了内部错误！");
        } else if (SCHEDULER_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "JOB任务调度出现异常！");
        } else if (DATA_INTEGRITY_VIOLATION_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "数据完整性冲突异常！");
        } else if (HTTP_CLIENT_ERROR_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "请求发生错误！");
        } else if (RETRYABLE_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "请求超时！");
        } else if (APIJSON_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "API请求JSON数据异常！");
        } else if (DUPLICATE_KEY_EXCEPTION.equals(exceptionName)) {
            error = new FB4Error(exceptionName, "数据库存在相同的唯一索引");
        } else {
            error = new FB4Error(SYSTEM_EXCEPTION, "程序内部错误，操作失败！");
        }
        return error;
    }

    public String buildErrorMessage(Exception e) {
        String errorMessage = null;
        String exceptionName = e.getClass().getSimpleName();
        if (DO_ERROR_EXCEPTION.equals(exceptionName)) {
            errorMessage = e.getMessage();
        } else if (DATA_ACCESS_EXCEPTION.equals(exceptionName)) {
            errorMessage = "数据库操作失败！";
        } else if (NULL_POINTER_EXCEPTION.equals(exceptionName)) {
            errorMessage = "调用了未经初始化的对象或者是不存在的对象！";
        } else if (IO_EXCEPTION.equals(exceptionName)) {
            errorMessage = "IO异常！";
        } else if (CLASS_NOT_FOUND_EXCEPTION.equals(exceptionName)) {
            errorMessage = "指定的类不存在！";
        } else if (ARITHMETIC_EXCEPTION.equals(exceptionName)) {
            errorMessage = ("数学运算异常！");
        } else if (ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION.equals(exceptionName)) {
            errorMessage = "数组下标越界!";
        } else if (ILLEGAL_ARGUMENT_EXCEPTION.equals(exceptionName)) {
            errorMessage = "方法的参数错误！";
        } else if (CLASS_CAST_EXCEPTION.equals(exceptionName)) {
            errorMessage = "类型强制转换错误！";
        } else if (SECURITY_EXCEPTION.equals(exceptionName)) {
            errorMessage = "违背安全原则异常！";
        } else if (SQL_EXCEPTION.equals(exceptionName)) {
            errorMessage = "操作数据库异常！";
        } else if (NO_SUCH_METHOD_ERROR.equals(exceptionName)) {
            errorMessage = "方法末找到异常！";
        } else if (INTERNAL_ERROR.equals(exceptionName)) {
            errorMessage = "Java虚拟机发生了内部错误！";
        } else if (SCHEDULER_EXCEPTION.equals(exceptionName)) {
            errorMessage = "JOB任务调度出现异常！";
        } else if (DATA_INTEGRITY_VIOLATION_EXCEPTION.equals(exceptionName)) {
            errorMessage = "数据完整性冲突异常！";
        } else if (HTTP_CLIENT_ERROR_EXCEPTION.equals(exceptionName)) {
            errorMessage = "请求发生错误！";
        } else if (RETRYABLE_EXCEPTION.equals(exceptionName)) {
            errorMessage = "服务调用失败！";
        } else if (APIJSON_EXCEPTION.equals(exceptionName)) {
            errorMessage = "API 请求JSON数据异常";
        } else {
            errorMessage = "程序内部错误，操作失败！";
        }
        return errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private void setPureMsg(String msg) {
        this.msg = msg;
    }

    public List<FB4Error> getErrors() {
        return errors;
    }

    public void setErrors(List<FB4Error> errors) {
        this.errors = errors;
    }

}
