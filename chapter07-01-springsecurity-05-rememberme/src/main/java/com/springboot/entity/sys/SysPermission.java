package com.springboot.entity.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Louis
 * @since 2019-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String permissionId;

    private String permissionName;

    private String description;

    private String url;

    /**
     * 权限级别：一级菜单，二级菜单
     */
    private Integer level;

    private Integer parentId;

    private Integer type;

    private Integer orderNum;

    private String icon;

    /**
     * 状态：1有效;0删除
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
