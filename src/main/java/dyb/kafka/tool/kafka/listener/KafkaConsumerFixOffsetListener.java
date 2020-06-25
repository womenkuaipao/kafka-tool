package dyb.kafka.tool.kafka.listener;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix="kafka",name="fix.offset.on",havingValue="true")
public class KafkaConsumerFixOffsetListener extends KafkaConsumerListener{
	/**
	 * 可以通过@TopicPartition监听多个topic，也可以通过topics
	 * @param data
	 */	
	@KafkaListener(containerFactory="kafkaListenerContainerFactory",
			topicPartitions= {
					@TopicPartition(topic = "${kafka.topic}",
							partitionOffsets= {@PartitionOffset(partition="${kafka.fix.offset.partition}",initialOffset="${kafka.fix.offset.start}")})
					})
	private void listener(List<ConsumerRecord<String, String>> data){
		super.dataDeal(data);
		if(data!=null) {
			for(ConsumerRecord<String, String> d:data) {
				System.out.println("KafkaConsumerListener"+d.offset()+":"+d.value());
			}
		}
		
	}
}