package com.gft.menospizza.business;

import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Order;
import com.gft.menospizza.provider.OrderProvider;
import com.gft.menospizza.util.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 18/01/18.
 */

public class OrderBusiness {

    private OrderProvider provider;

    public OrderBusiness() {
        provider = new OrderProvider(Constants.POST_URL);
    }

    public void postOrder(final CustomRequestCallback<Response<ResponseBody>> callback,  Order order) {
        provider.sendOrder(order).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    callback.onSuccess(response);
                else
                    callback.onFailure(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
