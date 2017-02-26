package com.example.antoan.planit.view.editProfile.changeAvatar;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.SqlData.UserContract;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.utils.CheckPermiter;
import com.example.antoan.planit.utils.ImageHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ChangeAvatarPresenter implements ChangeAvatarContract.Presenter {

    private final ChangeAvatarContract.View view;
    private final BaseData<User> httpData;
    private final AuthData authData;
    private final DbOperations db;
    private final ImageHelper imgHelper;
    private String email;
    private String encodedImg;

    @Inject
    public ChangeAvatarPresenter(ChangeAvatarContract.View view,
                                 BaseData<User> httpData,
                                 AuthData authData,
                                 DbOperations db,
                                 ImageHelper imgHelper,
                                 CheckPermiter checkPermiter){
        this.view = view;
        this.httpData = httpData;
        this.authData = authData;
        this.db =db;
        this.imgHelper = imgHelper;
        this.getView().setPresenter(this);
        this.getView().setCheckPermiter(checkPermiter);
    }
    @Override
    public void start() {
        this.getCurrentUser();
       httpData.getById(this.email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<User>() {
                   @Override
                   public void accept(User user) throws Exception {
                       Bitmap avatar = imgHelper.FromStringToBitmap(user.getAvatar());
                       getView().setImage(avatar);
                   }
               });

    }

    @Override
    public ChangeAvatarContract.View getView() {
        return this.view;
    }

    @Override
    public void onCaptureImage(Bitmap thumbnail) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".png");
        byte[] b = bytes.toByteArray();
        this.encodedImg = Base64.encodeToString(b,Base64.DEFAULT);


        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getView().setImage(thumbnail);
    }

    @Override
    public void onSelectFromGallery(Bitmap thumbnail) {
        if(thumbnail != null){
            this.encodedImg  = imgHelper.FromBitmapToString(thumbnail);
            this.getView().setImage(thumbnail);
        }
    }

    @Override
    public void updateAvatar() {
        this.authData.updateAvatar(new User("",this.email,"",this.encodedImg)).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isUpdated) throws Exception {
                        if(isUpdated){
                            //getView().notifyMessage("Profile picture was successfully saved");
                            getView().navigate();
                        }else{
                            //getView().notifyMessage("Problem occured with saving the picture. Please try again later.");
                        }
                    }
                });
    }

    private void getCurrentUser(){
        Cursor cursor = db.getCurrentUser();

        if(cursor.moveToFirst()){
            this.email = cursor.getString(cursor.getColumnIndex(UserContract.ResUserEntry.COLUMN_EMAIL));
        }
    }
}
