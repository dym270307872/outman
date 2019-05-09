package cn.dym.outman.springmvc.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.cache.WebappTemplateLoader;

//import cn.dym.outman.springmvc.filter.MyHandlerInterceptor;

@Configuration
@EnableWebMvc // 启动SpringMVC
@ComponentScan("cn.dym.outman.springmvc.controller") // 启动组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {

	
	// 配置springMVC处理上传文件的信息
   /* @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(10485760000L);
        resolver.setMaxInMemorySize(40960);
        return resolver;
    }*/

    /*// 配置静态文件处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        *//**
         * addResourceHandler 指的是对外暴露的访问路径
         * addResourceLocations 指的是文件放置的目录
         * *//*
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");

        // href 链接方式 下载文件
        registry.addResourceHandler("/files/**")
                .addResourceLocations("classpath:/files/");

        *//**
         * 解决 No handler found for GET /favicon.ico 异常
         * *//*
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/favicon.ico");

    }*/
	
	
	@Bean
    public ViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=utf-8");
		resolver.setViewClass(JstlView.class);
		resolver.setOrder(1);
		return resolver;
    }
	
	// 配置JSP视图解析器
		/*@Bean
		public ViewResolver viewResolver2() {
			FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
			resolver.setPrefix("/WEB-INF/pages/");
			resolver.setSuffix(".html");
			resolver.setExposeContextBeansAsAttributes(true);
			return resolver;
		}*/
		
    
    
    /**
	 * freemaker作为视图解析器
	 * 
	 * @return
	 */
	@Bean
	public FreeMarkerViewResolver freemakerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setContentType("text/html;charset=utf-8");
		resolver.setPrefix("/WEB-INF/pages/ftl/");
		resolver.setSuffix(".ftl");
		resolver.setViewClass(FreeMarkerView.class);
		resolver.setOrder(0);
		return resolver;
	}
 /**
     * 配置freemarker
     * @param servletContext
     * @return
     */
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer(ServletContext servletContext) {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		@SuppressWarnings("deprecation")
		freemarker.template.Configuration tplCfg = new freemarker.template.Configuration();
		tplCfg.setDefaultEncoding("UTF-8");
		tplCfg.setTemplateLoader(new WebappTemplateLoader(servletContext));
		freeMarkerConfigurer.setConfiguration(tplCfg);
		return freeMarkerConfigurer;
	}
    
    
		/*@Bean
		public FreeMarkerConfigurer freemarkerConfig(){
			FreeMarkerConfigurer freeMarkerConfigurer	=	new FreeMarkerConfigurer();			
			//freeMarkerConfigurer.setResourceLoader();
			
			return freeMarkerConfigurer;
			
		}
		
		// 配置JSP视图解析器
		@Bean
		public ViewResolver viewResolver3() {
			FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
			resolver.setPrefix("/WEB-INF/pages/");
			resolver.setSuffix(".ftl");
			resolver.setContentType("text/html; charset=utf-8");
			resolver.setExposeContextBeansAsAttributes(true);
			resolver.setViewClass(FreeMarkerView.class);
//			resolver.setOrder(1);
			return resolver;
		}*/

	// 配置静态资源的处理
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); // 对静态资源的请求转发到容器缺省的servlet，而不使用DispatcherServlet
	}
	
	
	/**
	   * 如果项目的一些资源文件放在/WEB-INF/resources/下面
	   * 在浏览器访问的地址就是类似：https://host:port/projectName/WEB-INF/resources/xxx.css
	   * 但是加了如下定义之后就可以这样访问：
	   * https://host:port/projectName/resources/xxx.css
	   * 非必须
	   */
	 /* @Override
	  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/").addResourceLocations("/WEB-INF/resources/");
	  }*/
	
	
	 //@Override
   /* public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/").excludePathPatterns("/admin");    
    }*/

}