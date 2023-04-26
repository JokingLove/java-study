package com.joking.controller;

import com.joking.entity.AuditFlow;
import com.joking.service.AuditFlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author joking
 */
@RestController
@RequestMapping("/audit")
public class AuditFlowController {

    private final AuditFlowService auditFlowService;

    public AuditFlowController(AuditFlowService auditFlowService) {
        this.auditFlowService = auditFlowService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuditFlow> findById(@PathVariable Long id) {
        return ResponseEntity.ok(auditFlowService.findById(id));
    }
}
