package priv.zwc.test;

import priv.zwc.Redis.RedisManager;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/7/7.
 */
public class RedisTest {
    public static void main(String[] args){
        Jedis jedis= RedisManager.getClient();
        jedis.set("aaa","这是aaa");
        String aaa= jedis.get("aaa");
        Map<String,String> map=new HashMap();
        map.put("id","1");
        map.put("name","张三");
        map.put("age","15");
        jedis.hmset("user",map);
        List<String> names= jedis.hmget("user","name");
        System.out.println(aaa);
    }
}
