package com.niit.niitphone.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.Random;

/**
 * 验证码配置类
 *
 * @author 章卜
 * @since 2020-12-16
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "6");
        properties.put("kaptcha.textproducer.char.length", "6");
        properties.put("kaptcha.image.height", "36");
        properties.put("kaptcha.textproducer.font.size", "32");
        properties.put("kaptcha.textproducer.char.string", "0987654321");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.noise.color", "black");
        properties.put("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        properties.put("kaptcha.background.clear.from", "211,211," + color());
        properties.put("kaptcha.background.clear.to", color() + ",220," + color());
        properties.put("kaptcha.textproducer.font.color", color() + "," + color() + "," + color());

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    private static int color() {
        return new Random().nextInt(254);
    }

}
