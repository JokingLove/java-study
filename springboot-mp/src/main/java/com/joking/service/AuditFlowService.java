package com.joking.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joking.entity.AuditFlow;
import com.joking.entity.dto.OrderResultDTO;
import com.joking.mapper.AuditFlowMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author joking
 */
@Service
public class AuditFlowService extends ServiceImpl<AuditFlowMapper, AuditFlow> {

    private final AuditFlowMapper auditFlowMapper;

    public AuditFlowService(AuditFlowMapper auditFlowMapper) {
        this.auditFlowMapper = auditFlowMapper;
    }


//    @DS("#header-tenant_id")
    public AuditFlow findById(Serializable id) {
        return this.getById(id);
    }


    public void insertTest() {
        int i = auditFlowMapper.insertTest();
        System.out.println(i);
    }


    public void selectTest() {
        OrderResultDTO orderResultDTO = auditFlowMapper.selectTest();
        System.out.println(orderResultDTO);
    }



}
