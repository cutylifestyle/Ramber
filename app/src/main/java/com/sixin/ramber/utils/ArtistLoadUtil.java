package com.sixin.ramber.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.sixin.ramber.models.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * 艺术家工具类
 */

public class ArtistLoadUtil {

    private ArtistLoadUtil(){
        throw new UnsupportedOperationException("u can't initiated me...");
    }

    /**
     * 获取所有艺术家信息
     * @param context 上下文对象
     * @return list
     * */
    public static List<Artist> getAllArtists(Context context) {
        return getArtistsForCursor(makeArtistCursor(context, null, null));
    }

    private static List<Artist> getArtistsForCursor(Cursor cursor) {
        ArrayList<Artist> arrayList = new ArrayList<>();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                Artist artist = new Artist();
                artist.setId(cursor.getLong(0));
                artist.setName(cursor.getString(1));
                artist.setAlbumCount(cursor.getInt(2));
                artist.setSongCount(cursor.getInt(3));

                arrayList.add(artist);
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    private static Cursor makeArtistCursor(Context context, String selection, String[] paramArrayOfString) {
        return context.getContentResolver().query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
                , new String[]{"_id", "artist", "number_of_albums", "number_of_tracks"}
                , selection
                , paramArrayOfString
                , null);

    }

}
