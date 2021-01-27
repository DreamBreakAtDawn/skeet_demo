package com.skeet.consul.provider.mine.frame.pinyin4j;

import cn.hutool.core.comparator.PinyinComparator;
import com.google.common.collect.Lists;
import com.skeet.consul.provider.model.entity.Person;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/21 17:20
 */
public class MainClass {

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        sorted();
//        pinyin4j();
    }

    private static void pinyin4j() throws BadHanyuPinyinOutputFormatCombination {
        String str = "黎明中的花朵（";
        // 这里为什么用字符串数组接受返回的拼音呢，因为中文有多音字，输入“长”，会返回zhang3和chang2,（2,3表示第几声)
        String pinyin = PinyinHelper.toHanYuPinyinString(str, null, null, false); // str.charAt(0) 第一个汉字
//        for (String py : pinyin) {
//            System.out.println(py);
//        }
        System.out.println(pinyin);
    }

    /**
     * 通过数字、字母、中文对应拼音进行排序
     */
    private static void sorted() {
        List<Person> list = Lists.newArrayList(
                Person.builder().name("成斯杰").build(),
                Person.builder().name("成斯纯").build(),
                Person.builder().name("b成卓杰").build(),
                Person.builder().name("1成斯敏").build(),
                Person.builder().name("a成卓圳").build(),
                Person.builder().name("**成卓钊").build(),
                Person.builder().name("成斯涵").build());
        list.sort(Comparator.comparing(Person::getName, new PinyinComparator()));
        list.forEach(System.out::println);
    }

}
