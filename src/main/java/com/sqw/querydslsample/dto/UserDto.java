package com.sqw.querydslsample.dto;

import com.sqw.querydslsample.bean.FamilyMemberBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shenqingwen
 * @date 2020/10/21
 */
@Data
public class UserDto implements Serializable {

    private Long id;

    private String name;

    private int age;

    private String address;

    private List<FamilyMemberBean> familyMemberBeanList;
}
