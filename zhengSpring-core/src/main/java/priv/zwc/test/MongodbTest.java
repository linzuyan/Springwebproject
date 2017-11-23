package priv.zwc.test;

import com.mongodb.MongoClient;
import priv.zwc.Mongodb.MongodbManager;

import java.util.List;

/**
 * Created by admin on 2016/7/7.
 */
public class MongodbTest {
    public static void main(String[] args){
        MongoClient mongoClient= MongodbManager.getMongoClient("127.0.0.1",27017);
        List<String> list= mongoClient.getDatabaseNames();
        for (String s:list) {
            System.out.println(s);
        }
    }
}
