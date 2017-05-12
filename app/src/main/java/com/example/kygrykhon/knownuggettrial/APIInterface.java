package com.example.kygrykhon.knownuggettrial;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Kygrykhon on 5/11/2017.
 */

public interface APIInterface {
    @POST("getKey")
    Call<APIPermKey> getPermanentKey(@Body APITempKey tempKey);

    @POST("getCoordinates")
    Call<Coordinate> getCoordinate(@Body APIPermKey permKey);
}
