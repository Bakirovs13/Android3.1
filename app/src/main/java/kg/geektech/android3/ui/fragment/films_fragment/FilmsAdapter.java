package kg.geektech.android3.ui.fragment.films_fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.android3.data.models.Film;
import kg.geektech.android3.databinding.ItemFilmBinding;
import kg.geektech.android3.interfaces.onItemClickListener;

public class FilmsAdapter  extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {


    private List<Film> films  = new ArrayList<>();
    private onItemClickListener onItemClickListener;


    @SuppressLint("NotifyDataSetChanged")
    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Film getItem(int position){
        return films.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return  new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         private final ItemFilmBinding binding;

        public ViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.title.setText(film.getTitle());
            binding.description.setText(film.getDescription());
            itemView.setOnClickListener(view ->
                    onItemClickListener.onClick(getAdapterPosition()));
        }
    }
}
