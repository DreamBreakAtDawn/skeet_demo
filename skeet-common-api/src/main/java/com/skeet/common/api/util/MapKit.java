package com.skeet.common.api.util;

import java.util.Map;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/15 19:52
 */
public class MapKit {

    public static <K, V> V putIfAbsent(Map<K, V> map, K key, V value) {
        V v = map.get(key);
        if (v == null) {
            map.put(key, value);
            v = value;
        }

        return v;
    }
}
