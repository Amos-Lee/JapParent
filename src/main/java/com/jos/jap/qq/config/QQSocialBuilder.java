package com.jos.jap.qq.config;

import com.jos.jap.qq.connect.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QQSocialBuilder {

    public SocialConnectionFactory buildConnectionFactory() {
        QQApiAdapter apiAdapter = new QQApiAdapter();
        QQTemplate template = new QQTemplate("1234", "8888", "www.baidu.com", "www.baidu.com");
        QQServiceProvider serviceProvider = new QQServiceProvider(template);
        return new QQConnectionFactory("1234@4321", serviceProvider, apiAdapter);
    }
}
