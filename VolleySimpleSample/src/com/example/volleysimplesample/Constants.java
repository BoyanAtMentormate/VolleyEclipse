package com.example.volleysimplesample;

import android.graphics.Bitmap;

/**
 * Created by boyan on 7/24/13.
 */
public class Constants {

	static final String TAG_MEMORY_CACHE = "BitmapLruImageCache";
	static final String TAG_DISK_CACHE = "DiskLruImageCache";
    public static final String TAG_IMAGE_REQUEST = "IMAGE_TAG";
    
    // we're using AJAX legacy api for simplicity
    public static final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android";

    // demo vars
    public static final boolean USER_NETWORK_IMAGE_VIEWS = true;
    public static final boolean USE_DISK_CACHE = true;
    
    // zero based
    public static final int MAX_IMAGES_PER_LOAD = 3;

    // general cache options (both disk and memory)
    public static final int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);
    public static final int DEFAULT_MAX_CACHE_LIMIT = MAX_MEMORY / 8;


    // Disk cache options
    public static final Bitmap.CompressFormat COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    public static final int COMPRESS_QUALITY = 70;
    public static final int DISK_CACHE_SOFT_TTL = 3 * 60 * 1000;
    public static final int DISK_CACHE_TTL = 5 * 60 * 1000;
}
