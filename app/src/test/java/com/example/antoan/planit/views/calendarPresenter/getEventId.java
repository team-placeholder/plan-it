package com.example.antoan.planit.views.calendarPresenter;

import com.data.models.EventResponse;
import com.data.models.PlanedEvent;
import com.data.services.EventsService;
import com.example.antoan.planit.adapters.AdaptersFactory;
import com.example.antoan.planit.view.calendar.CalendarContracts;
import com.example.antoan.planit.view.calendar.CalendarPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by hristov on 6.3.2017 г..
 */

public class getEventId {
    private EventResponse eventResponse;
    final String ID_FIRST = "1";
    final String ID_SECOND = "2";
    final String ID_THIRD = "3";

    @Mock
    EventsService eventsService;

    @Mock
    CalendarContracts.View view;

    @Mock
    AdaptersFactory adaptersFactory;

    @Before
    public void initTest(){
        MockitoAnnotations.initMocks(this);
    }

    public getEventId() {
        PlanedEvent first = new PlanedEvent();
        first.setId(ID_FIRST);

        PlanedEvent second = new PlanedEvent();
        second.setId(ID_SECOND);

        PlanedEvent third = new PlanedEvent();
        third.setId(ID_THIRD);

        this.eventResponse = new EventResponse();
        eventResponse.setEvents(new PlanedEvent[]{
                first, second, third
        });
    }

    @Test
    public void ShouldReturnEventOnCorrespondingPosition_WhenCalled() {
        // Arrange
        int year=0;
        int month=0;
        int day=0;
        when(this.eventsService.getDailyEvents(year, month, day))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        CalendarPresenter presenter = new CalendarPresenter(this.view, this.eventsService, this.adaptersFactory);
        presenter.getEventsForDay(year, month, day);

        // Assert
        Assert.assertEquals(eventResponse.getEvents()[0].getId(),presenter.getEventId(0));
    }
}
