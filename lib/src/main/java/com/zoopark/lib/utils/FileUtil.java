package com.zoopark.lib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtil {

    /**
     * 返回缓存文件
     *
     * @param context 上下文
     * @return 缓存文件
     */
    public static File getCacheFile(Context context) {
        // 如果 SD 卡正常挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 获取系统管理的 SD 卡缓存文件
            File file = context.getExternalCacheDir();

            // 如果文件为空, 使用自定义缓存文件夹做缓存路径
            if (file == null) {
                file = new File(getCacheFilePath(context));
                makeDirs(file);
            }
            return file;
        } else {
            // 使用应用内缓存
            return context.getCacheDir();
        }
    }

    /**
     * 获取自定义缓存文件地址
     *
     * @param context 上下文
     * @return 缓存文件地址
     */
    public static String getCacheFilePath(Context context) {
        String packageName = context.getPackageName();
        return  Environment.getExternalStorageDirectory().getPath()
                + packageName
                + "/custom_cache";
    }

    /**
     * 创建未存在的文件夹
     *
     * @param file 需要创建文件夹的文件
     * @return
     */
    public static File makeDirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

}
