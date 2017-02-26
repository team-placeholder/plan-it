package com.example.antoan.planit.view.editProfile;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.TabsNavigationAdapter;
import com.example.antoan.planit.view.editProfile.changeAvatar.ChangeAvatarContract;
import com.example.antoan.planit.view.editProfile.changeAvatar.ChangeAvatarView;
import com.example.antoan.planit.view.editProfile.changePassword.ChangePasswordContract;
import com.example.antoan.planit.view.editProfile.changePassword.ChangePasswordView;

import java.util.ArrayList;

import javax.inject.Inject;

public class EditProfileActivity extends AppCompatActivity {

    @Inject
    public ChangeAvatarContract.Presenter changeAvatarPresenter;
    @Inject
    public ChangePasswordContract.Presenter changePasswordPresenter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ArrayList<String> tabsNames = new ArrayList<>();
        tabsNames.add("Change Password");
        tabsNames.add("Change Avatar");

        this.injectDependancies();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) changePasswordPresenter.getView());
        fragments.add((Fragment) changeAvatarPresenter.getView());


        this.pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new TabsNavigationAdapter(getSupportFragmentManager(),tabsNames,fragments));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private void injectDependancies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }


}
