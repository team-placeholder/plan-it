package com.example.antoan.planit.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.data.models.PlanedEvent;
import com.data.models.User;

import java.util.List;

/**
 * Created by Antoan on 3/6/2017.
 */

public class AdaptersFactory {

    public AdaptersFactory(){

    }

    public EventsAdapter CreateEventsAdapter(Context context, int layoutId, List<PlanedEvent> data){
        return new EventsAdapter(context,layoutId,data);
    }

    public RequestsAdapter createRequestAdapter(Context context, int layoutId,List<User> data){
        return new RequestsAdapter(context,layoutId,data);
    }

    public UserAdapter createUserAdapter(Context context, int layoutResourceId, List<User> data){
        return new UserAdapter(context, layoutResourceId, data);
    }

    public TabsNavigationAdapter createTabsNavigationAdapter(FragmentManager fm, List<String> tabsName, List<Fragment> fragments){
        return new TabsNavigationAdapter(fm,tabsName,fragments);
    }
}
