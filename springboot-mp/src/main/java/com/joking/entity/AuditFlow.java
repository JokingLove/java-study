package com.joking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 审批流程表
 * </p>
 *
 * @author yongqiang.guo
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor    //无参
@AllArgsConstructor  //带参
@TableName("t_audit_flow")
public class AuditFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String templateCode;


    private String flowCode;


    private Integer flowType;


    private String flowName;


    private String initUserCode;


    private String initUserName;


    private String relateId;


    private Integer auditStatus;


    private LocalDateTime auditStartTime;


    private LocalDateTime auditEndTime;


    private String rejectReasonCode;


    private String rejectReason;

    private String remark;


    private Integer deleteFlag;


    private LocalDateTime createTime;


    private String createUser;


    private LocalDateTime updateTime;


    private String updateUser;


}
