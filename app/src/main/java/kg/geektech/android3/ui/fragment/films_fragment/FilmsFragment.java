package kg.geektech.android3.ui.fragment.films_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.geektech.android3.App;
import kg.geektech.android3.R;
import kg.geektech.android3.data.models.Film;
import kg.geektech.android3.data.remote.FilmsCallback;
import kg.geektech.android3.databinding.FragmentFilmsBinding;


public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    public FilmsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFilmsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initListeners();

        App.client.getFilms(new FilmsCallback() {
            @Override
            public void success(List<Film> films) {
                adapter.setFilms(films);
            }

            @Override
            public void failure(String msg) {
                Log.e("TAG","failure: "+msg);
            }
        });

    }

    private void initListeners() {
        adapter.setOnItemClickListener(position -> {
            Film film = adapter.getItem(position);
           openDetailFragment(film.getId());
        });
    }

    private void openDetailFragment(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("key",id);
        NavController navController = Navigation.findNavController(requireActivity(),R.id.container);
        navController.navigate(R.id.filmDetailFragment,bundle);
    }

    private void initAdapter() {
        adapter = new FilmsAdapter();
        binding.filmsRecycler.setAdapter(adapter);
    }
}