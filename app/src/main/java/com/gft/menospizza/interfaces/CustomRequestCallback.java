package com.gft.menospizza.interfaces;

/**
 * Created by tiago on 16/01/18.
 */

public interface CustomRequestCallback<T> {

    void onSuccess(T response);

    void onFailure(Throwable throwable);

    void onFailure(String message);

}
