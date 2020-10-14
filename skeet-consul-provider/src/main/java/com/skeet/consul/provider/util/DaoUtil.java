package com.skeet.consul.provider.util;

import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Desc:生成spring的业务层、dao层、mapper层文件
 *
 * @author chengsj
 * @date 2020/6/3 16:30
 */
public class DaoUtil {

    public static void main(String[] args) {
        FileDirectory directory = new FileDirectory();
        String parentPath = "E:\\projects\\fb4_fixbug\\fpx-fb4-order\\fpx-fb4-order-provider-common\\";
        String parentClassPath = parentPath + "src\\main\\java\\";
        directory.setServiceDirectory(parentClassPath + "com.fpx.fb4.order.provider.common.business.valueadded.service".replace(".", "\\"));
        directory.setDaoDirectory(parentClassPath + "com.fpx.fb4.order.provider.common.business.valueadded.dao".replace(".", "\\"));
        directory.setMapperDirectory(parentClassPath + "com.fpx.fb4.order.provider.common.business.valueadded.dao.mapper".replace(".", "\\"));
        directory.setMapperImplDirectory(parentPath + "src\\main\\resources\\META-INF\\mybatis\\mapper\\valueadded");
        String classFullName = "com.fpx.fb4.order.provider.common.business.valueadded.model.entity.ValueAddedConsignmentUploadDetail";
        genFile(classFullName, directory);
    }

    public static void genFile(String classFullName, FileDirectory directory) {
        String serviceDirectory = directory.getServiceDirectory();
        String serviceImplDirectory = appendFilePath(serviceDirectory, "impl");
        String daoDirectory = directory.getDaoDirectory();
        String daoImplDirectory = appendFilePath(daoDirectory, "impl");
        String mapperDirectory = directory.getMapperDirectory();
        String mapperImplDirectory = directory.getMapperImplDirectory();

        try {
            writeServiceContent(serviceDirectory, classFullName);
            writeServiceImplContent(serviceImplDirectory, classFullName);
            writeDaoContent(daoDirectory, classFullName);
            writeDaoImplContent(daoImplDirectory, classFullName);
            writeMapperContent(mapperDirectory, classFullName);
            writeMapperImplContent(mapperDirectory, mapperImplDirectory, classFullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeServiceContent(String serviceDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(serviceDirectory);

        String fileName = String.format("%sService", className);
        String fileTotalName = fileName + ".java";

        String path = appendFilePath(serviceDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "package %s;\n\n" +
                            "/**\n" +
                            " * Desc:\n" +
                            " *\n" +
                            " * @author chengsj\n" +
                            " * @date %s\n" +
                            " */\n" +
                            "public interface %s {\n}", packageName, getAuthorCurrentDate(), fileName);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeServiceImplContent(String serviceImplDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(serviceImplDirectory);

        String fileName = String.format("%sServiceImpl", className);
        String parentFileName = String.format("%sService", className);
        String fileTotalName = fileName + ".java";

        String path = appendFilePath(serviceImplDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "package %s;\n" +
                            "\n" +
                            "/**\n" +
                            " * Desc:\n" +
                            " *\n" +
                            " * @author chengsj\n" +
                            " * @date %s\n" +
                            " */\n" +
                            "@Service\n" +
                            "public class %s implements %s {\n" +
                            "\n" +
                            "    @Resource\n" +
                            "    private %sDao dao;\n" +
                            "\t\n" +
                            "}", packageName, getAuthorCurrentDate(), fileName, parentFileName, className);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeDaoContent(String daoDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(daoDirectory);

        String fileName = String.format("%sDao", className);
        String fileTotalName = fileName + ".java";

        String path = appendFilePath(daoDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "package %s;\n" +
                            "\n" +
                            "import com.fpx.fb4.common.core.dao.BaseDao;\n" +
                            "import %s;\n" +
                            "\n" +
                            "\n" +
                            "/**\n" +
                            " * Desc:\n" +
                            " *\n" +
                            " * @author chengsj\n" +
                            " * @date %s\n" +
                            " */\n" +
                            "public interface %s extends BaseDao<%s> {\n" +
                            "}", packageName, classFullName, getAuthorCurrentDate(), fileName, className);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeDaoImplContent(String daoImplDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(daoImplDirectory);

        String fileName = String.format("%sDaoImpl", className);
        String parentFileName = String.format("%sDao", className);
        String fileTotalName = fileName + ".java";

        String path = appendFilePath(daoImplDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "package %s;\n" +
                            "\n" +
                            "import com.fpx.fb4.common.core.dao.BaseDaoImpl;\n" +
                            "import com.fpx.fb4.common.core.dao.BaseMapper;\n" +
                            "import %s;\n" +
                            "\n" +
                            "/**\n" +
                            " * Desc:\n" +
                            " *\n" +
                            " * @author chengsj\n" +
                            " * @date %s\n" +
                            " */\n" +
                            "@Repository\n" +
                            "public class %s extends BaseDaoImpl<%s> implements %s {\n" +
                            "\n" +
                            "    @Resource\n" +
                            "    private %sMapper mapper;\n" +
                            "\n" +
                            "    @Override\n" +
                            "    protected BaseMapper<%s> getBaseMapper() {\n" +
                            "        return mapper;\n" +
                            "    }\n" +
                            "}", packageName, classFullName, getAuthorCurrentDate(), fileName, className, parentFileName, className, className);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeMapperContent(String mapperDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(mapperDirectory);

        String fileName = String.format("%sMapper", className);
        String fileTotalName = fileName + ".java";

        String path = appendFilePath(mapperDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "package %s;\n" +
                            "\n" +
                            "import com.fpx.fb4.common.core.dao.BaseMapper;\n" +
                            "import %s;\n" +
                            "import org.apache.ibatis.annotations.Mapper;\n" +
                            "\n" +
                            "/**\n" +
                            " * Desc:\n" +
                            " *\n" +
                            " * @author chengsj\n" +
                            " * @date %s\n" +
                            " */\n" +
                            "@Mapper\n" +
                            "public interface %sMapper extends BaseMapper<%s> {\n" +
                            "}", packageName, classFullName, getAuthorCurrentDate(), className, className);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeMapperImplContent(String mapperDirectory, String mapperImplDirectory, String classFullName) {
        String className = getClassName(classFullName);
        String packageName = getPackageName(mapperDirectory);

        String fileName = String.format("%sMapper", className);
        String fileTotalName = fileName + ".xml";

        String path = appendFilePath(mapperImplDirectory, fileTotalName);
        try {
            //文件路径（路径+文件名）
            File file = genNewFile(path);
            if (file == null) {
                return;
            }
            String content = String.format(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                            "<!DOCTYPE mapper\n" +
                            "        PUBLIC \"-//ibatis.apache.org//DTD Mapper 3.0//EN\"\n" +
                            "        \"http://mybatis.apache.org/dtd/mybatis-3-mapper.dtd\">\n" +
                            "<mapper namespace=\"%s.%s\">\n" +
                            "\n" +
                            "</mapper>", packageName, fileName);
            // 文件输出流用于将数据写入文件
            writeFile(file, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private static File genNewFile(String path) throws IOException {
        File file = new File(path);

        if (file.exists()) {
            return null;
        }

        File dir = new File(file.getParent());
        dir.mkdirs();
        file.createNewFile();
        return file;
    }

    private static String appendFilePath(String directory, String fileTotalName) {
        if (directory.charAt(directory.length() - 1) != '\\') {
            directory = directory + "\\";
        }

        return directory + fileTotalName;
    }

    private static void writeFile(File file, String content) throws IOException {
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(content.getBytes());
        outStream.close();
    }

    private static String getPackageName(String directoryName) {
        String packageName = directoryName.substring(directoryName.indexOf("com\\fpx\\fb4")).replace("\\", ".");
        if (packageName.charAt(packageName.length() - 1) == '.') {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        return packageName;
    }

    private static String getClassName(String classFullName) {
        return classFullName.substring(classFullName.lastIndexOf(".") + 1);
    }

    public static String getAuthorCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d hh:mm");
        return format.format(new Date());
    }

    @Data
    static class FileDirectory {
        private String serviceDirectory;
        private String daoDirectory;
        private String mapperDirectory;
        private String mapperImplDirectory;
    }
}
