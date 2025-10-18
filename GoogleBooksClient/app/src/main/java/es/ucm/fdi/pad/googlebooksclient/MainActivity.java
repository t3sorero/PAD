package es.ucm.fdi.pad.googlebooksclient;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int BOOK_LOADER_ID = 1;

    private final String TAG = MainActivity.class.getSimpleName();

    private EditText titleEditText;
    private EditText authorEditText;
    private RadioGroup radioGroup;
    private Button searchButton;
    private BookLoaderCallbacks bookLoaderCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate llamado");


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleEditText = findViewById(R.id.editTextText);
        authorEditText = findViewById(R.id.editTextText2);

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().trim().isEmpty()) {
                    clearFieldError(titleEditText);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        authorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().trim().isEmpty()) {
                    clearFieldError(titleEditText);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        radioGroup = findViewById(R.id.radioGroup4);
        searchButton = findViewById(R.id.button);


        bookLoaderCallbacks = new BookLoaderCallbacks();

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null) {
            loaderManager.initLoader(BOOK_LOADER_ID, null,
                    bookLoaderCallbacks);
        }

        searchButton.setOnClickListener(View -> searchBooks());

    }

    public void searchBooks() {

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton searchType = findViewById(selectedRadioButtonId);

        String printType = searchType.getTag().toString();

        String query = queryBuilder(selectedRadioButtonId);

        if (query == null) return;

        Log.i(TAG, "Buscando libros con query: " + query);


        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, query);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);

        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID,
                queryBundle, bookLoaderCallbacks);
    }


    private void showFieldError(EditText field, @StringRes int msg) {
        field.setError(getString(msg));
        field.setHintTextColor(ContextCompat.getColor(this, R.color.error));
        field.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.error)));
        field.requestFocus();
    }

    private void clearFieldError(EditText field) {
        field.setError(null);
        field.setHintTextColor(ContextCompat.getColor(this, R.color.neutral_gray));
        field.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray_400)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleEditText.setText("");
        authorEditText.setText("");
        titleEditText.requestFocus();
    }

    public String queryBuilder(int selectedRadioButtonId) {

        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();

        StringBuilder queryBuilder = new StringBuilder();


        if (selectedRadioButtonId == R.id.radioButton2) {
            //LIBROS
            if (author.isEmpty() && title.isEmpty()) {
                showFieldError(titleEditText, R.string.error_at_least_one_field);
                return null;
            }
            if (!title.isEmpty()) {
                queryBuilder.append("intitle:").append(title);
            }
            if (!author.isEmpty()) {
                if (!title.isEmpty()) {
                    queryBuilder.append("+");
                }
                queryBuilder.append("inauthor:").append(author);
            }

        } else if (selectedRadioButtonId == R.id.radioButton4) {
            //REVISTAS
            if (title.isEmpty()) {
                showFieldError(titleEditText, R.string.error_title_required);
                return null;
            } else {
                queryBuilder.append("intitle:").append(title);
            }
        } else {
            //TODOS
            if (author.isEmpty() && title.isEmpty()) {
                showFieldError(titleEditText, R.string.error_at_least_one_field);
                return null;
            }
            if (!title.isEmpty()) {
                queryBuilder.append("intitle:").append(title);
            }
            if (!author.isEmpty()) {
                if (!title.isEmpty()) {
                    queryBuilder.append("+");
                }
                queryBuilder.append("inauthor:").append(author);
            }
        }
        return queryBuilder.toString();
    }

    private class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

        private final String TAG = BookLoaderCallbacks.class.getSimpleName();

        public static final String EXTRA_QUERY = "query";
        public static final String EXTRA_PRINT_TYPE = "printType";

        @NonNull
        @Override
        public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {

            Log.i(TAG, "onCreateLoader llamado");

            String queryString = "";
            String printType = "all";

            if (args != null) {
                queryString = args.getString(EXTRA_QUERY, "");
                printType = args.getString(EXTRA_PRINT_TYPE, "all");

            }
            return new BookLoader(MainActivity.this, queryString, printType);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
            Log.i(TAG, "onLoadFinished llamado - Libros recibidos: " + (data != null ? data.size() : 0));
            if (data == null || data.isEmpty()) {
                Log.i(TAG, "No se encontraron libros");
                Toast.makeText(MainActivity.this, "No se encontraron libros", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putParcelableArrayListExtra("books", new ArrayList<>(data));
            Log.i(TAG, "Iniciando ResultsActivity");
            startActivity(intent);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
            Log.i(TAG, "onLoaderReset llamado");
            //Limpiar referencia al loader, pero no es necesario ya que no se usan
        }
    }
}

