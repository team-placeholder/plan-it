package com.example.antoan.planit.ui;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.example.antoan.planit.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Antoan on 2/22/2017.
 */

public class DrawerBuilder {
    private List<PrimaryDrawerItem> drawerItems;
    private Drawer drawer;

    @Inject
    public DrawerBuilder(List<PrimaryDrawerItem> drawerItems){
           this.drawerItems = drawerItems;
    }


    public void setDrawer(Activity activity, AccountHeader accountHeader, Toolbar toolbar, List<IDrawerItem> drawerItems, Drawer.OnDrawerItemClickListener onDrawerItemClickListener) {

        this.drawer = new com.mikepenz.materialdrawer.DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(accountHeader)
                .withToolbar(toolbar)
                .withDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(onDrawerItemClickListener)
                .build();
    }

    public void updateDrawerItem(int indetifier,int value){
        StringHolder p = new StringHolder(String.valueOf(value));
        if(value != 0){
            PrimaryDrawerItem item = drawerItems.get(indetifier-1).withBadge(p).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
            this.drawer.updateItem(item);
        }else{
            drawer.updateBadge(indetifier,null);
        }

    }
}
