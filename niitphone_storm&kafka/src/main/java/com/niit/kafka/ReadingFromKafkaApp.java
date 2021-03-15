package com.niit.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.spout.KafkaSpoutRetryExponentialBackoff;
import org.apache.storm.kafka.spout.KafkaSpoutRetryService;
import org.apache.storm.redis.bolt.RedisStoreBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.topology.TopologyBuilder;


import java.util.Properties;

/**
 * @author 章卜
 * @date 2020/11/13 8:35 上午
 */
public class ReadingFromKafkaApp {

    private static final String BOOTSTRAP_SERVER = "hadoop000:9092,hadoop000:9093,hadoop000:9094";
    private static final String TOPIC_NAME = "demo";
    private static final String REDIS_HOST = "192.168.43.30";
    private static final int REDIS_PORT = 6379;

    public static void main(String[] args) throws Exception {

        final TopologyBuilder builder = new TopologyBuilder();

//        Properties props = new Properties();
//        props.put("bootstrap.servers",BOOTSTRAP_SERVER);
//        props.put("acks","1");
//        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        KafkaBolt<String,String> kafkaBolt = new KafkaBolt<String,String>()
//                .withProducerProperties(props)
//                .withTopicSelector(new DefaultTopicSelector("test"))
//                .withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper<String, String>());

        builder.setSpout("kafkaSpout", new KafkaSpout<>(getKafkaSpoutConfig(BOOTSTRAP_SERVER,TOPIC_NAME)),1);
        builder.setBolt("splitBolt",new SplitBolt()).shuffleGrouping("kafkaSpout");
        builder.setBolt("countBolt",new CountBolt()).shuffleGrouping("splitBolt");
        //save to redis
        JedisPoolConfig poolConfig = new JedisPoolConfig.Builder()
                .setHost(REDIS_HOST).setPort(REDIS_PORT).setPassword("zhangbu").build();
        RedisStoreMapper storeMapper = new WordCountStoreMapper();
        RedisStoreBolt storeBolt = new RedisStoreBolt(poolConfig,storeMapper);
        builder.setBolt("storeBolt",storeBolt).shuffleGrouping("countBolt");

//        builder.setBolt("kafka_bolt",kafkaBolt,1).shuffleGrouping("bolt");

        if(args.length>0 && args[0].equals("cluster")) {
            try {
                StormSubmitter.submitTopology("ClusterReadingFromKafkaApp", new Config(), builder.createTopology());
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("LocalReadingFromKafkaApp", new Config(), builder.createTopology());
        }
    }

    private static KafkaSpoutConfig<String,String> getKafkaSpoutConfig(String bootstrapServers, String topic) {
        return KafkaSpoutConfig.builder(bootstrapServers,topic).setProp(ConsumerConfig.GROUP_ID_CONFIG,"kafkaSpoutTestGroup").setRetry(getRetryService()).setOffsetCommitPeriodMs(10_000)
                .build();
    }

    private static KafkaSpoutRetryService getRetryService() {
        return new KafkaSpoutRetryExponentialBackoff(KafkaSpoutRetryExponentialBackoff.TimeInterval.microSeconds(500),
                KafkaSpoutRetryExponentialBackoff.TimeInterval.milliSeconds(2),Integer.MAX_VALUE, KafkaSpoutRetryExponentialBackoff.TimeInterval.seconds(10));
    }
}
