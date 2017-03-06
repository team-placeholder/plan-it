package com.example.antoan.planit.views.eventDetailsPresenter;

import com.data.models.EventResponse;
import com.data.models.PlanedEvent;
import com.data.services.EventsService;
import com.example.antoan.planit.view.eventDetails.EventDetailsContracts;
import com.example.antoan.planit.view.eventDetails.EventDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hristov on 6.3.2017 г..
 */

public class start {
    private final PlanedEvent event;
    private EventResponse eventResponse;
    private String id;

    @Mock
    EventsService eventsService;

    @Mock
    EventDetailsContracts.View view;

    public start()
    {
        this.id = "id";
        this.eventResponse = new EventResponse();
        this.eventResponse.setMessage("message");
        this.event = new PlanedEvent();
        event.setId(this.id);
    }

    @Before
    public void initTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldCallViewNotify_WhenReceivedEventIsNull(){
        // Arrange
        this.eventResponse.setEvent(null);

        when(this.eventsService.getEventById(this.id))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        EventDetailsPresenter presenter = new EventDetailsPresenter(this.view, this.eventsService);
        presenter.setEventId(this.id);
        presenter.start();

        // Assert
        verify(this.view).notify(anyString());
    }

    @Test
    public void ShouldCallViewNotify_WhenReceivedEventIsNotNull(){
        // Arrange
        this.eventResponse.setEvent(this.event);

        when(this.eventsService.getEventById(this.id))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        EventDetailsPresenter presenter = new EventDetailsPresenter(this.view, this.eventsService);
        presenter.setEventId(this.id);
        presenter.start();

        // Assert
        verify(this.view).notify(anyString());
    }


    @Test
    public void ShouldCallViewSetEvent_WhenReceivedEventIsNotNull(){
        // Arrange
        this.eventResponse.setEvent(this.event);

        when(this.eventsService.getEventById(this.id))
                .thenReturn(Observable.just(this.eventResponse));

        // Act
        EventDetailsPresenter presenter = new EventDetailsPresenter(this.view, this.eventsService);
        presenter.setEventId(this.id);
        presenter.start();

        // Assert
        verify(this.view).setEvent(any(PlanedEvent.class));
    }
}
