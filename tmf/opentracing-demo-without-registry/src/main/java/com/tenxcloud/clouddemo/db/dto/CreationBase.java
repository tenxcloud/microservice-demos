/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * CreationBase
 *
 * @author huhu
 * @version v1.0
 * @date 2019-02-25 14:35
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CreationBase {
    String clusterId;
    String namespace;
    Integer creatorId;
    Long createTime;
    Long updateTime;
    String description;
}
