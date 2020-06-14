package flyingfuck.whatthe.and.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import flyingfuck.whatthe.and.MainActivity;
import flyingfuck.whatthe.and.OnSwipeTouchListener;
import flyingfuck.whatthe.and.R;
import flyingfuck.whatthe.and.ui.search.SearchEntry;
import flyingfuck.whatthe.and.ui.search.SearchEntryAdapter;
import flyingfuck.whatthe.and.ui.search.SearchEntryVM;

public class HomeFragment extends Fragment {

    private SearchEntryVM searchHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchHistoryViewModel =
                ViewModelProviders.of(getActivity()).get(SearchEntryVM.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final SearchEntryAdapter adapter = new SearchEntryAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        FloatingActionButton fab2 = root.findViewById(R.id.fab2);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHistoryViewModel.forward();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHistoryViewModel.backward();
            }
        });

        searchHistoryViewModel.getPage().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                adapter.setEntries(searchHistoryViewModel.updateWebEntries(integer.intValue()).getValue());
            }
        });

        root.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                searchHistoryViewModel.backward();
            }

            @Override
            public void onSwipeRight()
            {
                searchHistoryViewModel.forward();
            }
        });

        adapter.setOnItemClickListener(new SearchEntryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SearchEntry entry) {
                searchHistoryViewModel.insert(entry);
            }
        });

        return root;
    }
}
