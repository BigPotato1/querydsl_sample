package com.sqw.querydslsample.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shenqingwen
 * @date 2020/10/29
 */
@Data
@Entity
@Table(name = "t_family_member")
public class FamilyMemberBean implements Serializable {
    @Id
    @Column(name = "t_id")
    @GeneratedValue
    private Long id;
    @Column(name = "t_relationship")
    private String relationship;
    @Column(name = "t_user_id")
    private Long userId;

}
