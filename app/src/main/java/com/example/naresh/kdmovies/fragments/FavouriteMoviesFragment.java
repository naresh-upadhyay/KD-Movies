package com.example.naresh.kdmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.naresh.kdmovies.R;
import com.example.naresh.kdmovies.adpters.MovieBriefsSmallAdapter;
import com.example.naresh.kdmovies.network.movies.MovieBrief;
import com.example.naresh.kdmovies.utils.Favourite;

import java.util.ArrayList;
import java.util.List;


public class FavouriteMoviesFragment extends Fragment {

    private RecyclerView mFavMoviesRecyclerView;
    private List<MovieBrief> mFavMovies;
    private MovieBriefsSmallAdapter mFavMoviesAdapter;

    private LinearLayout mEmptyLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_movies, container, false);

        mFavMoviesRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fav_movies);
        mFavMovies = new ArrayList<>();
        mFavMoviesAdapter = new MovieBriefsSmallAdapter(getContext(), mFavMovies);
        mFavMoviesRecyclerView.setAdapter(mFavMoviesAdapter);
        mFavMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        mEmptyLayout = (LinearLayout) view.findViewById(R.id.layout_recycler_view_fav_movies_empty);

        loadFavMovies();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO (feature or a bug? :P)
        // for now when coming back to this activity after removing from fav it shows border heart.
        mFavMoviesAdapter.notifyDataSetChanged();
    }

    private void loadFavMovies() {
        List<MovieBrief> favMovieBriefs = Favourite.getFavMovieBriefs(getContext());
        if (favMovieBriefs.isEmpty()) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            return;
        }

        for (MovieBrief movieBrief : favMovieBriefs) {
            mFavMovies.add(movieBrief);
        }
        mFavMoviesAdapter.notifyDataSetChanged();
    }

}
