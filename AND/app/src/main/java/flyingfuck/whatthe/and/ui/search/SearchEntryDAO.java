package flyingfuck.whatthe.and.ui.search;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SearchEntryDAO {
    @Insert
    void insert(SearchEntry entry);
    @Update
    void update(SearchEntry entry);
    @Delete
    void delete(SearchEntry entry);
    @Query("DELETE FROM entry_table")
    void deleteAllEntries();
    @Query("SELECT * FROM entry_table ORDER BY release_date DESC")
    LiveData<List<SearchEntry>> getAllEntries();
}