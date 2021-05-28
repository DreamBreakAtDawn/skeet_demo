package com.skeet.mybatisplus.modules.bond.model.vo;

import com.skeet.mybatisplus.modules.bond.entity.PrimarySubscribeBondEntity;
import com.skeet.mybatisplus.modules.bond.entity.PrimaryUserBondRelationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/4/27 15:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrequentSubscribeBondVO {

    /**
     * 申购券id
     */
    private Long subscribeBondId;

    /**
     * 债券代码
     */
    private String bondCode;

    /**
     * 债券简称
     */
    private String bondSimpleName;

    /**
     * 债券全称
     */
    private String bondFullName;

    /**
     * 实际截标时间
     */
    private String realInterceptTime;

    /**
     * 是否投给我的
     */
    private Integer bidToMeFlag;

    private String bondNameShow;

    private String interceptTimeShow;

    public static List<FrequentSubscribeBondVO> buildData(List<PrimarySubscribeBondEntity> bonds,
                                                          List<PrimaryUserBondRelationEntity> relations) {


        return Optional.ofNullable(bonds)
                .map(opBonds -> {
                    Set<Long> bidToMeBondIdSet = relations
                            .stream()
                            .filter(r -> r.getRelationType().equals(1))
                            .map(PrimaryUserBondRelationEntity::getSubscribeBondId)
                            .collect(Collectors.toSet());
                    return opBonds.stream()
                            .map(bond -> {
                                FrequentSubscribeBondVO vo = new FrequentSubscribeBondVO();
                                vo.setBidToMeFlag(bidToMeBondIdSet.contains(bond.getId()) ? 1 : 0);
                                return vo;
                            })
                            .collect(Collectors.toList());
                })
                .map(opBonds -> {
                    opBonds.sort(Comparator.comparing(FrequentSubscribeBondVO::getBidToMeFlag).reversed()
                    .thenComparing(Comparator.comparing(FrequentSubscribeBondVO::getRealInterceptTime).reversed())
                    .thenComparing(Comparator.comparing(FrequentSubscribeBondVO::getBondNameShow)));
                    return opBonds;
                })
                .orElseGet(ArrayList::new);
    }
}
