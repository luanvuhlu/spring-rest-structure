package com.luanvv.spring.springstructure.configurations.configs.web;

import javax.servlet.Filter;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.luanvv.spring.springstructure.configurations.formatters.DateFormatter;
import com.luanvv.spring.springstructure.configurations.formatters.LocalDateTimeFormatAnnotationFormatterFactory;
import com.luanvv.spring.springstructure.configurations.formatters.StringTrimmerConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport implements ApplicationContextAware {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		return requestMappingHandlerMapping;
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatterForFieldAnnotation(new LocalDateTimeFormatAnnotationFormatterFactory());
		registry.addConverter(new StringTrimmerConverter(true));
		super.addFormatters(registry);
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
    /**
     * Log income request
     *
     * @return the filter
     */
    @Bean
    public Filter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(5120);
        return filter;
    }

}
