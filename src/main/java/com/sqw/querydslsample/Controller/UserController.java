package com.sqw.querydslsample.Controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sqw.querydslsample.bean.QUserBean;
import com.sqw.querydslsample.bean.UserBean;
import com.sqw.querydslsample.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author shenqingwen
 * @date 2020/10/20
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserJPA userJPA;

    //实体管理者
    @Autowired
    private EntityManager entityManager;

    //JPA查询工厂
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
        System.out.println("init JPAQueryFactory successfully");
    }

    /**
     * 查询全部数据并根据id倒序
     *
     * @return
     */
    @RequestMapping(value = "/queryAll")
    public List<UserBean> queryAll() {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        // 如果selectFrom参数的实体类型不是UserBean那fetch方法返回集合的类型也不是List<UserBean>
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .orderBy(_Q_user.id.desc())//根据id倒序
                .fetch();//执行查询并获取结果集
    }

    /**
     * 完全QueryDSL风格
     * 查询详情
     *
     * @param id 主键编号
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    public UserBean detail(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.id.eq(id))//指定查询具体id的数据
                .fetchOne();//执行查询并返回单个结果
    }

    /**
     * SpringDataJPA整合QueryDSL风格
     * SpringDataJPA & QueryDSL实现单数据查询
     *
     * @param id 主键编号
     * @return
     */
    @RequestMapping(value = "/detail_2/{id}")
    public UserBean detail_2(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回指定id的单条数据
        return userJPA.findOne(_Q_user.id.eq(id)).get();
    }
//    上面的两种风格都是可以根据id字段返回指定的单条数据，当然在上面的编写看来还是使用SpringDataJPA &QueryDSL要简单些，
//    也只是在简单的查询情况下，整合风格要比纯QueryDSL风格要简便，但是如果添加排序、模糊查询时还是纯QueryDSL编写更简单一些。

    /**
     * 根据名称模糊查询
     *
     * @param name 用户名字
     * @return
     */
    @RequestMapping(value = "/likeQueryWithName")
    public List<UserBean> likeQueryWithName(String name) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;

        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.name.like(name))//根据name模糊查询
                .fetch();//执行查询并返回结果集
    }

}

