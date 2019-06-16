package com.springboot.repository;

import com.springboot.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Louis
 * @title: PetRepository
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:30
 */
public interface PetRepository extends JpaRepository<Pet, Integer> {


}
