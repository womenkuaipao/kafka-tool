package dyb.kafka.tool.kafka.process;

import org.springframework.stereotype.Component;

@Component
public class MyProcessor extends DataProcess {
	@Override
	public Object data2Object(String data) {
		// TODO Auto-generated method stub
		System.out.println("测试我能否运行");
		return super.data2Object(data);
	}
}
