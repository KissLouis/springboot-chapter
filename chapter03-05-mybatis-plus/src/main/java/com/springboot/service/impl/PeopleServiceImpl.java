package com.springboot.service.impl;

import com.springboot.entity.People;
import com.springboot.mapper.PeopleMapper;
import com.springboot.service.IPeopleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Louis
 * @since 2019-06-02
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)// 表明此类上所有方法上的事务都是CGLib方式代理的
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {

}
