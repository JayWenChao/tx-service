package com.tencent.controller;

import com.tencent.dataservice.TxDataServiceImpl;
import com.tencent.dto.Pair;
import com.tencent.kt.model.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public MsgResponse selectTableTkList(@RequestParam(name = "numPage",required = false,defaultValue = "1") Integer numPage,
                                         @RequestParam(name = "seedId",required = false ) Integer seedId,
                                         @RequestParam(name = "priceTop",required = false,defaultValue = "1") Integer priceTop) {
        return txDataService.queryData(Pair.builder()
                                           .numPage(numPage)
                                           .seedId(seedId)
                                           .priceTop(priceTop)
                                           .build());
    }

    @PostMapping("/menu")
    public MsgResponse selectMenu() {
        return txDataService.queryMenu();
    }


}
