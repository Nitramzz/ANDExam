package flyingfuck.whatthe.and;

import com.google.gson.annotations.Expose;

import java.util.List;

import flyingfuck.whatthe.and.ui.search.SearchEntry;

public class Item {
    @Expose
    private List<SearchEntry> _items;

    public Item(List<SearchEntry> _items) {
        this._items = _items;
    }

    public List<SearchEntry> get_items() {
        return _items;
    }

    public void set_items(List<SearchEntry> _items) {
        this._items = _items;
    }
}
