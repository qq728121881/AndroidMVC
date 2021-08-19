package com.example.mylibrary.util.file;


import com.example.mylibrary.tool.log.DLog;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileUtils {
    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        if (isSpace(dirPath)) {
            return false;
        } else {
            File path = new File(dirPath);
            return path != null && (path.exists() ? path.isDirectory() : path.mkdirs());
        }
    }


    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile().getAbsolutePath())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void closeIO(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 用于判断指定字符是否为空白字符，空白符包含：空格、tab键、换行符。
     *
     * @param s
     * @return
     */
    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }


    /**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
    public static void deleteAllFile(File file){
        if(file.isFile()){
            deleteFile(file);
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                deleteFile(file);
                return;
            }

            for(File f : childFile){
                deleteAllFile(f);
            }
            deleteFile(file);
        }
    }


    public static String getFileNameByUrl(String url){
        String name = "";

        try {
            url = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (url != null){
            if(url.contains("?")){
                url = url.split("\\?")[0];
            }
            DLog.e("url: " + url);
        }

        if (url != null && url.trim().length() > 0){
            int start = url.lastIndexOf("/");
            if (start != -1 ) {
                name =  url.substring(start + 1);
            } else {
                name = "";
            }
        }
        return name;
    }

    /**
     * 返回-1代表文件不存在，否则返回1
     *
     * @param path
     * @return
     */
    public static boolean checkFileExistFromLocal(String path) {

        if (path == null || path.trim().equals("")) {
            return false;
        }

        File file = new File(path);
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            return false;
        }

        return true;
    }

}
