package com.gft.menospizza.manager;

import com.gft.menospizza.business.OrderBusiness;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Order;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by tiago on 18/01/18.
 */

public class OrderManager {

    private OrderBusiness business;

    public OrderManager() {
        this.business = new OrderBusiness();
    }

    public void sendOrder(final CustomRequestCallback<Response<ResponseBody>> callback, Order order) {
        business.postOrder(callback, order);
    }

}
