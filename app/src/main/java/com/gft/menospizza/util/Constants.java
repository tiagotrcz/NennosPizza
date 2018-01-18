package com.gft.menospizza.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by tiago on 16/01/18.
 */

public class Constants {

    public static String BASE_URL = "https://api.myjson.com/bins/";
    public static String POST_URL = "http://posttestserver.com/";

    public static String BASE_PRICE = "BASE_PRICE";
    public static String PIZZA = "PIZZA";
    public static String INGREDIENTS = "INGREDIENTS";
    public static String CART = "CART";

    public static long READ_TIMEOUT_VALUE = 40;
    public static TimeUnit READ_TIMEOUT_UNIT = TimeUnit.SECONDS;
    public static long CONNECT_TIMEOUT_VALUE = 40;
    public static TimeUnit CONNECT_TIMEOUT_UNIT = TimeUnit.SECONDS;

}
