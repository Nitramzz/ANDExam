package flyingfuck.whatthe.and.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import flyingfuck.whatthe.and.EntryActivity;
import flyingfuck.whatthe.and.MainActivity;
import flyingfuck.whatthe.and.R;

public class SearchHistoryFragment extends Fragment {

    private SearchEntryVM searchHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final SearchEntryAdapter adapter = new SearchEntryAdapter();
        recyclerView.setAdapter(adapter);

        searchHistoryViewModel =
                ViewModelProviders.of(getActivity()).get(SearchEntryVM.class);
        searchHistoryViewModel.getAllEntries().observe(getViewLifecycleOwner(), new Observer<List<SearchEntry>>() {
            @Override
            public void onChanged(@Nullable List<SearchEntry> searchEntries) {
                adapter.setEntries(searchEntries);
            }
        });

        adapter.setOnItemClickListener(new SearchEntryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SearchEntry entry) {
                Intent intent = new Intent(getContext(),
                        EntryActivity.class);
                intent.putExtra("Entry", entry);
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                searchHistoryViewModel.delete(adapter.getEntriesAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Entry deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }
}
