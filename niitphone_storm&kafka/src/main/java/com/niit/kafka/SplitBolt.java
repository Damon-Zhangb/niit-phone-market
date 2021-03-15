package com.niit.kafka;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author 章卜
 * @date 2020/11/13 8:43 上午
 */
public class SplitBolt extends BaseRichBolt {

    private OutputCollector collector;

    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
       try {
           String value = input.getStringByField("value");
          String[] words = value.split("\t");
//          for(String word: words) {
              collector.emit(new Values(words[0], String.valueOf(1)));
//          }
           collector.ack(input);
       }catch (Exception e){
           e.printStackTrace();
           collector.fail(input);
       }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","count"));
    }
}
