package com.springboot.util.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Louis
 * @title: PageResult
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/27 21:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
