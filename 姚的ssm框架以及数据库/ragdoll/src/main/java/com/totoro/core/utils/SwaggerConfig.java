package com.totoro.core.utils;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.EnableWebMvc;  
  
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;  
import com.mangofactory.swagger.models.dto.ApiInfo;  
import com.mangofactory.swagger.plugin.EnableSwagger;  
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import springfox.documentation.swagger2.annotations.EnableSwagger2; 


@Configuration  
@EnableWebMvc  
@EnableSwagger
public class SwaggerConfig {

private SpringSwaggerConfig springSwaggerConfig;  
	  
    
	  
    
    @Autowired  
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {  
        this.springSwaggerConfig = springSwaggerConfig;  
    }  
  
    /** 
     * 链式编程 来定制API样式 
     * 后续会加上分组信息 
     * 
     * @return 
     */  
    @Bean  
    public SwaggerSpringMvcPlugin customImplementation() {  
    	return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".rest.*");  
    }  
  
    private ApiInfo apiInfo() {
    	 ApiInfo apiInfo = new ApiInfo(  
                 "ums接口文档",  
                 "这里是所有的ums接口，里边含有说明，请自行测试",  
                 "My Apps API terms of service",  
                 "My Apps API Contact Email",  
                 "My Apps API Licence Type",  
                 "My Apps API License URL");  
         return apiInfo;  
    }  
	
}
