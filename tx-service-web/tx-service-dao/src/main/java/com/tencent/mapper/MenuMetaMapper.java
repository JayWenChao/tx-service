package com.tencent.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.kt.constants.mysql.MysqlDbConstants;
import com.tencent.model.ClassMateModel;
import com.tencent.model.MenuModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@Repository
@DS(value = MysqlDbConstants.DB_NAME)
public interface MenuMetaMapper extends BaseMapper<ClassMateModel> {

    @Select("select *from tx_meta_class")
    List<MenuModel> selectAll();
}
