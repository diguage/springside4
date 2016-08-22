package com.diguage.bytecode;

import com.google.common.collect.Lists;
import net.bytebuddy.ByteBuddy;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by diguage on 16/8/22.
 */
public class Main {
    public static List<Class<?>> findAllClass() {
        String pkgName = "org.springside.examples.quickstart";
        Class<Main> c = Main.class;
        System.out.println(c.getProtectionDomain().getCodeSource().getLocation());
//        System.out.println(c.getSimpleName());
//        System.out.println(c.getName().replace('.', '/'));
//        System.out.println(c.getResource(c.getSimpleName() + ".class").getPath());
//        String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getName().replace('.', '/') + ".class", "");
        String path = c.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(path);
        return fingAllClassInPath(path, pkgName);
    }

    public static List<Class<?>> fingAllClassInPath(String path, String pkgName) {
        List<Class<?>> result = Lists.newArrayList();
        String pkgPath = pkgName.replace('.', '/');
        List<File> fileList = fingAllClassInPath(new File(path + pkgPath));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (File file : fileList) {
            String className = file.getAbsolutePath().replace(path, "").replace(".class", "").replace('/', '.');
            try {
                result.add(classLoader.loadClass(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<File> fingAllClassInPath(File path) {
        List<File> result = Lists.newArrayList();
        if (path.isFile() && path.getName().endsWith(".class")) {
            result.add(path);
        } else if (path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file : files) {
                result.addAll(fingAllClassInPath(file));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("\n\n\nGet all classes in the package.");
        List<Class<?>> classList = findAllClass();
        classList.stream()
                .map(Class::getName)
                .forEach(System.out::println);
        for (Class<?> clazz : classList) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                new ByteBuddy()
                        .redefine(clazz)
                        .defineMethod(method.getName())
            }

        }


//        System.out.println("\n\n");
    }
}
