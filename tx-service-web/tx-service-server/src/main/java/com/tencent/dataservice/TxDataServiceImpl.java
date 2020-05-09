package com.tencent.dataservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.tencent.dto.ClassMateDTO;
import com.tencent.dto.Pair;
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

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Slf4j
@Service
public class TxDataServiceImpl implements ITxDataService {

    @Autowired
    private ClassMateMapper classMateMapper;

    @Autowired
    private MenuMetaMapper menuMetaMapper;


    @Override
    public MsgResponse queryData(Pair pair) {
        checkPreconditions(pair);
        Page<ClassMateModel> page = new Page<>(pair.getNumPage(), 20);
        QueryWrapper<ClassMateModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_seed_id", pair.getSeedId());
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
        List<MenuModel> menuModels = menuMetaMapper.selectAll();
        menuModels.stream().collect(Collectors.groupingBy(MenuModel::getPSeedId))
                .forEach(new BiConsumer<Integer, List<MenuModel>>() {
                    @Override
                    public void accept(Integer integer, List<MenuModel> menuModels) {

                    }
                });

        return MsgResponse.ofSuccessMsg("操作成功",menuModels);
    }
}
