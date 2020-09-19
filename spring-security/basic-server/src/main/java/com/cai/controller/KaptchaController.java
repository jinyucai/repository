package com.cai.controller;

import com.cai.entity.CaptchaCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping(value = "/kaptcha")
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String text = defaultKaptcha.createText();
        session.setAttribute("kaptcha_key", new CaptchaCode(text, 2*60));
        BufferedImage image = defaultKaptcha.createImage(text);
        try(ServletOutputStream outputStream = response.getOutputStream();) {
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        }

    }

}
