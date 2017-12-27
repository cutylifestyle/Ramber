package com.sixin.ramber.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.sixin.ramber.R;
import com.sixin.ramber.models.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 */

public class PlaylistLoadUtil {

    private static final String MUSIC_ONLY_SELECTION = MediaStore.Audio.AudioColumns.IS_MUSIC + "=1"
            + " AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''";

    public static List<Playlist> getPlayLists(Context context){
        ArrayList<Playlist> data = new ArrayList<>();
        makeDefaultPlaylists(context,data);
        Cursor cursor = makePlayListCursor(context);
        if(cursor != null && cursor.moveToFirst()){
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            int songCount = getSongCountForPlaylist(context,id);
            data.add(new Playlist(id, name, songCount));
        }
        return data;
    }

    private static Cursor makePlayListCursor(Context context) {
        return context.getContentResolver().query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[]{
                        BaseColumns._ID,
                        MediaStore.Audio.PlaylistsColumns.NAME
                },null,null,MediaStore.Audio.Playlists.DEFAULT_SORT_ORDER);
    }

    private static  int getSongCountForPlaylist(final Context context, final long playlistId) {
        Cursor c = context.getContentResolver().query(
                MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId),
                new String[]{BaseColumns._ID}, MUSIC_ONLY_SELECTION, null, null);

        if (c != null) {
            int count = 0;
            if (c.moveToFirst()) {
                count = c.getCount();
            }
            c.close();
            return count;
        }
        return 0;
    }

    private static void makeDefaultPlaylists(Context context,ArrayList<Playlist> data) {
        final Resources resources = context.getResources();

        /* Last added list */
        final Playlist lastAdded = new Playlist(-1,
                resources.getString(R.string.playlist_last_added), -1);
        data.add(lastAdded);

        /* Recently Played */
        final Playlist recentlyPlayed = new Playlist(-2,
                resources.getString(R.string.playlist_recently_played), -1);
        data.add(recentlyPlayed);

        /* Top Tracks */
        final Playlist topTracks = new Playlist(-3,
                resources.getString(R.string.playlist_top_tracks), -1);
        data.add(topTracks);
    }

}
