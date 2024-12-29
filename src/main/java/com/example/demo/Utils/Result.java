package com.example.demo.Utils;

import java.util.HashMap;
import java.util.Map;

// 统一返回的结果
public class Result {
    private Boolean success;
    private Integer code; // 自定义状态码，方便与前端交互
    private String message; // 状态码的解释信息
    private Map<String, Object> data = new HashMap<String, Object>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    // 私有化构造方法
//    private Result(Boolean success, Integer code, String message, Map<String, Object> data) {}
    private Result() {}

    // 成功静态方法
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功执行啦~");
        return result;
    }

    // 失败静态方法
    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.FAIL);
        result.setMessage("好像有点问题......");
        return result;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
