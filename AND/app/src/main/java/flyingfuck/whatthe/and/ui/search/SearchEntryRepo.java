package flyingfuck.whatthe.and.ui.search;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import flyingfuck.whatthe.and.WebAPI;

public class SearchEntryRepo {
    private SearchEntryDAO entryDAO;
    private LiveData<List<SearchEntry>> allEntries;
    private MutableLiveData<List<SearchEntry>> allWebEntries;
    private WebAPI api;

    public SearchEntryRepo(Application application) {
        SearchEntryDB database = SearchEntryDB.getInstance(application);
        entryDAO = database.searchEntryDAO();
        allEntries = entryDAO.getAllEntries();
        api = new WebAPI();
        allWebEntries = api.getWebEntries(1);
    }

    public void insert(SearchEntry entry) {
        new InsertEntryAsyncTask(entryDAO).execute(entry);
    }

    public void update(SearchEntry entry) {
        new UpdateEntryAsyncTask(entryDAO).execute(entry);
    }

    public void delete(SearchEntry entry) {
        new DeleteEntryAsyncTask(entryDAO).execute(entry);
    }

    public void deleteAllEntries() {
        new DeleteAllEntriesAsyncTask(entryDAO).execute();
    }

    public LiveData<List<SearchEntry>> getAllEntries() {
        return allEntries;
    }

    public  MutableLiveData<List<SearchEntry>> getAllWebEntries(int page)
    {
        this.allWebEntries = api.getWebEntries(page);
        return allWebEntries;
    }

    private static class InsertEntryAsyncTask extends AsyncTask<SearchEntry, Void, Void> {
        private SearchEntryDAO entryDAO;

        private InsertEntryAsyncTask(SearchEntryDAO entryDAO1) {
            this.entryDAO = entryDAO1;
        }

        @Override
        protected Void doInBackground(SearchEntry... entries) {
            entryDAO.insert(entries[0]);
            return null;
        }
    }

    private static class UpdateEntryAsyncTask extends AsyncTask<SearchEntry, Void, Void> {
        private SearchEntryDAO entryDAO;

        private UpdateEntryAsyncTask(SearchEntryDAO entryDAO1) {
            this.entryDAO = entryDAO1;
        }

        @Override
        protected Void doInBackground(SearchEntry... entries) {
            entryDAO.update(entries[0]);
            return null;
        }
    }

    private static class DeleteEntryAsyncTask extends AsyncTask<SearchEntry, Void, Void> {
        private SearchEntryDAO entryDAO;

        private DeleteEntryAsyncTask(SearchEntryDAO entryDAO1) {
            this.entryDAO = entryDAO1;
        }

        @Override
        protected Void doInBackground(SearchEntry... entries) {
            entryDAO.delete(entries[0]);
            return null;
        }
    }

    private static class DeleteAllEntriesAsyncTask extends AsyncTask<Void, Void, Void> {
        private SearchEntryDAO entryDAO;

        private DeleteAllEntriesAsyncTask(SearchEntryDAO entryDAO1) {
            this.entryDAO = entryDAO1;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            entryDAO.deleteAllEntries();
            return null;
        }
    }
}