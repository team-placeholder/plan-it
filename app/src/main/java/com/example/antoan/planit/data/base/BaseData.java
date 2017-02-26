package com.example.antoan.planit.data.base;

import com.example.antoan.planit.models.ResponsePair;

import io.reactivex.Observable;

/**
 * Created by Antoan on 2/18/2017.
 */

public interface BaseData<T> {
    Observable<T[]> getAll();

    Observable<T> getById(Object id);

    Observable<T> add(T item);

    Observable<Boolean> register(final T item);

    Observable<ResponsePair> authorize(final T item);

    Observable<Boolean> updateAvatar(final T item);

    Observable<Boolean> updatePassword(final T item);
}
