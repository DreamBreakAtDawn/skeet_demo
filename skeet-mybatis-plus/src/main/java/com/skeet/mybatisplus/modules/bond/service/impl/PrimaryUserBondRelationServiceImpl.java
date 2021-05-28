package com.skeet.mybatisplus.modules.bond.service.impl;

import com.skeet.mybatisplus.modules.bond.entity.PrimaryUserBondRelationEntity;
import com.skeet.mybatisplus.modules.bond.mapper.PrimaryUserBondRelationEntityMapper;
import com.skeet.mybatisplus.modules.bond.service.IPrimaryUserBondRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员与申购债券关系表 服务实现类
 * </p>
 *
 * @author chengsj
 * @since 2021-04-27
 */
@Service
public class PrimaryUserBondRelationServiceImpl extends ServiceImpl<PrimaryUserBondRelationEntityMapper, PrimaryUserBondRelationEntity> implements IPrimaryUserBondRelationService {

}
