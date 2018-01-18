package com.gft.menospizza.provider;

import com.gft.menospizza.interfaces.OrderService;
import com.gft.menospizza.model.Order;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by tiago on 18/01/18.
 */

public class OrderProvider extends BaseRestProvider {

    private OrderService service;

    public OrderProvider(String url) {
        super(url);
        service = retrofit.create(OrderService.class);
    }

    public Call<ResponseBody> sendOrder(Order order) {
        return service.sendOrder(order);
    }
}
