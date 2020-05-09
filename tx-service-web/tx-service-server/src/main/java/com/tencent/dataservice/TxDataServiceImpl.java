package com.tencent.dataservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.kt.model.MsgResponse;
import com.tencent.mapper.ClassMateMapper;
import com.tencent.model.ClassMateModel;
import com.tencent.service.ITxDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Slf4j
@Service
public class TxDataServiceImpl implements ITxDataService {

    @Autowired
    private ClassMateMapper classMateMapper;


    @Override
    public MsgResponse queryData() {
        Page<ClassMateModel>  page = new Page<>(1,20);
        Page<ClassMateModel> classMateModelPage = classMateMapper.selectPage(page, new QueryWrapper<ClassMateModel>());
        log.info("mode: [{}]", classMateModelPage);
        return MsgResponse.ofSuccessMsg("操作成功", classMateModelPage);
    }

}
