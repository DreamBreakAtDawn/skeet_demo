package com.skeet.mybatisplus.modules.bond.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeet.mybatisplus.modules.bond.entity.PrimarySubscribeBondEntity;
import com.skeet.mybatisplus.modules.bond.entity.PrimaryUserBondRelationEntity;
import com.skeet.mybatisplus.modules.bond.model.vo.FrequentSubscribeBondVO;
import com.skeet.mybatisplus.modules.bond.service.IPrimarySubscribeBondService;
import com.skeet.mybatisplus.modules.bond.service.IPrimaryUserBondRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/4/27 17:00
 */
@Service
public class SubscribeBondBsServiceImpl {

    @Resource
    private IPrimarySubscribeBondService subscribeBondService;
    @Resource
    private IPrimaryUserBondRelationService userBondRelationService;

    public List<FrequentSubscribeBondVO> listSubscribeBonds() {
        Integer type = null;
        String openId = null;

        Wrapper<PrimaryUserBondRelationEntity> queryWrapper = Wrappers.lambdaQuery(PrimaryUserBondRelationEntity.class)
                .eq(PrimaryUserBondRelationEntity::getDataStatus, 1)
                // and ((查询条件-投给我的) or (查询条件-我关注的))
                .and(wrapper -> wrapper
                        .nested(tenderWrapper -> tenderWrapper
                                // 指定截标状态
                                .in(type == 1, PrimaryUserBondRelationEntity::getOrgId, "")
                                // 当用户点击了未截标/已截标,则只能查询等待发行、正在发行的债券
                                .in(type == 2, PrimaryUserBondRelationEntity::getOpenId, openId)
                                .eq(PrimaryUserBondRelationEntity::getRelationType, 1)
                        )
                        .or(collectWrapper -> collectWrapper
                                .in(PrimaryUserBondRelationEntity::getOpenId, openId)
                                .eq(PrimaryUserBondRelationEntity::getRelationType, 2)
                        )
                );

        List<PrimaryUserBondRelationEntity> relations = userBondRelationService.list(queryWrapper);
        List<Long> subscribeBondIds = relations.stream().map(PrimaryUserBondRelationEntity::getSubscribeBondId).collect(Collectors.toList());

        List<PrimarySubscribeBondEntity> list = subscribeBondService.lambdaQuery()
                .eq(PrimarySubscribeBondEntity::getInterceptFlag, "")
                .eq(PrimarySubscribeBondEntity::getDataStatus, 1)
                .ge(PrimarySubscribeBondEntity::getRealInterceptTime, "2021-04-27 00:00")
                .in(PrimarySubscribeBondEntity::getId, subscribeBondIds)
                .list();

        return FrequentSubscribeBondVO.buildData(list, relations);
    }
}
