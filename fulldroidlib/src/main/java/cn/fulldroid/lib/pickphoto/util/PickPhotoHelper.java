package cn.fulldroid.lib.pickphoto.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import cn.fulldroid.lib.pickphoto.model.DirImage;
import cn.fulldroid.lib.pickphoto.model.GroupImage;
import cn.fulldroid.lib.pickphoto.util.event.ImageLoadOkEvent;

/**
 * Created by wanbo on 2016/12/31.
 */

public class PickPhotoHelper {

    private Activity activity;
    public HashMap<String, List<String>> mGroupMap = new LinkedHashMap<>();

    public PickPhotoHelper(Activity activity) {
        this.activity = activity;
    }

    public void getImages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = activity.getContentResolver();

                //jpeg & png
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                if (mCursor == null) {
                    return;
                }
                List<String> dirNames = new ArrayList<>();
                while (mCursor.moveToNext()) {
                    // get image path
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    File file = new File(path);
                    if(!file.exists()){
                        continue;
                    }

                    // get image parent name
                    String parentName = new File(path).getParentFile().getName();
                    Log.d(PickConfig.TAG, parentName + ":" + path);
                    // save all Photo
                    if (!mGroupMap.containsKey(PickConfig.ALL_PHOTOS)) {
                        dirNames.add(PickConfig.ALL_PHOTOS);
                        List<String> chileList = new ArrayList<>();
                        chileList.add(path);
                        mGroupMap.put(PickConfig.ALL_PHOTOS, chileList);
                    } else {
                        mGroupMap.get(PickConfig.ALL_PHOTOS).add(path);
                    }
                    // save by parent name
                    if (!mGroupMap.containsKey(parentName)) {
                        dirNames.add(parentName);
                        List<String> chileList = new ArrayList<>();
                        chileList.add(path);
                        mGroupMap.put(parentName, chileList);
                    } else {
                        mGroupMap.get(parentName).add(path);
                    }
                }
                mCursor.close();
                GroupImage groupImage = new GroupImage();
                groupImage.mGroupMap = mGroupMap;
                DirImage dirImage = new DirImage();
                dirImage.dirName = dirNames;
                PickPreferences.getInstance(activity).saveImageList(groupImage);
                PickPreferences.getInstance(activity).saveDirNames(dirImage);
                r.post(new Runnable() {
                    @Override
                    public void run() {
                        RxBus.getInstance().send(new ImageLoadOkEvent());
                    }
                });
            }
        }).start();

    }

    static android.os.Handler r = new android.os.Handler(Looper.getMainLooper());
}
