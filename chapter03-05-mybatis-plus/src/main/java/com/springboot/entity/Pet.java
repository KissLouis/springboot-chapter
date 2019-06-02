package com.springboot.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author Louis
* @since 2019-06-02
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "petId", type = IdType.AUTO)
    private Integer petId;

        @TableField("petName")
    private String petName;

            /**
            * 宠物品种
1：宠物猪
2：宠物狗
3：猫咪
            */
        @TableField("Breed")
    private Integer Breed;

        @TableField("modifyTime")
    private LocalDateTime modifyTime;

        @TableField("createTime")
    private LocalDateTime createTime;


}
