package com.skeet.consul.provider.util;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.skeet.consul.provider.model.domain.PageDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author huanggh
 * @since 2016年8月24日
 * 通用转化类
 */
public class SimpleConverter {
    /**
     * 根据原生对象生成新对象
     * <p>
     * <pre>
     * 例如把 User 对象 转为 UserDTO：
     *  UserDTO userDTO = SimpleConverter.convert(user, UserDTO.class);
     * </pre>
     *
     * @param orig 原始对象
     * @param dest 目标对象class
     * @return 目标对象实例
     */
    public static <T> T convert(Object orig, Class<T> dest) {
        return convert(orig, dest, (String[]) null);
    }

    /**
     * @param ignoreProperties 不做赋值属性
     */
    public static <T> T convert(Object orig, Class<T> dest, String... ignoreProperties) {
        if (Objects.isNull(orig)) {
            return null;
        }
        T obj = null;
        try {
            obj = dest.newInstance();
            BeanUtils.copyProperties(orig, obj, ignoreProperties);
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", orig, obj), e);
        }

        return obj;
    }

    /**
     * 根据原生集合生成新集合
     * <p>
     * <pre>
     * List<UserDTO> list = Arrays.asList(new UserDTO("name", "country"));
     * List<UserVO> vos = SimpleConverter.convert(list, UserVO.class);
     * </pre>
     */
    public static <F, T> List<T> convert(List<F> orig, final Class<T> dest) {
        if (Objects.isNull(orig)) {
            return null;
        }
        List<T> list = new ArrayList<>();

        for (F o : orig) {
            T t = convert(o, dest);
            list.add(t);
        }

        return list;
    }

    /**
     * 把一个分页对象，转化为另一个分页对象
     * <p>
     * <pre>
     * UserVO userVO = new UserVO();
     * userVO.setName("hong");
     * userVO.setAge("123");
     * List<UserVO> userVOs = Arrays.asList(userVO);
     * PageDTO<UserVO> pageVO = new PageDTO<UserVO>(userVOs, 10);
     * PageDTO<UserDTO> pageDTO = SimpleConverter.convert(pageVO, UserDTO.class);
     * </pre>
     */
    public static <T> PageDTO<T> convert(PageDTO<?> orig, Class<T> dest) {
        List<?> origDatas = orig.getData();

        List<T> destDatas = convert(origDatas, dest);

        return new PageDTO<>(destDatas, orig.getTotal());
    }

    /**
     * 解析指定分隔符的字符串为集合，去除空格及多余的分隔符
     *
     * @param str       字符串
     * @param separator 分隔符
     * @param isRepeat  是否允许重复
     */
    public static List<String> convertStr(String str, String separator, boolean isRepeat) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(separator);
        List<String> list = Splitter.on(separator).trimResults()
                .omitEmptyStrings().splitToList(str);
        if (!isRepeat) {
            return ImmutableSet.copyOf(list).asList();
        }
        return list;
    }

    /**
     * 将逗号分隔的字符串转换成集合，过滤掉多余的空格和逗号
     * <br>convertStr(" a, b ,c ") returns ["a", "b", "c"].
     *
     * @param str 逗号分隔的字符串
     */
    public static List<String> convertStr(String str) {
        return convertStr(str, ",", true);
    }

    /**
     * 将集合按指定分隔符连接,排除空
     *
     * @param list      集合
     * @param separator 分隔符
     */
    public static String convertList(List<?> list, String separator) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(separator);
        return Joiner.on(separator).skipNulls().join(list.iterator());
    }

    /**
     * 过滤掉字符串中其它字符，只保留数字
     *
     * @param character 字符串
     */
    public static String filterCharacter(String character) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < character.length(); i++) {
            if (Character.isDigit(character.charAt(i))) {
                buffer.append(character.charAt(i));
            }
        }
        return buffer.toString();
    }

    /**
     * 获取值为null的属性名称集合
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
