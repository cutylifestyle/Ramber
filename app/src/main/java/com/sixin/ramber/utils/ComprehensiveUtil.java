package com.sixin.ramber.utils;

import android.content.ContentUris;
import android.net.Uri;

/**
 * 综合工具类
 * 包含的方法比较种类较多，彼此之间联系性不精密
 * */
public class ComprehensiveUtil {

    private ComprehensiveUtil(){
        throw new UnsupportedOperationException("u can't initiated me...");
    }

    /**
     * 获取歌曲对应的图片的Uri
     * @param albumId 歌曲的id
     * @return Uri
     * */
    public static Uri getAlbumArtUri(long albumId){
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

}
