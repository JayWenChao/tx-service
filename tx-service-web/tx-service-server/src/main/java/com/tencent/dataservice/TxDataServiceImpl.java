package com.tencent.dataservice;

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
        ClassMateModel classMateModel = classMateMapper.selectById(1);
        log.info("mode: [{}]", classMateModel);
        return MsgResponse.ofSuccessMsg("操作成功", classMateModel);
    }

}
