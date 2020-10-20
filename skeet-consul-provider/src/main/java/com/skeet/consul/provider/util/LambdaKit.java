package com.skeet.consul.provider.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * 常用java8的lambda工具类
 *
 * @author zhangh
 * @date 2018年3月10日
 * @since
 */
public class LambdaKit {

    /**
     * List<T> 转为 Map<K,T>，过滤重复key值
     * <p>例：Lambdakit.listToMap(lstSku, OtaskSkuDTO::getSkuId)
     *
     * @param lstObj
     * @param keyMapper
     * @return
     */
    public static <K, T> Map<K, T> listToMap(Collection<T> list, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream().collect(Collectors.toMap(keyMapper, Function.identity(), (key1, key2) -> key2));
    }

    public static <K, V, T> Map<K, V> listToMap(Collection<T> list, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, (key1, key2) -> key2));
    }


    /**
     * 合计
     *
     * @param lstObj
     * @param mapper
     * @return
     */
    public static <T> Integer sum(Collection<T> list, ToIntFunction<? super T> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        return list.stream().mapToInt(mapper).sum();
    }

    /**
     * 合计
     *
     * @param lstObj
     * @param mapper
     * @return
     */
    public static <T> Double total(Collection<T> list, ToDoubleFunction<? super T> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return 0d;
        }
        return list.stream().mapToDouble(mapper).sum();
    }

    /**
     * 过滤
     * <p>例：LambdaKit.filter(lstOtask, task -> task.getStatus().equals(taskStatus))
     *
     * @param lstObj
     * @param predicate
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 数据根据条件去重
     * <p>例：LambdaKit.deduplication(lstOtask, Otask::getTaskNo)
     *
     * @param list
     * @param function
     * @param <T>
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> deduplication(List<T> list, Function<? super T, ? extends U> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(function))), ArrayList::new)
        );
    }

    /**
     * 转换集合属性
     *
     * @param lstObj
     * @param predicate
     * @return
     */
    public static <T, R> List<R> convert(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 获取唯一属性
     *
     * @param lstObj
     * @param predicate
     * @return
     */
    public static <T, R> List<R> convertUnique(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 按属性分组(相同属性Map.get->List)
     *
     * @param list
     * @param keyFunction
     * @return
     */
    public static <K, T> Multimap<K, T> group(Collection<T> list, com.google.common.base.Function<? super T, K> keyFunction) {
        if (CollectionUtils.isEmpty(list)) {
            list = Lists.newArrayList();
        }
        return Multimaps.index(list, keyFunction);
    }

    /**
     * 查找符合条件的第一个对象，如果没有符合条件的对象，则返回{@code null}
     *
     * <p>例：LambdaKit.findFirst(lstOtask, task -> task.getStatus().equals(taskStatus))
     *
     * @param list
     * @param predicate
     * @return
     */
    public static <T> T findFirst(List<T> list, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        list = list.stream().filter(predicate).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 根据对象的属性去重ValueAddedConsignmentSkuDTO
     *
     * @param list         原列表
     * @param keyExtractor 属性映射
     * @param <T>
     * @return 去重后的列表
     */
    public static <T> List<T> distinctByObjectAttribute(Collection<T> list, Function<? super T, Object> keyExtractor) {
        return list.stream().filter(distinctByKey(keyExtractor)).collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
