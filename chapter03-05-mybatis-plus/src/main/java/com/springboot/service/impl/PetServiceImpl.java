package com.springboot.service.impl;

import com.springboot.entity.Pet;
import com.springboot.mapper.PetMapper;
import com.springboot.service.IPetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Louis
 * @since 2019-06-02
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

}
