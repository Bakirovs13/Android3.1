package kg.geektech.android3.ui.fragment.film_detail_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kg.geektech.android3.App;
import kg.geektech.android3.data.models.Film;
import kg.geektech.android3.databinding.FragmentFilmDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmDetailFragment extends Fragment {


    FragmentFilmDetailBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!= null){
            Bundle bundle = getArguments();
            String id = bundle.getString("key");
            App.api.getFilmById(id).enqueue(new Callback<Film>() {
                @Override
                public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                    if (response.isSuccessful()){
                       setData(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {

                }
            });
        }
    }
    @SuppressLint("SetTextI18n")
    private void setData(Response<Film> response) {
        assert response.body() != null;
        binding.title.setText(response.body().getTitle());
        binding.description.setText(response.body().getDescription());
        binding.director.setText("Director:  " +response.body().getDirector());
        binding.origTitle.setText(response.body().getOriginalTitle());
    }
}