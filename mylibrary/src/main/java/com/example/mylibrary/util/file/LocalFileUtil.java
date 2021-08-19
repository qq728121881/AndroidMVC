package com.example.mylibrary.util.file;

import android.text.TextUtils;

import java.io.File;
import java.util.List;

public class LocalFileUtil {

    /**
     * 获取固定文件夹下的图书文件列表
     *
     * @param folder 文件夹
     * @param list 图书文件列表
     * @param filter 过滤器
     * */
    public static void getLocalFilesByFolderAndFilter(File folder, List<File> list, LocalFileTypeFilter filter){
        File[] files = folder.listFiles(filter);
        if(files == null || files.length == 0){
            return ;
        }
        for(File file: files){
            if(file.isDirectory()){
                getLocalFilesByFolderAndFilter(file,list,filter);
            }else{
                list.add(file);
            }
        }
    }

    /**
     * 文件是不是固定文件格式
     *
     * @param fileName 文件名称
     * @param conditions 文件格式条件
     * */
    public static boolean isTheFile(String fileName, String... conditions){
        if(!TextUtils.isEmpty(fileName)){
            String fileEnd = fileName
                    .substring(fileName.lastIndexOf(".") + 1, fileName.length())
                    .toLowerCase();
            if(conditions == null || conditions.length == 0){
                return true;//没有条件，返回true
            }
            for(String theFileEnd : conditions){
                if(fileEnd.equals(theFileEnd)){
                    return true;
                }
            }
        }
        return false;
    }

}
