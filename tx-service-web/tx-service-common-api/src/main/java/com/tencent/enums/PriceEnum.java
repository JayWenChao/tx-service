package com.tencent.enums;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author admin
 */
@Getter
@AllArgsConstructor
public enum PriceEnum {

    LOW(0, "低于100元课程,大额课"),
    HIGH(1, "大于100课程,小额课");


    private Integer price;
    private String desc;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
