package com.yubin.homework.week01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-17 16:15
 **/
public class MyFirstClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) {
        FileInputStream fileInputStream = null;
        try {
            String path = "/Users/admin/homework/src/main/resources/" + name + ".xlass";
            File file = new File(path);
            fileInputStream = new FileInputStream(file);
            byte[] byteArr = new byte[fileInputStream.available()];
            fileInputStream.read(byteArr);
            byte[] newDataArr = decode(byteArr);
            return defineClass(name, newDataArr, 0, newDataArr.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public byte[] decode(byte[] dataArr) {
        byte[] newDataArr = new byte[dataArr.length];
        for (int i = 0; i < dataArr.length; i++) {
            newDataArr[i] = (byte) (255 - dataArr[i]);
        }
        return newDataArr;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        MyFirstClassLoader classLoader = new MyFirstClassLoader();
        Class clazz = classLoader.findClass("Hello");
        Method method = clazz.getMethod("hello");
        try {
            Object obj = clazz.newInstance();
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
