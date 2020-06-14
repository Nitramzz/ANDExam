package flyingfuck.whatthe.and.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import flyingfuck.whatthe.and.R;

public class SearchEntryAdapter extends RecyclerView.Adapter<SearchEntryAdapter.SearchHolder> {
    private List<SearchEntry> entries = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);
        return new SearchHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        SearchEntry currentEntry = entries.get(position);
        //TODO fix these to match the model
        holder.textViewTitle.setText(currentEntry.getName());
        holder.textViewPriority.setText(currentEntry.getWebId());
        holder.icon.setImageBitmap(currentEntry.getBitmapIcon());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setEntries(List<SearchEntry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    public SearchEntry getEntriesAt(int position) {
        return entries.get(position);
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPriority;
        private ImageView icon;

        public SearchHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            icon = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(entries.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SearchEntry entry);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}