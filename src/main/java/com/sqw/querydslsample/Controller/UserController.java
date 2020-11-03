package com.sqw.querydslsample.Controller;

import com.github.dozermapper.core.Mapper;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sqw.querydslsample.bean.FamilyMemberBean;
import com.sqw.querydslsample.bean.QFamilyMemberBean;
import com.sqw.querydslsample.bean.QUserBean;
import com.sqw.querydslsample.bean.UserBean;
import com.sqw.querydslsample.dto.FamilyMemberDto;
import com.sqw.querydslsample.dto.UserDto;
import com.sqw.querydslsample.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    @Autowired
    Mapper objectMapper;


    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
        System.out.println("init JPAQueryFactory successfully");
    }

    /**
     * 查询全部数据并根据id倒序
     * http://localhost:8080/user/queryAll
     *
     * @return
     */
    @GetMapping(value = "/queryAll")
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
     * http://localhost:8080/user/detail/2
     *
     * @param id 主键编号
     * @return
     */
    @GetMapping(value = "/detail/{id}")
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
    @GetMapping(value = "/detail_2/{id}")
    public UserBean detail_2(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回指定id的单条数据
        return userJPA.findOne(_Q_user.id.eq(id)).get();
    }
//    上面的两种风格都是可以根据id字段返回指定的单条数据，当然在上面的编写看来还是使用SpringDataJPA &QueryDSL要简单些，
//    也只是在简单的查询情况下，整合风格要比纯QueryDSL风格要简便，但是如果添加排序、模糊查询时还是纯QueryDSL编写更简单一些。

    /**
     * 实现查询后返回自定义dto对象,自定义dto对象相对于Entity类(即UserBean)中减少了字段 pwd,即返回信息不展示密码
     * 完全QueryDSL风格
     *
     * @param id 主键编号
     * @return
     */
    @GetMapping(value = "/detail_3/{id}")
    public UserDto detail_3(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        return queryFactory
                .select(
                        Projections.bean(
                                UserDto.class,
                                _Q_user.id,
                                _Q_user.name,
                                _Q_user.address,
                                _Q_user.age
                        )
                )
                .from(_Q_user)//查询源
                .where(_Q_user.id.eq(id))//指定查询具体id的数据
                .fetchOne();//执行查询并返回单个结果
    }

    /**
     * 根据名称模糊查询
     * http://localhost:8080/user/likeQueryWithName?name=李四
     *
     * @param name 用户名字
     * @return
     */
    @GetMapping(value = "/likeQueryWithName")
    public List<UserBean> likeQueryWithName(String name) {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;

        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.name.like(name))//根据name模糊查询
                .fetch();//执行查询并返回结果集
    }

    @GetMapping("/familyMembers")
    public List<FamilyMemberBean> getUserFamilyMembers() {
        QUserBean qUserBean = QUserBean.userBean;
        QFamilyMemberBean qFamilyMemberBean = QFamilyMemberBean.familyMemberBean;
        var query = queryFactory.select(qFamilyMemberBean).from(qFamilyMemberBean, qUserBean).where(qFamilyMemberBean.userId.eq(qUserBean.id)).fetch();
        return query;
    }

    /**
     * 一对多的两表联合查询，并返回自定义的Dto
     * http://localhost:8080/user/userAttachFamilyMembers
     *
     * @return
     */
    @GetMapping("/userAttachFamilyMembers")
    public List<UserDto> getUserAttachFamilyMember() {
        QUserBean qUserBean = QUserBean.userBean;
        QFamilyMemberBean qFamilyMemberBean = QFamilyMemberBean.familyMemberBean;
        List<UserBean> userBeanList = queryFactory.select(qUserBean).from(qUserBean).fetch();

        List<UserDto> userDtoList = new ArrayList<>();
        userBeanList.forEach(userBean -> {
            UserDto userDto = objectMapper.map(userBean, UserDto.class);
            List<FamilyMemberDto> familyMemberDtoList = queryFactory.select(
                    Projections.bean(
                            FamilyMemberDto.class,
                            qFamilyMemberBean.relationship
                    )
            )
                    .from(qFamilyMemberBean)
                    .where(qFamilyMemberBean.userId.eq(userDto.getId())).fetch();
            userDto.setFamilyMemberDtoList(familyMemberDtoList);
            userDtoList.add(userDto);
        });

        return userDtoList;
    }
}

