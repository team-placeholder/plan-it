package com.example.antoan.planit.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ImageHelper {

    public ImageHelper(){

    }
    public  Bitmap FromStringToBitmap(String base64String){
        final byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        return decodedByte;
    }

    public  String FromBitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImg = Base64.encodeToString(b,Base64.DEFAULT);
        return encodedImg;
    }
}
