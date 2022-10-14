package com.wdf.tp.common.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 实现读取yaml格式的{@link PropertySourceFactory}.
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:57
 * @since 1.0.0
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource)
        throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        try {
            Properties properties = factory.getObject();
            return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
        } catch (IllegalStateException e) {
            // 兼容@PropertySource中的ignoreResourceNotFound属性,参考上一层调用的源码
            if (e.getCause() instanceof FileNotFoundException) {
                throw (FileNotFoundException) e.getCause();
            }
            throw e;
        }
    }
}
