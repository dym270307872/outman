package cn.dym.outman.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
  
  public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
      @Override
      protected Class<?>[] getRootConfigClasses() {
          return new Class<?>[] { RootConfig.class };
     }
 
     @Override
     protected Class<?>[] getServletConfigClasses() {
         return new Class<?>[] { WebConfig.class };        //ָ指定Web配置类
     }
 
     @Override
     protected String[] getServletMappings() {    //将DispatcherServlet映射到"/"
         return new String[] { "/" };
     }
 
 }