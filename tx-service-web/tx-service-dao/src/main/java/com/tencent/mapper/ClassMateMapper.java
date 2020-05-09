package com.tencent.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.kt.constants.mysql.MysqlDbConstants;
import com.tencent.model.ClassMateModel;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 */
@Repository
@DS(value = MysqlDbConstants.DB_NAME)
public interface ClassMateMapper extends BaseMapper<ClassMateModel> {

}
