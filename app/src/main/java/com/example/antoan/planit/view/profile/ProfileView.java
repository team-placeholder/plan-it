package com.example.antoan.planit.view.profile;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;
import com.example.antoan.planit.ui.AcountHeaderBuilder;
import com.example.antoan.planit.ui.DrawerListener;
import com.example.antoan.planit.view.calendar.CalendarActivity;
import com.example.antoan.planit.view.editProfile.EditProfileActivity;
import com.example.antoan.planit.view.friends.FriendsActivity;
import com.example.antoan.planit.view.friendsRequest.FriendRequestsActivity;
import com.example.antoan.planit.view.home.HomeActivity;
import com.example.antoan.planit.view.login.LoginActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileView extends Fragment implements ProfileContract.View {


    private ProfileContract.Presenter presenter;
    private AccountHeader accountHeader;
    private Drawer.OnDrawerItemClickListener onDrawerItemClickListener;
    private List<IDrawerItem> drawerItems;
    private  Toolbar toolbar;
    private Context ctx;
    private AcountHeaderBuilder accountHeaderBuilder;
    private com.example.antoan.planit.ui.DrawerBuilder drawerBuilder;
    private Fragment fragment;
    private DrawerListener drawerListener;


    public ProfileView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.fragment = this;
        View root = inflater.inflate(R.layout.fragment_profile_view, container, false);

        toolbar = (Toolbar) root.findViewById(R.id.tb_profile);
        this.presenter.start();


        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setOnDrawerItemClickListener() {

        this.onDrawerItemClickListener = new Drawer.OnDrawerItemClickListener() {


            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = null;
                switch ((int)drawerItem.getIdentifier()){
                    case 1:
                        intent = new Intent(ctx,HomeActivity.class);
                        break;
                    case 2:
                        intent = new Intent(ctx,EditProfileActivity.class);
                        break;
                    case 3:
                        intent = new Intent(ctx, FriendRequestsActivity.class);
                        presenter.clearNewRequests((int)drawerItem.getIdentifier(),0);
                        break;
                    case 4:
                        intent = new Intent(ctx, FriendsActivity.class);
                        break;
                    case 5:
                        presenter.logout();
                        intent = new Intent(ctx, LoginActivity.class);
                        break;
                    case 6:
                        intent = new Intent(ctx, CalendarActivity.class);
                        break;

                }



                startActivity(intent);
                return true;
            }
        };
    }

    public void setDrawerItems(List<IDrawerItem> drawerItems) {
        this.drawerItems = drawerItems;
    }



    @Override
    public void setAccountHeaderBuilder(AcountHeaderBuilder acountHeaderBuilder) {
        this.accountHeaderBuilder = acountHeaderBuilder;
    }

    @Override
    public void setDrawerBuilder(com.example.antoan.planit.ui.DrawerBuilder drawerBuilder) {
        this.drawerBuilder = drawerBuilder;
    }

    @Override
    public void setupAccountHeader(String username, String email, Bitmap avatar) {
       this.accountHeader = this.accountHeaderBuilder.getAcountHeader((Activity)this.getContext(),username,email,avatar);
    }

    @Override
    public void setupDrawer() {
        this.drawerBuilder.setDrawer((Activity)this.getContext(),this.accountHeader,this.toolbar,this.drawerItems,this.onDrawerItemClickListener);
    }

    @Override
    public void setDrawerLister(DrawerListener drawerListener) {
        this.drawerListener = drawerListener;
    }

    @Override
    public void setNewRequests(int indentifier,int i) {
        this.drawerBuilder.updateDrawerItem(indentifier,i);
    }
}
