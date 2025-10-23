package es.ucm.fdi.pad.googlebooksclient;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
    private static final String TAG = "MainActivity";
    private static final int BOOK_LOADER_ID = 1;

    private EditText titleEditText;
    private EditText authorEditText;
    private RadioGroup typeRadioGroup;

    private BookLoaderCallbacks bookLoaderCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Log.d(TAG, "onCreate INICIADO");

        titleEditText = findViewById(R.id.editTextText);
        authorEditText = findViewById(R.id.editTextText2);
        typeRadioGroup = findViewById(R.id.radioGroup4);

        typeRadioGroup.check(R.id.radioButton5);

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    clearFieldError(titleEditText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        authorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    clearFieldError(authorEditText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        bookLoaderCallbacks = new BookLoaderCallbacks();

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null) {
            Log.d(TAG, "Loader ya existe");
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }

        Log.d(TAG, "onCreate FINALIZADO");
    }

    public void searchBooks(View view) {
        Log.d(TAG, "searchBooks LLAMADO");

        clearFieldError(titleEditText);
        clearFieldError(authorEditText);

        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();

        Log.d(TAG, "Título: " + title);
        Log.d(TAG, "Autor: " + author);

        int selectedId = typeRadioGroup.getCheckedRadioButtonId();
        String printType = "all";
        boolean isValid = true;

        StringBuilder queryBuilder = new StringBuilder();

        if (selectedId == R.id.radioButton2) {
            printType = "books";
            Log.d(TAG, "Tipo seleccionado: LIBROS");

            if (title.isEmpty() && author.isEmpty()) {
                showFieldError(titleEditText, R.string.error_at_least_one_field);
                showFieldError(authorEditText, R.string.error_at_least_one_field);
                Log.w(TAG, "Validación falló: ambos campos vacíos");
                isValid = false;
            } else {
                if (!author.isEmpty()) {
                    queryBuilder.append("inauthor:").append(author);
                }
                if (!title.isEmpty()) {
                    if (queryBuilder.length() > 0) {
                        queryBuilder.append("+");
                    }
                    queryBuilder.append("intitle:").append(title);
                }
            }

        } else if (selectedId == R.id.radioButton4) {
            printType = "magazines";
            Log.d(TAG, "Tipo seleccionado: REVISTAS");

            if (title.isEmpty() && author.isEmpty()) {
                showFieldError(titleEditText, R.string.error_at_least_one_field);
                showFieldError(authorEditText, R.string.error_at_least_one_field);
                Log.w(TAG, "Validación falló: ambos campos vacíos en revistas");
                isValid = false;
            } else {
                if (!author.isEmpty()) {
                    queryBuilder.append("inauthor:").append(author);
                }
                if (!title.isEmpty()) {
                    if (queryBuilder.length() > 0) {
                        queryBuilder.append("+");
                    }
                    queryBuilder.append("intitle:").append(title);
                }
            }

        } else if (selectedId == R.id.radioButton5) {
            printType = "all";
            Log.d(TAG, "Tipo seleccionado: TODO");

            if (title.isEmpty() && author.isEmpty()) {
                showFieldError(titleEditText, R.string.error_at_least_one_field);
                showFieldError(authorEditText, R.string.error_at_least_one_field);
                Log.w(TAG, "Validación falló: ambos campos vacíos");
                isValid = false;
            } else {
                if (!author.isEmpty()) {
                    queryBuilder.append("inauthor:").append(author);
                }
                if (!title.isEmpty()) {
                    if (queryBuilder.length() > 0) {
                        queryBuilder.append("+");
                    }
                    queryBuilder.append("intitle:").append(title);
                }
            }
        }

        if (!isValid) {
            Log.w(TAG, "Búsqueda cancelada por validación");
            return;
        }

        String queryString = queryBuilder.toString();

        Log.d(TAG, "Query construido: " + queryString);
        Log.d(TAG, "PrintType: " + printType);

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);

        Log.d(TAG, "Iniciando Loader");

        LoaderManager.getInstance(this)
                .restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);

        Log.d(TAG, "searchBooks FINALIZADO");
    }

    private void showFieldError(EditText field, int msgResId) {
        field.setError(getString(msgResId));
        field.setHintTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        field.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, android.R.color.holo_red_dark)));
        field.requestFocus();
    }

    private void clearFieldError(EditText field) {
        field.setError(null);
        field.setHintTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        field.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, android.R.color.darker_gray)));
    }

    private class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

        public static final String EXTRA_QUERY = "query";
        public static final String EXTRA_PRINT_TYPE = "printType";
        private final String TAG = BookLoaderCallbacks.class.getSimpleName();

        @NonNull
        @Override
        public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
            Log.d(TAG, "onCreateLoader LLAMADO");

            String queryString = "";
            String printType = "all";

            if (args != null) {
                queryString = args.getString(EXTRA_QUERY, "");
                printType = args.getString(EXTRA_PRINT_TYPE, "all");
                Log.d(TAG, "onCreateLoader - Query: " + queryString);
                Log.d(TAG, "onCreateLoader - PrintType: " + printType);
            } else {
                Log.w(TAG, "onCreateLoader - Bundle es NULL");
            }

            BookLoader loader = new BookLoader(MainActivity.this, queryString, printType);
            Log.d(TAG, "BookLoader creado");
            return loader;
        }
        @Override
        public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
            Log.d(TAG, "onLoadFinished LLAMADO");
            Log.d(TAG, "Datos recibidos: " + (data != null ? data.size() + " libros" : "NULL"));

            if (data == null || data.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        R.string.no_results,
                        Toast.LENGTH_SHORT).show();
                Log.w(TAG, "No hay resultados para mostrar");
                return;
            }

            ArrayList<BookInfo> booksList = new ArrayList<>(data);

            Log.d(TAG, "Preparando Intent para ResultsActivity");

            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putParcelableArrayListExtra("books", booksList);

            Log.d(TAG, "Libros en Intent: " + booksList.size());
            Log.d(TAG, "Iniciando ResultsActivity");

            startActivity(intent);

            titleEditText.setText("");
            authorEditText.setText("");

            LoaderManager.getInstance(MainActivity.this).destroyLoader(BOOK_LOADER_ID);

            Log.d(TAG, "onLoadFinished FINALIZADO");
        }
        @Override
        public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
            Log.d(TAG, "onLoaderReset llamado");
            // Limpiar referencias si es necesario
        }
    }
}