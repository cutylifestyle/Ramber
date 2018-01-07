package com.sixin.ramber.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 */

public class FileUtil {

    private FileUtil(){
        throw new UnsupportedOperationException("u can't instantiate me ...");
    }

    /**
     * 判断是否是文件夹
     * @param file 文件对象
     * @return {@code true}: 存在<br>{@code false}: 不存在
     * */
    public static boolean isDir(final File file){
        return file != null && file.exists() && file.isDirectory();
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(final String filePath){
        return filePath == null ? null: new File(filePath);
    }

    /**
     * 获取目录下所有文件
     * <p>不递归进子目录</p>
     *
     * @param dirPath 目录路径
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final String dirPath){
        return listFilesInDir(dirPath,false);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final String dirPath, final boolean isRecursive){
        return listFilesInDir(getFileByPath(dirPath),isRecursive);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final File dir, final boolean isRecursive){
        return listFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        },isRecursive);
    }

    /**
     * 获取目录下所有过滤的文件
     * <p>不递归进子目录</p>
     *
     * @param dir 目录路径
     * @param filter  过滤器
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(final File dir,
                                                      final FileFilter filter,
                                                      final boolean isRecursive){
        if(!isDir(dir)){
            return null;
        }
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0){
            for(File file : files){
                if(filter.accept(file)){
                    list.add(file);
                }
                if(isRecursive && isDir(file)){
                    list.addAll(listFilesInDirWithFilter(file, filter, true));
                }
            }
        }
        return list;
    }



}
