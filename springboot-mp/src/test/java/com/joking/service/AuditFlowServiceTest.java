package com.joking.service;

import com.joking.BaseSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditFlowServiceTest extends BaseSpringBootTest {

    @Autowired
    private AuditFlowService auditFlowService;

    public void testFindById() {
    }

    public void testInsertTest() {
    }

    @Test
    public void testSelectTest() {

        auditFlowService.selectTest();
    }
}