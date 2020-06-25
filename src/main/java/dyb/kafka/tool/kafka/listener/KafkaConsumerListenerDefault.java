package dyb.kafka.tool.kafka.listener;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix="kafka",name="fix.offset.on",havingValue="false")
public class KafkaConsumerListenerDefault extends KafkaConsumerListener{
	/**
	 * 可以通过@TopicPartition监听多个topic，也可以通过topics
	 * @param data
	 */	
	@KafkaListener(containerFactory="kafkaListenerContainerFactory",topics="${kafka.topic}")
	private void listener(List<ConsumerRecord<String, String>> data){
		super.dataDeal(data);
		if(data!=null) {
			for(ConsumerRecord<String, String> d:data) {
				System.out.println("KafkaConsumerListenerDefault"+d.offset()+":"+d.value());
			}
		}
		
	}
}