package flyingfuck.whatthe.and;

import android.os.AsyncTask;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import flyingfuck.whatthe.and.ui.search.SearchEntry;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebAPI {
    private MutableLiveData<List<SearchEntry>> webEntries;
    private static GitHubService service;

    public WebAPI()
    {
        service = getClient().create(GitHubService.class);
        webEntries = executeWebEntries(1);
    }

    public MutableLiveData<List<SearchEntry>> getWebEntries(int page)
    {
        this.webEntries = executeWebEntries(page);
        return webEntries;
    }

    public MutableLiveData<List<SearchEntry>> executeWebEntries(int page) {
        MutableLiveData<List<SearchEntry>> b = new MutableLiveData<>();
        try {
            List<SearchEntry> baba = new ExecuteWeb(page).execute().get();
            b.setValue(baba);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }

    private class ExecuteWeb extends AsyncTask<Void, Void, List<SearchEntry>> {
        private int page;

        public ExecuteWeb(int page)
        {
            this.page = page;
        }

        @Override
        protected List<SearchEntry> doInBackground(Void... voids) {
            List<SearchEntry> a = new ArrayList<>();
            try
            {
               a = service.listRepos(String.valueOf(this.page)).execute().body().get_items();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return a;
        }
    }


    static Retrofit getClient()
    {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("2005-01-26")
                .setLenient()
                .serializeNulls()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.osrsbox.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
