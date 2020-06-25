package dyb.kafka.tool.kafka.process;

import org.springframework.stereotype.Component;

@Component
public class DataProcess {
	/**
	 * 将kafka数据转换成对象
	 * @param data
	 * @param t
	 * @return
	 */
	public Object data2Object(String data) {
		return null;
	}
	
	/**
	 * 是否过滤该数据
	 * @param t
	 * @return
	 */
	public boolean filterData(Object t) {
		return false;
	}
	
	/**
	 * 将数据转换成另一个目标对象
	 * @param t
	 * @return
	 */
	public Object transferData(Object t) {
		return null;
	}
	
	/**
	 * 存储数据
	 * @param v
	 */
	public void storeData(Object v) {
		
	}
}
