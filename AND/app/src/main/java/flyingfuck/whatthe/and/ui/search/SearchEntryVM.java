package flyingfuck.whatthe.and.ui.search;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class SearchEntryVM extends AndroidViewModel {
    private SearchEntryRepo repository;
    private MutableLiveData<Integer> page = new MutableLiveData<>(1);
    private LiveData<List<SearchEntry>> allEntries;
    private MutableLiveData<List<SearchEntry>> allWebEntries;

    public SearchEntryVM(@NonNull Application application) {
        super(application);
        repository = new SearchEntryRepo(application);
        allEntries = repository.getAllEntries();
        allWebEntries = repository.getAllWebEntries(page.getValue().intValue());
    }

    public void insert(SearchEntry entry) {
        repository.insert(entry);
    }

    public void update(SearchEntry entry) {
        repository.update(entry);
    }

    public void delete(SearchEntry entry) {
        repository.delete(entry);
    }

    public void deleteAllEntries() {
        repository.deleteAllEntries();
    }

    public MutableLiveData<List<SearchEntry>> updateWebEntries(int page) {
        this.allWebEntries = repository.getAllWebEntries(page);
        return allWebEntries;
    }

    public LiveData<List<SearchEntry>> getAllEntries() {
        return allEntries;
    }

    public MutableLiveData<List<SearchEntry>> getAllWebEntries() {
        return allWebEntries;
    }

    public void forward()
    {
        page.setValue(page.getValue()+1);
    }

    public void backward()
    {
        if(page.getValue() == 1)
        {

        }
        else
        {
            page.setValue(page.getValue()-1);
        }
    }
    public MutableLiveData<Integer> getPage()
    {
        return page;
    }
}