package priv.zwc.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by admin on 2016/6/3.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<String>();
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceKey();
    }

    public static String getDataSourceKey(){
        return threadLocal.get();
    }
    public static void changeDataSourceKey(String datasource){
        threadLocal.set(datasource);
    }
}
