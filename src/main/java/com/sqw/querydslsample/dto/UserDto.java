package com.sqw.querydslsample.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author shenqingwen
 * @date 2020/10/21
 */
@Data
public class UserDto implements Serializable {

    private Long id;

    private String userName;

    private int age;

    private String address;
}
