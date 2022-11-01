package com.tenxcloud.clouddemo.db.repository;

import com.tenxcloud.clouddemo.db.dto.TenxCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @Author: XingWeiLei
 * @Date: 2021/01/12 11:37 上午
 * @Version:
 */
public interface TenxClusterRepository extends JpaRepository<TenxCluster, String>, JpaSpecificationExecutor<TenxCluster> {


}
