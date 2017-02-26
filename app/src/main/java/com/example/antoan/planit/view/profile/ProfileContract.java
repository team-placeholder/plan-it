package com.example.antoan.planit.view.profile;

import android.graphics.Bitmap;

import com.example.antoan.planit.ui.AcountHeaderBuilder;
import com.example.antoan.planit.ui.DrawerBuilder;
import com.example.antoan.planit.ui.DrawerListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ProfileContract {
    public interface View{

        void setPresenter(ProfileContract.Presenter presenter);

        void setOnDrawerItemClickListener();

        void setDrawerItems(List<IDrawerItem> drawerItems);


        void setAccountHeaderBuilder(AcountHeaderBuilder acountHeaderBuilder);

        void setDrawerBuilder(DrawerBuilder drawerBuilder);

        void setupAccountHeader(String username, String email, Bitmap avatar);

        void setupDrawer();

        void setDrawerLister(DrawerListener drawerListener);

        void setNewRequests(int indentifier, int i);
    }

    public interface Presenter extends Logoutable {

        void start();

        ProfileContract.View getView();

        void logout();

        void clearNewRequests(int indentifier, int i);
    }

    public interface Logoutable{
        void logout();
    }
}
