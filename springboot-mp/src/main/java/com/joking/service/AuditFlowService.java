package com.joking.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joking.entity.AuditFlow;
import com.joking.mapper.AuditFlowMapper;
import org.springframework.stereotype.Service;

/**
 * @author joking
 */
@Service
public class AuditFlowService extends ServiceImpl<AuditFlowMapper, AuditFlow> {

    private final AuditFlowMapper auditFlowMapper;

    public AuditFlowService(AuditFlowMapper auditFlowMapper) {
        this.auditFlowMapper = auditFlowMapper;
    }


    public AuditFlow findById(Long id) {
        return this.getById(id);
    }



}
