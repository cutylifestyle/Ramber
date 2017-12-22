package com.sixin.ramber.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.sixin.ramber.models.Song;

import java.util.ArrayList;

/**
 * @author zhou
 * 用于获取手机上音乐数据的工具类
 */

public class SongLoadUtil {

    public static ArrayList<Song> getAllSongs(Context context){
        return getSongsForCursor(makeSongCursor(context,null,null));
    }

    private static Cursor makeSongCursor(Context context, String selection, String[] paramArrayOfString) {
        final String songSortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        return makeSongCursor(context, selection, paramArrayOfString, songSortOrder);
    }

    private static Cursor makeSongCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "is_music=1 AND title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"}, selectionStatement, paramArrayOfString, sortOrder);
    }

    private static ArrayList<Song> getSongsForCursor(Cursor cursor) {
        ArrayList<Song> arrayList = new ArrayList<>();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String artistName = cursor.getString(2);
                String albumName = cursor.getString(3);
                int duration = cursor.getInt(4);
                int trackNumber = cursor.getInt(5);
                long artistId = cursor.getInt(6);
                long albumId = cursor.getLong(7);

                arrayList.add(new Song(albumId,albumName, artistId, artistName, duration,id, title,trackNumber));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

}
