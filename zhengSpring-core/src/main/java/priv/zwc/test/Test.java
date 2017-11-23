package priv.zwc.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/21.
 */
public class Test {
    public static void main(String[] args){
        HashMap<Integer,Object> map=new HashMap<Integer, Object>();
        map.put(1,"map1");
        map.put(2,"map2");
        map.put(2,"map3");

        HashSet<Integer> set=new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(2);

        for (Integer item: map.keySet()) {
            System.out.println(map.get(item));
        }

        for (Integer item: set) {
            System.out.println(item);
        }
    }
}
