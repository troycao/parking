package com.troy.parking.common;


import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Title: 统一返回包装对象
 * @Auther: ZhangYuSai
 * @Date: 2018/8/2 09:42
 * @version: 1.0.0
 */
public class Response<T> implements Serializable {
    private String code;
    private String message;
    private boolean success;
    private T result;

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("message='" + message + "'")
                .add("success=" + success)
                .add("result=" + result)
                .toString();
    }
    public static Response ok(){
        return new Response(true);
    }
    public static Response ok(Object result){
        if(result!=null){
            Response res = new Response();
            res.result = result;
            res.success = true;
            res.code = "0";
            res.message = "success";
            return res;
        }
        return Response.failed();
    }
    public static Response failed(){
        return new Response(false);
    }
    public static Response failed(String message){
        return new Response(message);
    }
    public Response() {
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public Response(T result) {
        if(result!=null){
            this.result = result;
            this.success = true;
            this.code = "0";
            this.message = "success";
        }
    }
    public Response(Throwable ex) {
        this.message = ex.getMessage();
        this.success = false;
        this.code = "0";
    }
    public Response(String message) {
        this.message = message;
        this.success = false;
        this.code = "0";
    }

    private Response(boolean bool) {
        if(bool){
            this.code = "1";
            this.message = "success";
        }else{
            this.code = "0";
            this.message = "failed";
        }
        this.success = bool;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        if(result!=null){
            this.result = result;
            this.success = true;
            this.code = "0";
            this.message = "success";
        }
    }
}