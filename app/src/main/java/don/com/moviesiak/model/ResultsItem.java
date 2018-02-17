package don.com.moviesiak.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gson.annotations.SerializedName;

public class ResultsItem  implements Parcelable{

    @SerializedName("overview")
    private String overview;

    @SerializedName("title")
    private String title;


    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;


    public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
        @Override
        public ResultsItem createFromParcel(Parcel in) {
            return new ResultsItem(in);
        }

        @Override
        public ResultsItem[] newArray(int size) {
            return new ResultsItem[size];
        }
    };

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
    // Parcelling part
    public ResultsItem(Parcel in) {
        this.overview = in.readString();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
    }




    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        "overview = '" + overview + '\'' +
                        ",title = '" + title + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        "}";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(overview);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeDouble(voteAverage);
    }
}