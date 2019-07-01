package com.springboot.util.imagecode;

import com.springboot.constants.MyConstants;
import com.springboot.entity.image.ImageCode;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Louis
 * @title: generateImageCode
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/7/1 20:21
 */
public class generateImageCodeUtil {

    /**
     *
     * @description: //TODO 生成图形验证码
     * @author Louis
     * @date 2019/7/1 20:22
     * @param [request]
     * @return com.springboot.entity.image.ImageCode
     */
    public static ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", MyConstants.WIDTH);
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", MyConstants.HEIGHT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        int length = ServletRequestUtils.getIntParameter(request.getRequest(), "length", MyConstants.RANDOM_SIZE);
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, MyConstants.EXPIRE_SECOND);
    }

    /**
     *
     * @description: //TODO 生成随机背景条纹
     * @author Louis
     * @date 2019/7/1 20:22
     * @param [fc, bc]
     * @return java.awt.Color
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


}
