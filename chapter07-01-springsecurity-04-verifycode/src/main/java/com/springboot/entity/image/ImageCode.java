package com.springboot.entity.image;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 生成图形验证码
 *
 * @param request
 * @return
 */
@Data
@AllArgsConstructor
public class ImageCode {


    // 图片
    private BufferedImage image;

    // 随机数
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    public ImageCode() {
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.code = code;
        this.image = image;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(getExpireTime());
    }

}
