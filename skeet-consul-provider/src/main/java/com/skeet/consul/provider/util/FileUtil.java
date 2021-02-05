package com.skeet.consul.provider.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/7/5 15:12
 */
public class FileUtil {

    private static final Lock LOCK = new ReentrantLock(true);

    /**
     * 写入文件的主路径
     */
    public static final String PARENT_WRITE_PATH = "E:\\java脚本生成的文件";

    public static void main(String[] args) {
//        writeStringListToFile("E:\\total.txt", Lists.newArrayList("A", "B"));
//        writeStringToFile("E:\\total.txt", "A");
        System.out.println(appendPath("E:\\java脚本生成的文件", "file.txt"));
        System.out.println(appendPath("E:\\java脚本生成的文件\\", "\\file.txt"));
        System.out.println(appendPath("E:\\java脚本生成的文件\\", "file.txt"));
        System.out.println(appendPath("E:\\java脚本生成的文件", "\\file.txt"));
    }

    public static String loadFileString(String filePath) {
        int len = 0;
        StringBuilder str = new StringBuilder();
        File file = new File(filePath);
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                // 处理换行符的问题
                if (len != 0) {
                    str.append("\r\n").append(line);
                } else {
                    str.append(line);
                }
                len++;
            }
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();

    }

    /**
     * 从文件中获取字符串，并将字符串根据换行符分割成集合
     *
     * @param filePath
     * @return
     */
    public static List<String> loadFileListWithLinefeed(String filePath) {
        String str = FileUtil.loadFileString(filePath);
        return SkeetStringUtil.split(str, "\r\n");
    }

    /**
     * 按行分割文件
     *
     * @param rows                为多少行一个文件
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     */
    public static void splitDataToSaveFileByTxt(int rows,
                                                String sourceFilePath,
                                                String targetDirectoryPath) {
        splitDataToSaveFile(rows, sourceFilePath, targetDirectoryPath, "txt");
    }


    /**
     * 按行分割文件
     *
     * @param rows                为多少行一个文件
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     */
    public static void splitDataToSaveFileByCsv(int rows,
                                                String sourceFilePath,
                                                String targetDirectoryPath) {
        splitDataToSaveFile(rows, sourceFilePath, targetDirectoryPath, "csv");
    }

    /**
     * 按行分割文件
     *
     * @param rows                为多少行一个文件
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     * @param targetFileSuffix    文件分割后的扩展名
     */
    public static void splitDataToSaveFile(int rows,
                                           String sourceFilePath,
                                           String targetDirectoryPath,
                                           String targetFileSuffix) {
        long start1 = System.currentTimeMillis();

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return;
            }
        } else {
            targetFile.mkdirs();
        }
        try {

            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath), "GBK");
            BufferedReader br = new BufferedReader(in);

            BufferedWriter bw = null;
            String str = "";
            String tempData = br.readLine();
            int i = 1, s = 0;
            long start2 = System.currentTimeMillis();
            while (tempData != null) {
                str += tempData + "\r\n";
                if (i % rows == 0) {
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                            targetFile.getAbsolutePath() + "/"
                                    + sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."))
                                    + "_"
                                    + (s + 1)
                                    + "."
                                    + targetFileSuffix), "GBK"), 1024);
                    bw.write(str);
                    bw.close();

                    str = "";
                    start2 = System.currentTimeMillis();
                    s += 1;
                }
                i++;
                tempData = br.readLine();
            }
            if ((i - 1) % rows != 0) {

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        targetFile.getAbsolutePath() + "/"
                                + sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."))
                                + "_"
                                + (s + 1)
                                + "."
                                + targetFileSuffix), "GBK"), 1024);
                bw.write(str);
                bw.close();
                br.close();

                s += 1;
            }
            in.close();

        } catch (Exception e) {
        }
    }


    /**
     * 存储list到文件
     *
     * @param path
     * @param list
     */
    @SuppressWarnings("resource")
    public static <T> void writeList(String path, List<T> list) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(list);
            stream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 追加写入到txt
     *
     * @param path
     * @param data
     */
    @SuppressWarnings("all")
    public static void writeStringToFile(String path, String data) {
        writeStringToFile(path, data, false);
    }


    /**
     * 追加写入到txt
     *
     * @param path
     * @param data
     * @param genNewFile 是否写入到新文件中
     */
    @SuppressWarnings("all")
    public static void writeStringToFile(String path, String data, boolean genNewFile) {
        File file = null;
        try {

            if (genNewFile) {
                file = getSingleFile(path);
                path = file.getPath();
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
                String format = dateFormat.format(new Date());
                int indexDot = path.lastIndexOf(".");
                String fullPath = path.substring(0, indexDot);
                String suffix = path.substring(indexDot);

                path = fullPath + String.format("_%s", format) + suffix;
                file = new File(path);
                // 路径不存在，则创建路径
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            }

            // 追加写入
            FileOutputStream outputStream = new FileOutputStream(file, true);

            if (file.exists() && file.length() == 0) {
                outputStream.write(data.getBytes());
            } else {
                outputStream.write(("\r\n" + data).getBytes());
            }
            outputStream.close();
            System.out.println(String.format("已写入数据【%s】至文件【%s】中", data, path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常：");
            e.printStackTrace();
        }
    }

    /**
     * 写入到txt，对于相同的{@code path}，每次执行该方法都会生成新文件
     *
     * @param path     文件全路径名
     * @param dataList 待写入数据集合
     */
    @SuppressWarnings("all")
    public static void writeStringListToFile(String path, Collection<String> dataList) {
        writeStringListToFile(path, dataList, true);
    }

    /**
     * 写入到txt，对于相同的{@code path}，每次执行该方法都会生成新文件
     *
     * @param path       文件全路径名
     * @param dataList   待写入数据集合
     * @param genNewFile 是否写入到新文件中
     */
    public static void writeStringListToFile(String path, Collection<String> dataList, boolean genNewFile) {
        try {

            File file = null;
            if (genNewFile) {
                file = getSingleFile(path);
                path = file.getPath();
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
                String format = dateFormat.format(new Date());
                int indexDot = path.lastIndexOf(".");
                String fullPath = path.substring(0, indexDot);
                String suffix = path.substring(indexDot);

                path = fullPath + String.format("_%s", format) + suffix;
                file = new File(path);
                // 路径不存在，则创建路径
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            }

            // 追加写入
            FileOutputStream outputStream = new FileOutputStream(file, true);

            for (String data : dataList) {
                if (file.exists() && file.length() == 0) {
                    outputStream.write(data.getBytes());
                } else {
                    outputStream.write(("\r\n" + data).getBytes());
                }
                System.out.println(String.format("已写入数据【%s】至文件【%s】中", data, path));
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常：");
            e.printStackTrace();
        }

    }

    /**
     * 根据文件路径名获取唯一的文件，文件格式：文件前缀名 + 日期 + 序号 + 文件后缀名
     * 如：【E:\task.txt】转为【E:\task_190820-1.txt】
     *
     * @param path 文件全路径名
     * @return 文件对象
     */
    public static File getSingleFile(String path) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String format = dateFormat.format(new Date());
        int indexDot = path.lastIndexOf(".");
        String fullPath = path.substring(0, indexDot);
        String suffix = path.substring(indexDot);
        int seq = 1;

        path = fullPath + String.format("_%s-%s", format, seq++) + suffix;
        File file = new File(path);

        while (file.exists()) {
            path = fullPath + String.format("_%s-%s", format, seq++) + suffix;
            file = new File(path);
        }

        // 路径不存在，则创建路径
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        return file;
    }

    public static String appendPath(String... pathOrName) {
        StringBuilder builder = new StringBuilder();
        List<String> list = Arrays.asList(pathOrName);
        list.forEach(s -> {
            boolean frontEndDiagonalFlag = builder.toString().endsWith("\\");
            boolean currentStartDiagonalFlag = s.startsWith("\\");
            if (frontEndDiagonalFlag && currentStartDiagonalFlag) {
                builder.append(s.substring(1));
            } else if (!frontEndDiagonalFlag && !currentStartDiagonalFlag) {
                builder.append("\\").append(s);
            } else {
                builder.append(s);
            }
        });

        return builder.toString().substring(1);
    }
}
