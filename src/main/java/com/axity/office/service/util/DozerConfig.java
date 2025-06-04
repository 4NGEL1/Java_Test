package com.axity.office.service.util;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Dozer configuration class
 * 
 * @author username@axity.com
 */
@Configuration
@Component
public class DozerConfig
{
  @Bean
  public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean( @Value("classpath*:mappings/*mappings.xml")
  Resource[] resources ) throws Exception
  {
    final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
    dozerBeanMapperFactoryBean.setMappingFiles( resources );
    return dozerBeanMapperFactoryBean;
  }
}
