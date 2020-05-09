package com.tencent.controller;

import com.tencent.dataservice.TxDataServiceImpl;
import com.tencent.dto.Pair;
import com.tencent.kt.model.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class KtDataController {


    @Autowired
    private TxDataServiceImpl txDataService;


    @PostMapping("/query")
    public MsgResponse selectTableTkList(Pair pair) {
        return txDataService.queryData(pair);
    }

    @PostMapping("/menu")
    public MsgResponse selectMenu(Pair pair) {
        return txDataService.queryMenu();
    }



}
