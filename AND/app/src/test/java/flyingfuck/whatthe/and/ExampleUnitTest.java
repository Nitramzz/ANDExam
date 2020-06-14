package flyingfuck.whatthe.and;

import android.content.Context;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import flyingfuck.whatthe.and.ui.home.HomeViewModel;
import flyingfuck.whatthe.and.ui.search.SearchEntry;
import flyingfuck.whatthe.and.ui.search.SearchEntryDB;
import flyingfuck.whatthe.and.ui.search.SearchEntryVM;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    //These will always fail as the async tasks are not mocked
    @Test
    public void WebAPITest1()
    {
        WebAPI api = new WebAPI();
        MutableLiveData<List<SearchEntry>> a = api.executeWebEntries(1);
        assertEquals(a.getValue().get(0).getName().toUpperCase(), "Dwarf remains".toUpperCase());
    }

    @Test
    public void WebAPITest2()
    {
        WebAPI api = new WebAPI();
        MutableLiveData<List<SearchEntry>> a = api.executeWebEntries(2);
        assertEquals(a.getValue().get(12).getName().toUpperCase(), "Black candle".toUpperCase());
    }

    @Test
    public void WebAPITest3()
    {
        WebAPI api = new WebAPI();
        MutableLiveData<List<SearchEntry>> a = api.executeWebEntries(3);
        assertEquals(a.getValue().get(24).getName().toUpperCase(), "Khazard armour".toUpperCase());
    }
}