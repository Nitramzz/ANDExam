package flyingfuck.whatthe.and;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Looper;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import flyingfuck.whatthe.and.ui.search.SearchEntry;
import flyingfuck.whatthe.and.ui.search.SearchEntryDB;
import flyingfuck.whatthe.and.ui.search.SearchEntryVM;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("flyingfuck.whatthe.and", appContext.getPackageName());
    }

    @Test
    public void DBTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SearchEntryDB db = SearchEntryDB.getInstance(appContext);
        LiveData<List<SearchEntry>> a = db.searchEntryDAO().getAllEntries();

        assertEquals(a.getValue().get(0).getName().toUpperCase(),"Abyssal whip".toUpperCase());
    }

    @Test
    public void DBTestDelete()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SearchEntryDB db = SearchEntryDB.getInstance(appContext);
        db.searchEntryDAO().delete(db.searchEntryDAO().getAllEntries().getValue().get(0));

        LiveData<List<SearchEntry>> a = db.searchEntryDAO().getAllEntries();

        assertNotEquals(a.getValue().get(0).getName().toUpperCase(),"test 1".toUpperCase());
    }


    @Test
    public void VMTestRepoEntries()
    {
        Looper.prepare();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SearchEntryDB db = SearchEntryDB.getInstance(appContext);
        LiveData<List<SearchEntry>> a = db.searchEntryDAO().getAllEntries();


        MainActivity activity = new MainActivity();
        SearchEntryVM b = new SearchEntryVM(activity.getApplication());
        assertEquals(b.getAllEntries().getValue(), a.getValue());
    }

    @Test
    public void VMTestWebEntries()
    {
        Looper.prepare();
        MainActivity activity = new MainActivity();
        SearchEntryVM b = new SearchEntryVM(activity.getApplication());

        assertEquals(b.getAllWebEntries().getValue().get(0).getName().toUpperCase(), "Dwarf remains".toUpperCase());
    }

    @Test
    public void VMTestPage()
    {
        Looper.prepare();
        MainActivity activity = new MainActivity();
        SearchEntryVM b = new SearchEntryVM(activity.getApplication());
        b.forward();
        b.forward();
        b.backward();
        assertEquals(b.getPage().getValue().intValue(), 2);
    }
}
