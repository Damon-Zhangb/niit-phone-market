package com.niit.trident;

import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.tuple.ITuple;

/**
 * @author 章卜
 * @date 2021/1/4 9:58 下午
 */
public class WordCountStoreMapper implements RedisStoreMapper {

    private RedisDataTypeDescription description;
    private final String hashKey = "wordCount1";

    public WordCountStoreMapper() {
        description = new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH, hashKey);
    }

    @Override
    public RedisDataTypeDescription getDataTypeDescription() {
        return description;
    }

    @Override
    public String getKeyFromTuple(ITuple iTuple) {
        return iTuple.getStringByField("word");
    }

    @Override
    public String getValueFromTuple(ITuple iTuple) {
        return iTuple.getStringByField("count");
    }
}
