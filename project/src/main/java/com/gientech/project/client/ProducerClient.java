package com.gientech.project.client;

/**
 * @Author: Yuwei
 * @Date: 2022-10-02-16:48
 * @Description:
 */

import java.util.concurrent.Future;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


/**
 * 生产者
 */
public class ProducerClient {

    private static Producer<String, String> producer = KafkaUtil.getProducer();
    public static void sendToKafka(String topic,String processId,JSONObject bpmData) {
        try {
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,
                    processId, bpmData.toJSONString());
            Future<RecordMetadata> send = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("sendToKafka-发送至Kafka:" + "d+key-" + processId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
