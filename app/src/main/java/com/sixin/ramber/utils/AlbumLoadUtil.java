package com.sixin.ramber.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.sixin.ramber.models.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐专辑工具类
 */
// TODO: 2018/1/17 没有添加排序功能
public class AlbumLoadUtil {

    private AlbumLoadUtil(){
        throw new UnsupportedOperationException("u can't initiated me...");
    }

    /**
     * 获取所有专辑的数量
     * @param context 上下文对象
     * @return List
     * */
   public static List<Album> getAllAlbums(Context context){
        return getAlbumsForCursor(makeAlbumCursor(context,null,null));
   }

   private static Cursor makeAlbumCursor(Context context, String selection, String[] paramArrayOfString){
       return context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
               , new String[]{"_id", "album", "artist", "artist_id", "numsongs", "minyear"}
               , selection, paramArrayOfString, null);
   }

   private static List<Album> getAlbumsForCursor(Cursor cursor) {
       ArrayList<Album> arrayList = new ArrayList<>();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                Album album = new Album();
                album.setId(cursor.getLong(0));
                album.setTitle(cursor.getString(1));
                album.setArtistName(cursor.getString(2));
                album.setArtistId(cursor.getLong(3));
                album.setSongCount(cursor.getInt(4));
                album.setYear(cursor.getInt(5));

                arrayList.add(album);
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

}
