package com.skeet.consul.provider.mine.loadclass;

import java.io.FileInputStream;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/14 16:46
 */
public class MyClassLoader extends ClassLoader {

    private String defaultDirectory = "E:/class/";

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        synchronized (getClassLoadingLock(name)) {
//            // First, check if the class has already been loaded
//            Class<?> c = findLoadedClass(name);
//            if (c == null) {
//                long t0 = System.nanoTime();
//                try {
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }
//
//                if (c == null) {
//                    // If still not found, then invoke findClass in order
//                    // to find the class.
//                    long t1 = System.nanoTime();
//                    c = findClass(name);
//
//                    // this is the defining class loader; record the stats
//                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    sun.misc.PerfCounter.getFindClasses().increment();
//                }
//            }
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] btyes = loadByte(name);
        return defineClass(name, btyes, 0, btyes.length);
    }

    private byte[] loadByte(String name) {
        String s = name.replaceAll("\\.", "/");
        String fullPath = defaultDirectory + s + ".class";
        byte[] bytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fullPath);
            int available = fileInputStream.available();
            bytes = new byte[available];
            fileInputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
