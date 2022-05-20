/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo.db.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * SnapshotApi
 *
 * @author huhu
 * @version v1.0
 * @date 2019-02-25 11:48
 */
@Entity
@Getter
@Setter
@Table(name = "snapshot_api", uniqueConstraints = {@UniqueConstraint(columnNames = {"clusterId", "namespace", "creatorId", "name"})})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SnapshotApi extends CreationBase {

    public static int Status_Normal = 0;
    public static int Status_Stopped = 1;
    public static int Status_Deleted = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String protocols;
    private String methods;
    private String path;
    private String name;
    private String authType;
    private Boolean stripPath;

    /**
     *  json structure contained information that the api published, eg. status, version, env and so on.
     */
    @Lob
    @Column(columnDefinition = "text")
    private String publishedInfo;

    /**
     * api， 0-正常， 1-停止  2-删除
     */
    private Integer status;

    private String extendsInfo;

    /**
     * API类型： 标签：tag,自定义sql:sql,注册API：regist
     */
    String apiType;

    private Long apiGroupId;

}
