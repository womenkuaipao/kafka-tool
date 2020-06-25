package dyb.kafka.tool.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
@Configuration
public class KafkaConfig {
	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServer;
	@Value ("${kafka.group.id}")
	private String groupId;
	@Value ("${kafka.partition.num}")
	private Integer partitionNum;

	private Map<String,Object> getKafkaConsumerConfig(){
		Map<String,Object> config=new HashMap<String,Object>();
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_tool_"+groupId);
		config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
		config.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 100);
		return config;
	}

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
	kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory=new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(getKafkaConsumerConfig()));
		factory.setConcurrency(partitionNum==null?1:partitionNum);
		factory.setBatchListener(true);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}

}
