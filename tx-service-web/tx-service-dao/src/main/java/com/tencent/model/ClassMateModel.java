package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.math.BigDecimal;

/**
 * @author admin
 */
@TableName("tx_classmate")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class ClassMateModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "users")
    private String users;
    @TableField(value = "agency")
    private String agency;

    @TableField(value = "link")
    private String link;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
