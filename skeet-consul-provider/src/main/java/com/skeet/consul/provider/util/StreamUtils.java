package com.skeet.consul.provider.util;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: Stream转换工具类
 * @Author: echo
 * @Date: 2020/11/12 18:45
 * @Version: 1.0
 */
public class StreamUtils {

    /**
     * 将List转成指定Key,Value的Map
     *
     * @param list     list
     * @param keyField key
     * @param value    value
     * @return Map
     */
    public static <T, K, V> Map<K, V> toMap(List<T> list,
                                            Function<? super T, ? extends K> keyField,
                                            Function<? super T, ? extends V> value) {
        if (CollectionUtil.isEmpty(list)) {
            return Maps.newHashMapWithExpectedSize(0);
        }

        String a = "";

        return list.stream().collect(Collectors.toMap(keyField, value, (o1, o2) -> o1));
    }

    /**
     * 将List转成指定Key,Value的Map
     *
     * @param list     list
     * @param keyField key
     * @return Map
     */
    public static <T, K> Map<K, T> toMap(List<T> list, Function<? super T, ? extends K> keyField) {
        if (CollectionUtil.isEmpty(list)) {
            return Maps.newHashMapWithExpectedSize(0);
        }

        return list.stream().collect(Collectors.toMap(keyField, Function.identity(), (o1, o2) -> o1));
    }

    /**
     * 通过指定条件过滤
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @return 已过滤集合
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * 根据对象的属性去重
     *
     * @param list         原列表
     * @param keyExtractor 属性映射
     * @param <T>          对象类型
     * @return 去重后的列表
     */
    public static <T> List<T> distinct(Collection<T> list, Function<? super T, Object> keyExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream().filter(distinctByKey(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * 将对象put到map中，如果map已存在该对象，则Predicate的结果为false，否则为true
     *
     * @param keyExtractor 被比较的对象
     * @param <T>          对象类型
     * @return 比对结果
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 根据对象的任一属性去重
     *
     * @param list          原列表
     * @param keyExtractors 属性映射列表
     * @param <T>           对象类型
     * @return 去重后的列表
     */
    public static <T> List<T> anyDistinct(Collection<T> list, Function<T, Object>... keyExtractors) {
        return list.stream().filter(anyDistinctByKey(keyExtractors)).collect(Collectors.toList());
    }

    /**
     * 具体去重判断
     *
     * @param functions 被比较的对象属性列表
     * @param <T>       对象类型
     * @return 判断结果，true表示无重复值，false表示有重复值
     */
    private static <T> Predicate<T> anyDistinctByKey(Function<T, Object>... functions) {
        int length = functions.length;

        // 初始化判断重复的集合
        List<Set<String>> list = Lists.newArrayListWithCapacity(length);
        for (int i = 0; i < length; i++) {
            list.add(Sets.newHashSet());
        }

        return t -> {
            // 判断是否与之前的数据的重复
            boolean existFlag = consumeLoop(functions, list, t, true, Set::contains);

            // 如果没有重复的数据，则将对应的值加入到集合中
            if (!existFlag) {
                consumeLoop(functions, list, t, false, Set::add);
            }

            return !existFlag;
        };
    }

    /**
     * 遍历Function判断条件是否成立
     *
     * @param functions   对象与成员属性的映射数组
     * @param list        存储set的集合，需与{@code functions}等长
     * @param obj         被操作的对象
     * @param trueExist   条件成立时是否退出循环
     * @param biPredicate 对集合与元素的逻辑操作
     * @return {@code biPredicate}只需一次布尔结果为true时则返回true，全为false时才返回false
     */
    private static <T> boolean consumeLoop(Function<T, Object>[] functions,
                                           List<Set<String>> list,
                                           T obj,
                                           boolean trueExist,
                                           BiPredicate<Set<String>, String> biPredicate) {
        int length = functions.length;
        boolean result = false;
        for (int i = 0; i < length; i++) {
            Function<? super T, Object> function = functions[i];
            Object property = function.apply(obj);
            if (Objects.isNull(property)) {
                continue;
            }

            String str = property.toString();
            if (StringUtils.isBlank(str)) {
                continue;
            }

            Set<String> set = list.get(i);
            if (biPredicate.test(set, str)) {
                result = true;
            }

            if (trueExist && result) {
                break;
            }
        }

        return result;
    }

    /**
     * 查找符合条件的第一个对象，如果没有符合条件的对象，则返回{@code null}
     *
     * <p>例：LambdaKit.findFirst(lstOtask, task -> task.getStatus().equals(taskStatus))
     *
     * @param list      集合
     * @param predicate 条件
     * @return 第一个对象
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
     * 获取无重复值的集合
     *
     * @param list   源集合
     * @param mapper 要返回的集合类型
     * @return 结果集
     */
    public static <T, R> List<R> mapUnique(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 获取转换后的集合
     *
     * @param list   源集合
     * @param mapper 要返回的集合类型
     * @return 结果集
     */
    public static <T, R> List<R> map(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据指定条件分组
     *
     * @param list   源集合
     * @param mapper 映射条件
     * @param <T>    集合对象类型
     * @param <R>    映射后的Map的key类型
     * @return Map
     */
    public static <T, R> Map<R, List<T>> group(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        return list.stream().collect(Collectors.groupingBy(mapper));
    }

    /**
     * 根据指定条件分割
     *
     * @param list      源集合
     * @param predicate 映射条件
     * @param <T>       集合对象类型
     * @return Map
     */
    public static <T> Map<Boolean, List<T>> partition(Collection<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        return list.stream().collect(Collectors.partitioningBy(predicate));
    }

    /**
     * 通过指定条件过滤，并转换集合
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    要返回的集合类型
     * @return 已过滤集合
     */
    public static <T, R> List<R> filterAndMap(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * 通过指定条件过滤，并分组
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    分组逻辑
     * @return 分组后集合
     */
    public static <T, R> Map<R, T> filterAndToMap(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.toMap(mapper, Function.identity(), (o1, o2) -> o1));
    }

    /**
     * 通过指定条件过滤，并分组
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    分组逻辑
     * @return 分组后集合
     */
    public static <T, R> Map<R, List<T>> filterAndGroup(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.groupingBy(mapper));
    }

    /**
     * 任一元素匹配
     *
     * @param list      集合
     * @param predicate 匹配条件
     * @return 匹配结果
     */
    public static <T> boolean anyMatch(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        return list.stream()
                .anyMatch(predicate);
    }

    /**
     * 全部元素匹配
     *
     * @param list      待过滤集合
     * @param predicate 匹配条件
     * @return 匹配结果
     */
    public static <T> boolean allMatch(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        return list.stream()
                .allMatch(predicate);
    }


    public static void main(String[] args) {
        String number = "2852956131\n" +
                "3008829514\n" +
                "3007991200\n" +
                "3007348957\n" +
                "2852896019\n" +
                "3007223435\n" +
                "3007290293\n" +
                "3007283767\n" +
                "2852792953\n" +
                "3008709616\n" +
                "3003179321\n" +
                "3007987867\n" +
                "2853109679\n" +
                "3007775778\n" +
                "3007629480\n" +
                "2852826889\n" +
                "3007378668\n" +
                "3003185056\n" +
                "3004492841\n" +
                "3007369423\n" +
                "3007427290\n" +
                "2850476508\n" +
                "3007296354\n" +
                "3003416732\n" +
                "2852956124\n" +
                "2852826889\n" +
                "3007408036\n" +
                "3007311762\n" +
                "3007229158\n" +
                "2853600639\n" +
                "3007931256\n" +
                "3007258366\n" +
                "2852956125\n" +
                "3007367824\n" +
                "2852786312\n" +
                "2852238899\n" +
                "3007228263\n" +
                "3007409012\n" +
                "3007229106\n" +
                "2850271400\n" +
                "3007347608\n" +
                "2850712507\n" +
                "3003412195\n" +
                "2851703634\n" +
                "2852754840\n" +
                "2852797923\n" +
                "2852831105\n" +
                "2852754840";

        String[] strings = StringUtils.split(number, "\n");
        List<String> results = StreamUtils.distinct(Arrays.asList(strings), Function.identity());
        results.forEach(System.out::println);
    }
}
