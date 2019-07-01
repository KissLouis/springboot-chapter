package com.springboot.controller.images;

import com.springboot.constants.MyConstants;
import com.springboot.entity.image.ImageCode;
import com.springboot.util.imagecode.generateImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Louis
 * @title: ValidateCodeController
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/30 16:49
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private SessionStrategy sessionStrategy;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = generateImageCodeUtil.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), MyConstants.SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
