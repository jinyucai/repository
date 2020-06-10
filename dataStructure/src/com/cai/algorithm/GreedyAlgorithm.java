package com.cai.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcastMap = new HashMap<String, HashSet<String>>();
        HashSet<String> broadcast1 = new HashSet<>();
        broadcast1.add("北京");
        broadcast1.add("上海");
        broadcast1.add("天津");
        HashSet<String> broadcast2 = new HashSet<>();
        broadcast2.add("广州");
        broadcast2.add("北京");
        broadcast2.add("深圳");
        HashSet<String> broadcast3 = new HashSet<>();
        broadcast3.add("成都");
        broadcast3.add("上海");
        broadcast3.add("杭州");
        HashSet<String> broadcast4 = new HashSet<>();
        broadcast4.add("上海");
        broadcast4.add("天津");
        HashSet<String> broadcast5 = new HashSet<>();
        broadcast5.add("杭州");
        broadcast5.add("大连");

        broadcastMap.put("k1", broadcast1);
        broadcastMap.put("k2", broadcast2);
        broadcastMap.put("k3", broadcast3);
        broadcastMap.put("k4", broadcast4);
        broadcastMap.put("k5", broadcast5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        ArrayList<String> select = new ArrayList<>();
        HashSet<String> temp = new HashSet<>();
        String maxSize = null;//存放最大长度的广播key

        while (allAreas.size() > 0){
            maxSize = null;

            for (String key: broadcastMap.keySet()){
                temp.clear();
                HashSet<String> areas = broadcastMap.get(key);
                temp.addAll(areas);
                //求出交集赋值给temp
                temp.retainAll(allAreas);
                HashSet<String> last = new HashSet<>();
                if(maxSize != null){
                    last = broadcastMap.get(maxSize);
                    last.retainAll(allAreas);
                }
                if(temp.size() > 0 && (maxSize == null || temp.size() > last.size())){
                    maxSize = key;
                }
            }
            if(maxSize != null){
                select.add(maxSize);
                allAreas.removeAll(broadcastMap.get(maxSize));
            }
        }

        System.out.println("结果" + select);
    }

}
