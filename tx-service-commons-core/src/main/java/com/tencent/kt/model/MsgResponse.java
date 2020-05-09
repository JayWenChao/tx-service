package com.tencent.kt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import lombok.*;

import java.io.Serializable;

/**
 * @author admin
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MsgResponse implements Serializable {


    @JsonIgnoreProperties(ignoreUnknown = true)
    private Object data;

    private String msg;

    @NonNull
    private Integer code;
    private boolean flag;

    public static MsgResponse ofSuccessMsg(String msg) {
        return MsgResponse.builder().msg(msg).code(200).flag(true).build();
    }

    public static MsgResponse ofSuccessMsg() {
        return MsgResponse.builder().msg("sucess").code(200).flag(true).build();
    }

    public static MsgResponse success(Object data) {
        return MsgResponse.builder().msg("sucess").code(200).flag(true).data(data).build();
    }

    public static MsgResponse ofSuccessMsg(String msg, Object data) {
        return MsgResponse.builder().msg(msg).data(data).code(200).flag(true).build();
    }

    public static MsgResponse ofFail(int code, String msg) {
        return MsgResponse.builder().code(code).msg(msg).flag(false).build();
    }

    public static MsgResponse ofFail(String msg) {
        return MsgResponse.builder().code(500).msg(msg).flag(false).build();
    }

    public static MsgResponse ofThrowable(int code, Throwable throwable) {
        return MsgResponse.builder().msg(throwable.getClass().getName() + ", " + throwable.getMessage()).code(code).flag(false).build();
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}

