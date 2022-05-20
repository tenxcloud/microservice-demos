package com.tenxcloud.clouddemo.db.repository;

import com.tenxcloud.clouddemo.db.dto.SnapshotApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @Author: XingWeiLei
 * @Date: 2021/01/11 2:20 下午
 * @Version:
 */
public interface SnapshotRepository extends JpaRepository<SnapshotApi, Long>, JpaSpecificationExecutor<SnapshotApi> {

    SnapshotApi findSnapshotApiById(Long Id);

    List<SnapshotApi> findSnapshotApisByApiGroupId(Long id);

}
