package es.ucm.fdi.pad.googlebooksclient;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private static String TAG = ResultsActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private BookResultAdapter bookResultAdapter;

    private TextView resultTextView;

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

        //ActionBar para volver ara atras
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Resultados");
            Log.i(TAG, "ActionBar configurado");
        }

        recyclerView = findViewById(R.id.recyclerView2);
        resultTextView = findViewById(R.id.textView2);

        ArrayList<BookInfo> bookList = getIntent().getParcelableArrayListExtra("books");

        if(bookList == null){
            bookList = new ArrayList<>();
            Log.w(TAG, "No se encontraron libros en la intent");
        }
        Log.i(TAG, "Libros recibidos: " + bookList.size());

        bookResultAdapter = new BookResultAdapter(this);
        recyclerView.setAdapter(bookResultAdapter);

        updateBookResultsList(bookList);

        if (bookList.isEmpty()) {
            resultTextView.setText("No se han encontrado resultados");
            Log.w(TAG, "No se han encontrado resultados");
        } else {
            resultTextView.setText("Resultados (" + bookList.size() + ")");
            Log.i(TAG, "Se han encontrado " + bookList.size() + " resultados");
        }
    }

    private void updateBookResultsList(ArrayList<BookInfo> books){
        bookResultAdapter.setBooksData(books);
        bookResultAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}