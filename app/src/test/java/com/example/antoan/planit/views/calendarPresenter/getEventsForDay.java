package com.example.antoan.planit.views.calendarPresenter;

import com.data.models.EventResponse;
import com.data.services.EventsService;
import com.example.antoan.planit.view.calendar.CalendarContracts;
import com.example.antoan.planit.view.calendar.CalendarPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hristov on 6.3.2017 г..
 */

public class getEventsForDay {
    private EventResponse eventResponse;

    @Mock
    EventsService eventsService;

    @Mock
    CalendarContracts.View view;

    public getEventsForDay() {
        this.eventResponse = new EventResponse();
    }

    @Before
    public void initTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldCallEventsServiceGetDailyEvents_WhenCalled(){
        // Arrange
        int year=0;
        int month=0;
        int day=0;
        when(this.eventsService.getDailyEvents(year, month, day))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        CalendarPresenter presenter = new CalendarPresenter(this.view, this.eventsService);
        presenter.getEventsForDay(year, month, day);

        // Assert
        verify(this.eventsService).getDailyEvents(year,month,day);
    }

    @Test
    public void ShouldCallViewNotify_WhenCalled(){
        //Arrange
        int year=0;
        int month=0;
        int day=0;
        when(this.eventsService.getDailyEvents(year, month, day))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        CalendarPresenter presenter = new CalendarPresenter(this.view,this.eventsService);
        presenter.getEventsForDay(year, month, day);

        // Assert
        verify(this.view).notify(this.eventResponse.getMessage());
    }

    @Test
    public void ShouldCallViewSetEvents_WhenCalled(){
        // Arrange
        int year=0;
        int month=0;
        int day=0;
        when(this.eventsService.getDailyEvents(year, month, day))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        CalendarPresenter presenter = new CalendarPresenter(this.view,this.eventsService);
        presenter.getEventsForDay(year, month, day);

        // Assert
        verify(this.view).setEvents(this.eventResponse.getEvents());
    }
}
