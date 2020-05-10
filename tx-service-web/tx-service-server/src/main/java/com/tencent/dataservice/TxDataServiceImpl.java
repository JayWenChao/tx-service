package com.tencent.dataservice;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.tencent.dto.ClassMateDTO;
import com.tencent.dto.Pair;
import com.tencent.dto.WrapperDTO;
import com.tencent.enums.PriceEnum;
import com.tencent.kt.model.MsgResponse;
import com.tencent.mapper.ClassMateMapper;
import com.tencent.mapper.MenuMetaMapper;
import com.tencent.model.ClassMateModel;
import com.tencent.model.MenuModel;
import com.tencent.service.ITxDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Slf4j
@Service
public class TxDataServiceImpl implements ITxDataService {

    private final static String KEY = "menu";
    private final static Cache<String, List<WrapperDTO>> CACHE = CacheBuilder.newBuilder()
            .initialCapacity(1024)
            .maximumSize(1000)
            .expireAfterWrite(8, TimeUnit.MINUTES)
            .concurrencyLevel(10)
            .recordStats()
            .build();


    @Autowired
    private ClassMateMapper classMateMapper;

    @Autowired
    private MenuMetaMapper menuMetaMapper;


    @Override
    public MsgResponse queryData(Pair pair) {
        checkPreconditions(pair);

        Page<ClassMateModel> page = new Page<>(pair.getNumPage(), 20);
        LambdaQueryWrapper<ClassMateModel> queryWrapper = Wrappers.<ClassMateModel>lambdaQuery().eq(ClassMateModel::getSeedId, pair.getSeedId());
        if (PriceEnum.LOW.getPrice().equals(pair.getPriceTop())) {
            queryWrapper.le(ClassMateModel::getPrice, 100);
        } else {
            queryWrapper.ge(ClassMateModel::getPrice, 100);
        }

        Page<ClassMateModel> classMateModelPage = classMateMapper.selectPage(page, queryWrapper);
        List<ClassMateDTO> result = classMateModelPage.getRecords().stream().map(classMateModel -> {
            ClassMateDTO classMateDTO = ClassMateDTO.builder().build();
            BeanUtils.copyProperties(classMateModel, classMateDTO);
            return classMateDTO;
        }).collect(Collectors.toList());
        log.info("mode: [{}]", result);
        return MsgResponse.ofSuccessMsg("操作成功", ImmutableMap.of("count", page.getTotal(), "result", result));
    }

    @Override
    public MsgResponse queryMenu() {

        List<WrapperDTO> resultList = Collections.emptyList();
        resultList = CACHE.getIfPresent(KEY);
        if (CollectionUtil.isEmpty(resultList)) {

            List<MenuModel> menuModels = menuMetaMapper.selectAll();
            Multimap<WrapperDTO, WrapperDTO> multimap = ArrayListMultimap.create();
            Flux.fromIterable(menuModels).subscribe(menuModel -> {
                WrapperDTO pwd = WrapperDTO.builder()
                        .name(menuModel.getPDescription())
                        .id(menuModel.getPSeedId())
                        .build();
                WrapperDTO cwd = WrapperDTO.builder()
                        .name(menuModel.getCDescription())
                        .id(menuModel.getCSeedId())
                        .build();
                multimap.put(pwd, cwd);
            });

            List<WrapperDTO> wds = Lists.newArrayListWithCapacity(8);
            Flux.fromIterable(multimap.keySet()).subscribe(wrapperDTO -> {
                wrapperDTO.setChildren(Lists.newArrayList(multimap.get(wrapperDTO)));
                wds.add(wrapperDTO);
            });
            CACHE.put(KEY, wds);
            resultList = wds;
        }

        return MsgResponse.ofSuccessMsg("操作成功", resultList);
    }
}
