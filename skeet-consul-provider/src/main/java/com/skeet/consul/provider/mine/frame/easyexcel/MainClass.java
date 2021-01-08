package com.skeet.consul.provider.mine.frame.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.skeet.consul.provider.mine.frame.easyexcel.entity.ExportExcel;
import com.skeet.consul.provider.mine.frame.easyexcel.strategy.SameContentMergeStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/7 10:53
 */
public class MainClass {

    public static void main(String[] args) {
        // 方法1 注解
//        String fileName = "C:\\Test" + "mergeWrite" + System.currentTimeMillis() + ".xlsx";
//        // 在DemoStyleData里面加上ContentLoopMerge注解
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        EasyExcel.write(fileName, ExportExcel.class).sheet("模板").doWrite(data());

        // 方法2 自定义合并单元格策略
        String fileName = "C:\\Test\\" + "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        OnceAbsoluteMergeStrategy loopMerge2Strategy = new OnceAbsoluteMergeStrategy(1, 2, 1, 2);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        long startTime = System.currentTimeMillis();
        EasyExcel
                .write(fileName, ExportExcel.class)
                .registerWriteHandler(new SameContentMergeStrategy(0, 1, 2))
                .sheet("模板")
                .doWrite(data());

        System.out.println("cost:" + (System.currentTimeMillis() - startTime));

    }

    private static List<ExportExcel> data() {
        return Optional.of(new ArrayList<ExportExcel>())
                .map(list -> {
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());
                    list.add(ExportExcel.builder().age(30).name("b").favorite("strawberry").build());
                    list.add(ExportExcel.builder().age(30).name("b").favorite("strawberry").build());
                    list.add(ExportExcel.builder().age(30).name("b").favorite("strawberry").build());
                    list.add(ExportExcel.builder().age(40).name("c").favorite("cherry").build());
                    list.add(ExportExcel.builder().age(40).name("c").favorite("cherry").build());
                    list.add(ExportExcel.builder().age(40).name("c").favorite("cherry").build());
                    list.add(ExportExcel.builder().age(40).name("c").favorite("strawberry").build());
                    list.add(ExportExcel.builder().age(40).name("c").favorite("strawberry").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("cherry").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("cherry").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());
                    list.add(ExportExcel.builder().age(20).name("a").favorite("vegetable").build());

                    return list;
                })
                .orElseGet(ArrayList::new);

    }
}
