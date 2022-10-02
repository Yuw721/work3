package com.gientech.project.client;

/**
 * @Author: Yuwei
 * @Date: 2022-10-02-16:49
 * @Description:
 */

import java.util.Arrays;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 消费者
 */
public class ConsumerClient {

    public  static KafkaConsumer<String, String> consumer = null;

    public static void main(String[] args) {
        fecthKafka();
    }

    public static void fecthKafka() {
        consumer = KafkaUtil.getConsumer("testGroup1","oaadmin","NTA4YjRhZDBmYjQ3"); //group
        consumer.subscribe(Arrays.asList("3_kjczxsmrtj"));//topic

        int i=0;
        while (true) {
            ConsumerRecords<String, String> records ;
            try {
                records = consumer.poll(Long.MAX_VALUE);//毫秒
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

            for (ConsumerRecord<String, String> record : records) {
                System.out.println("fetched from partition " + record.partition() + ", offset: " + record.offset() + ",key: " + record.key() + ",value:" + record.value() );
                i++;
                System.out.println(i);
            }

            try {
                consumer.commitSync();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }



        }
    }
}
