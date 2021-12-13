package kg.geektech.android3.data.remote;

import androidx.annotation.NonNull;

import java.util.List;

import kg.geektech.android3.App;
import kg.geektech.android3.data.models.Film;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsApiClient {

    public void getFilms(FilmsCallback filmsCallback){
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(@NonNull Call<List<Film>> call, @NonNull Response<List<Film>> response) {
                if (response.isSuccessful()&& response.body()!= null){
                    filmsCallback.success(response.body());
                }else {
                    assert response.errorBody() != null;
                    filmsCallback.failure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Film>> call, @NonNull Throwable t) {
                filmsCallback.failure(t.getLocalizedMessage());
            }
        });
    }



}
