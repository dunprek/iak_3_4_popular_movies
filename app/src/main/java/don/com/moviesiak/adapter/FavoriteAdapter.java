package don.com.moviesiak.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import don.com.moviesiak.Constants;
import don.com.moviesiak.R;
import don.com.moviesiak.activity.DetailActivity;
import don.com.moviesiak.model.ResultsItem;

/**
 * Created by gideon on 24/02/18.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    ArrayList<ResultsItem> data;
    Context context;


    public FavoriteAdapter(ArrayList<ResultsItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ADD VIEW TO VIEW HOLDER WHICH IS LIST ITEM LAYOUT
        //
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_favorite, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


   /*    -----------cuma bikin gambar doang ------
   ///set text ke holder setelah data di dapatkan
        holder.tvName.setText(data.get(position).getTitle());
        holder.tvOverview.setText(data.get(position).getOverview());*/
   /*-----------------------------------------------------------------*/


        String pathPoster = data.get(position).getPosterPath();
        //image picasso
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + pathPoster)
                .resize(500, 750)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_poster);
            //set click listener for  viewholder
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_poster:
                    Log.d("TAG", "onClick " + getAdapterPosition());

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MOVIE_POSTER", data.get(getAdapterPosition()).getPosterPath());
                    intent.putExtra("MOVIE_TITLE", data.get(getAdapterPosition()).getTitle());
                    intent.putExtra("MOVIE_YEAR", data.get(getAdapterPosition()).getReleaseDate());
                    intent.putExtra("MOVIE_RATING", data.get(getAdapterPosition()).getVoteAverage());
                    intent.putExtra("MOVIE_OVERVIEW", data.get(getAdapterPosition()).getOverview());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;


            }


        }
    }
}