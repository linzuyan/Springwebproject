package priv.zwc.Common;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/4/18.
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String format ="yyyy-MM-dd";
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return null;
    }

    private Object process(Object value){

        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}
