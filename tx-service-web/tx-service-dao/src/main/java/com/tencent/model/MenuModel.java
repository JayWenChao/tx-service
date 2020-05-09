package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author admin
 */
@TableName("tx_meta_class")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class MenuModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "p_description")
    private String pDescription;

    @TableField(value = "p_seed_id")
    private Integer pSeedId;

    @TableField(value = "c_description")
    private String cDescription;

    @TableField(value = "c_seed_id")
    private Integer cSeedId;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
