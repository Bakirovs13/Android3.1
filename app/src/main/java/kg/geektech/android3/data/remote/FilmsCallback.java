package kg.geektech.android3.data.remote;

import java.util.List;

import kg.geektech.android3.data.models.Film;

public interface FilmsCallback {

    void success(List<Film>films);
    void failure(String msg);

}
