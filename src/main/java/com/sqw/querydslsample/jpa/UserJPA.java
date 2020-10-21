package com.sqw.querydslsample.jpa;

import com.sqw.querydslsample.bean.UserBean;

/**
 * @author shenqingwen
 * @date 2020/10/20
 */
public interface UserJPA extends BaseJPA<UserBean> {
    //../可以添加命名方法查询
    //public UserBean findOne(Long id);
}