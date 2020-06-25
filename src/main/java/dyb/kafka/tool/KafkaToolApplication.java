package dyb.kafka.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaToolApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(KafkaToolApplication.class, args);
	}

}
