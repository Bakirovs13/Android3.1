package kg.geektech.android3;

import android.app.Application;

import kg.geektech.android3.data.remote.FilmsApi;
import kg.geektech.android3.data.remote.FilmsApiClient;
import kg.geektech.android3.data.remote.RetrofitClient;

public class App extends Application {

    public static FilmsApi api;
    public static FilmsApiClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        api = retrofitClient.provideFilmsApi(); //init api
        client = new FilmsApiClient();
    }
}
