package com.sq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Classname propertiesFiles
 * @Description TODO
 * @Date 2019\3\9 0009 11:12
 * @Created by WenJunChi
 */
@Configuration
@Component
@Data
public class PropertiesFilesConfig {
    @Value("${phpinteface.url}")
    private String phpUrl;
    @Value("${phpinteface.username}")
    private String username;
    @Value("${phpinteface.password}")
    private String password;
    @Value("${phpinteface.forcerefresh}")
    private boolean forcerefresh;


}
