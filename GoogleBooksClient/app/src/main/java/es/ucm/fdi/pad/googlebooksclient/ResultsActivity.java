package es.ucm.fdi.pad.googlebooksclient;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private static final String TAG = ResultsActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private BookResultAdapter bookResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG, "LayoutManager configurado");

        ArrayList<BookInfo> bookList = getIntent().getParcelableArrayListExtra("books");

        if (bookList == null) {
            bookList = new ArrayList<>();
            Log.w(TAG, "No se encontraron libros en la intent");
        }
        Log.i(TAG, "Libros recibidos: " + bookList.size());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setTitle(R.string.results + "(" + bookList.size() + ")");
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            Log.i(TAG, "ActionBar configurado");
        }

        bookResultAdapter = new BookResultAdapter(this);
        recyclerView.setAdapter(bookResultAdapter);

        updateBookResultsList(bookList);

        if (bookList.isEmpty()) {
            Log.w(TAG, "No se han encontrado resultados");
        } else {
            Log.i(TAG, "Se han encontrado " + bookList.size() + " resultados");
        }
    }

    private void updateBookResultsList(ArrayList<BookInfo> books) {
        bookResultAdapter.setBooksData(books);
        bookResultAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}