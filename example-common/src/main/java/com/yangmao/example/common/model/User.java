package com.yangmao.example.common.model;

import java.io.Serializable;

/**
 * 用户实体类
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class User implements Serializable {
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
