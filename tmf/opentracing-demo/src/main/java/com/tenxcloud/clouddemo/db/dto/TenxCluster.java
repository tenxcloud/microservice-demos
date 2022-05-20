/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo.db.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * TenxCluster
 *
 * @author huhu
 * @version v1.0
 * @date 2019-02-25 11:27
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tenx_clusters")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenxCluster {
    @Id
    String id;
    String name;
    String protocol;
    String host;
    @Lob
    @Column(columnDefinition = "text")
    String token;
    String version;
    String description;
    Date createTime;

}
