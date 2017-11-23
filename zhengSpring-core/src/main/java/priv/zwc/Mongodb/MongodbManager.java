package priv.zwc.Mongodb;

import com.mongodb.MongoClient;

/**
 * Created by admin on 2016/7/7.
 */
public class MongodbManager {

    public static MongoClient getMongoClient(String host,int port){
        MongoClient client=new MongoClient(host,port);
        return client;
    }

}
