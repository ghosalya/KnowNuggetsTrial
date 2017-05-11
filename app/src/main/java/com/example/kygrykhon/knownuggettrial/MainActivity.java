package com.example.kygrykhon.knownuggettrial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //private static String permanentKey;
    private static APIPermKey permanentKey;
    private final static String temporaryKey = "oVQST5D6NT";
    private static APIInterface apiService;
    private Coordinate coor1, coor2;
    private static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = APIClient.getClient().create(APIInterface.class);

        acquirePermanentKey();

        coor1 = null;
        coor2 = null;

    }

    public synchronized void coordinateSet(Coordinate coor) {
        if(coor1 == null) coor1 = coor;
        else if (coor2 == null) coor2 = coor;

        if(coor1 != null && coor2!=null) { //finished fetching 2 coordinates
            if(dialog!=null) {
                dialog.setProgress(dialog.getMax());
            }

            double distance = coor1.distanceTo(coor2);
            String display = "Distance: "+distance;
            Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
        }
    }

    public void getDistance(View view) {
        coor1 = null;
        coor2 = null;
        dialog = ProgressDialog.show(getApplicationContext(), "Getting distance..", "Please wait while we fetch coordinates..", true );
        acquireCoordinate();
        acquireCoordinate();
    }

    private void acquirePermanentKey() {
        Call<APIPermKey> call = apiService.getPermanentKey(temporaryKey);
        call.enqueue(new Callback<APIPermKey>() {
            @Override
            public void onResponse(Call<APIPermKey> call, Response<APIPermKey> response) {
                //permanentKey = response.body().getPermKey();
                permanentKey = response.body();
                if(permanentKey!=null) {
                    Log.d("getKeySuccess", "Permanent Key get!" + permanentKey);
                } else {
                    Log.d("getKeyNull", "Get a null key object");
                }
            }

            @Override
            public void onFailure(Call<APIPermKey> call, Throwable t) {
                Log.d("getKeyFailure", "failed to get permanent key");
            }
        });
    }

    private void acquireCoordinate() {
        Call<Coordinate> call = apiService.getCoordinate(permanentKey.getPermKey());

        call.enqueue(new Callback<Coordinate>() {
            @Override
            public void onResponse(Call<Coordinate> call, Response<Coordinate> response) {
                coordinateSet(response.body());
            }

            @Override
            public void onFailure(Call<Coordinate> call, Throwable t) {

            }
        });
    }
}
