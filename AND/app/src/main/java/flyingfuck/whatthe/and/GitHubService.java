package flyingfuck.whatthe.and;

import androidx.lifecycle.LiveData;

import java.util.List;

import flyingfuck.whatthe.and.ui.search.SearchEntry;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("items")
    Call<Item> listRepos(@Query("page") String page);
}