package com.tencent.service;

import com.google.common.base.Preconditions;
import com.tencent.dto.Pair;
import com.tencent.kt.model.MsgResponse;

/**
 * @author admin
 */
@FunctionalInterface
public interface ITxDataService {


    public MsgResponse queryData(Pair pair);


    default MsgResponse queryMenu(){
        return MsgResponse.ofSuccessMsg();
    }


    default void checkPreconditions(Pair pair) {
        Preconditions.checkNotNull(pair, "对象不能为空");
        Preconditions.checkArgument(pair.getNumPage() >= 1, "页号必须大于0");
    }

}
