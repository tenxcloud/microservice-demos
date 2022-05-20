package com.tenxcloud.clouddemo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:25
 **/
@Getter
@Setter
public class User implements Serializable {
    private String name;
    private String pwd;
}
