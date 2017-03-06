package com.example.antoan.planit.views.calendarPresenter;

import android.support.v4.app.FragmentActivity;

import com.data.services.EventsService;
import com.example.antoan.planit.utils.ICanNavigateActivity;
import com.example.antoan.planit.view.calendar.CalendarContracts;
import com.example.antoan.planit.view.calendar.CalendarPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hristov on 6.3.2017 г..
 */

public class navigateCreate {

    @Mock
    EventsService eventsService;

    @Mock
    CalendarContracts.View view;

    @Mock
    ICanNavigateActivity navigateActivity;


    @Before
    public void initTest(){
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void ShouldCallViewsActivityNavigateMethod_WhenCalled() {
        // Arrange
        int year=0;
        int month=0;
        int day=0;

        // TODO:  ask questions!
        when(this.view.getActivity())
                .thenReturn((FragmentActivity)navigateActivity);

        CalendarPresenter presenter = new CalendarPresenter(this.view, this.eventsService);
        presenter.setDate(year, month, day);

        // Act and Assert
        verify(this.navigateActivity).navigate(ArgumentMatchers.any());
    }*/
}
