package flyingfuck.whatthe.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.Serializable;

import flyingfuck.whatthe.and.ui.search.SearchEntry;
import flyingfuck.whatthe.and.ui.search.SearchHistoryFragment;

public class EntryActivity extends AppCompatActivity {
    private Serializable entry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final FloatingActionButton fab = findViewById(R.id.backbutton);
        final TextView name = findViewById(R.id.name);
        final TextView id = findViewById(R.id.id);
        final TextView release_date = findViewById(R.id.release_date);
        final TextView lowalch = findViewById(R.id.lowalch);
        final TextView highalch = findViewById(R.id.highalch);
        final TextView members = findViewById(R.id.members);
        final TextView tradeable = findViewById(R.id.tradeable);
        final ImageView icon = findViewById(R.id.icon);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        entry = getIntent().getSerializableExtra("Entry");
        SearchEntry current =(SearchEntry) entry;

        name.setText(current.getName());
        id.setText(current.getWebId());
        release_date.setText(current.getRelease_date());
        icon.setImageBitmap(current.getBitmapIcon());
        lowalch.setText(String.valueOf(current.getLowalch()));
        highalch.setText(String.valueOf(current.getHighalch()));

        if(current.isMembers())
            members.setText("True");
        else
            members.setText("False");

        if(current.isTradeable())
            tradeable.setText("True");
        else
            tradeable.setText("False");


    }
}
