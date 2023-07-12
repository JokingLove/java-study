package com.joking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joking.entity.AuditFlow;
import com.joking.entity.dto.OrderResultDTO;

/**
 * <p>
 * 审批流程表 Mapper 接口
 * </p>
 *
 * @author yongqiang.guo
 * @since 2023-03-01
 */
public interface AuditFlowMapper extends BaseMapper<AuditFlow> {

    int insertTest();

    OrderResultDTO selectTest();

}
