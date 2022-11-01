package com.tenxcloud.clouddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tag  from skywalking .
 *
 * @author bing.zheng
 * @date 2022/7/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private String key;

    private String value;

    @Override
    public String toString() {
        return key + "=" + value;
    }

}
