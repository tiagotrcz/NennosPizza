package com.gft.menospizza.interfaces;

import com.gft.menospizza.model.Cart;
import com.gft.menospizza.model.Order;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tiago on 18/01/18.
 */

public interface OrderService {

    @POST("post.php")
    Call<ResponseBody> sendOrder(@Body Order order);

}
