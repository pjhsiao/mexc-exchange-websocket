package invest.robot.mexc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

	private ThreadPoolTaskExecutor executor;

	@Value("${threadPool.keepAliveSeconds}")
	private int keepAliveSeconds;
	@Value("${threadPool.threadNamePrefix}")
	private String threadNamePrefix;
	@Value("${threadPool.corePoolSize}")
	private int corePoolSize;
	@Value("${threadPool.maxPoolSize}")
	private int maxPoolSize;
	@Value("${threadPool.queueCapacity}")
	private int QueueCapacity;

	@Bean
	 public ThreadPoolTaskExecutor threadPool() {
		 executor = new ThreadPoolTaskExecutor();  
		 executor.setCorePoolSize(corePoolSize);
		 executor.setMaxPoolSize(maxPoolSize);
		 executor.setQueueCapacity(QueueCapacity);
         executor.setAllowCoreThreadTimeOut(true);
         executor.setThreadNamePrefix(threadNamePrefix);
         executor.setKeepAliveSeconds(keepAliveSeconds);
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
         return executor;
     }
	@PreDestroy
	public void  destroy () {
		if(null != executor) {
			executor.shutdown();
		}
	}
}
