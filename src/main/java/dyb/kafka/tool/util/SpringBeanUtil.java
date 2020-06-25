package dyb.kafka.tool.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import dyb.kafka.tool.kafka.process.DataProcess;
@Component
public class SpringBeanUtil implements ApplicationContextAware,EnvironmentAware {
	private static ApplicationContext applicationContext=null;
	private static Environment environment=null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtil.applicationContext=applicationContext;
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		SpringBeanUtil.environment=environment;
	}
	
	public static Object getBeanByName(String name) {
		if(applicationContext!=null) {
			return applicationContext.getBean(name);
		}
		return null;
	}
	
	public static String getEnviromentParam(String key) {
		if(environment!=null) {
			return environment.getProperty(key);
		}
		return null;
	}
}
