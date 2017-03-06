package com.example.antoan.planit.view.editProfile.changeAvatar;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.utils.CheckPermiter;
import com.example.antoan.planit.view.calendar.CalendarActivity;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeAvatarView extends Fragment implements ChangeAvatarContract.View, View.OnClickListener {


    private ChangeAvatarContract.Presenter presenter;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private ImageView ivImage;
    private String userChoosenTask;
    private Bitmap thumbnail;
    private Button btnUpdate;
    private CheckPermiter checkPermiter;

    public ChangeAvatarView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_change_avatar_view, container, false);

        btnSelect = (Button) root.findViewById(R.id.btn_select_photo);
        ivImage = (ImageView) root.findViewById(R.id.ivImage);
        btnUpdate = (Button) root.findViewById(R.id.btn_update);

        btnSelect.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        this.presenter.start();

        return root;
    }

    @Override
    public void setPresenter(ChangeAvatarContract.Presenter presenter) {
         this.presenter = presenter;
    }

    @Override
    public void setImage(Bitmap avatar) {
        this.thumbnail = avatar;
        ivImage.setImageBitmap(avatar);
    }

    @Override
    public void setCheckPermiter(CheckPermiter checkPermiter) {
        this.checkPermiter = checkPermiter;
    }

    @Override
    public void navigate() {
        Intent intent = new Intent(this.getContext(), CalendarActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifyMessage(String msg) {
          Toast.makeText(this.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select_photo:
                selectImage();
                break;
            case R.id.btn_update:
                if(this.thumbnail != null){
                this.presenter.updateAvatar();
            }else{
                    Toast.makeText(this.getContext(),"Please select photo",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= checkPermiter.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CheckPermiter.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        this.presenter.onCaptureImage(thumbnail);
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap thumbnail=null;
        if (data != null) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.presenter.onSelectFromGallery(thumbnail);

    }
}
