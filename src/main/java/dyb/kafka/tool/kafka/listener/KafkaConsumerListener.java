package dyb.kafka.tool.kafka.listener;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import dyb.kafka.tool.kafka.process.DataProcess;
import dyb.kafka.tool.util.SpringBeanUtil;

public class KafkaConsumerListener {
	private static final Logger log=LoggerFactory.getLogger(KafkaConsumerListener.class);
	private DataProcess process=null;
	private void initProcess() {
		if(process==null) {
			synchronized(KafkaConsumerListener.class) {
				if(process==null) {
					String beanName=SpringBeanUtil.getEnviromentParam("kafka.processor.bean.name");
					if(StringUtils.isEmpty(beanName)) {
						process=(DataProcess)SpringBeanUtil.getBeanByName("dataProcess");
					}else {
						process=(DataProcess)SpringBeanUtil.getBeanByName(beanName);
					}
					if(process==null) {
						throw new NullPointerException("kafka 消费者数据处理器初始化失败");
					}
				}
			}
		}
	}
	
	public void dataDeal(List<ConsumerRecord<String, String>> data) {
		initProcess();
		try {
			if(data!=null) {
				for(ConsumerRecord<String, String> d:data) {
					log.debug("kafka数据，分区:[{}],offset[{}],值[{}]",d.partition(),d.offset(),d.value());
					//1.transfer data
					Object o=process.data2Object(d.value());
					//2.filter data
					if(process.filterData(o)) {
						continue;
					}
					//3.data to other
					Object transferd=process.transferData(o);
					//4.store
					process.storeData(transferd);
				}
			}
			
		}catch(Exception e) {
			log.error("data process error!",e);
		}
	}
}
