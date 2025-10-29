package es.ucm.fdi.pad.googlebooksclient;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int BOOK_LOADER_ID = 1;

    private EditText titleEditText;
    private EditText authorEditText;
    private RadioGroup typeRadioGroup;
    private FrameLayout loadingOverlay;
    private TextView resultMessageTextView;

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

        Log.d(TAG, "onCreate llamado");

        titleEditText = findViewById(R.id.editTextText);
        authorEditText = findViewById(R.id.editTextText2);
        typeRadioGroup = findViewById(R.id.radioGroup4);
        loadingOverlay = findViewById(R.id.loadingOverlay);
        resultMessageTextView = findViewById(R.id.resultMessageTextView);

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
            Log.i(TAG, "Loader ya existe");
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }

        Log.d(TAG, "onCreate finalizado");
    }

    public void searchBooks(View view) {
        Log.d(TAG, "searchBooks llamado");

        clearFieldError(titleEditText);
        clearFieldError(authorEditText);
        hideResultMessage();

        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();

        Log.i(TAG, "Título: " + title);
        Log.i(TAG, "Autor: " + author);

        int selectedId = typeRadioGroup.getCheckedRadioButtonId();
        String printType = "all";
        boolean isValid = true;

        StringBuilder queryBuilder = new StringBuilder();

        if (selectedId == R.id.radioButton2) {
            printType = "books";
            Log.i(TAG, "Tipo seleccionado: LIBROS");

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
            Log.i(TAG, "Tipo seleccionado: REVISTAS");

            if (title.isEmpty()) {
                showFieldError(titleEditText, R.string.error_title_required);
                Log.w(TAG, "Validación falló: título vacío para revistas");
                isValid = false;
            } else {
                queryBuilder.append(title);
            }

        } else if (selectedId == R.id.radioButton5) {
            printType = "all";
            Log.i(TAG, "Tipo seleccionado: TODO");

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

        Log.i(TAG, "Query construido: " + queryString);
        Log.i(TAG, "PrintType: " + printType);

        showLoadingOverlay();
        Log.i(TAG, "Mostrando overlay de carga");

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);

        Log.d(TAG, "Iniciando Loader");

        LoaderManager.getInstance(this)
                .restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);

        Log.d(TAG, "searchBooks finalizado");
    }

    private void showLoadingOverlay() {
        loadingOverlay.setVisibility(View.VISIBLE);
        loadingOverlay.setAlpha(0f);
        loadingOverlay.animate()
                .alpha(1f)
                .setDuration(200)
                .start();
    }

    private void hideLoadingOverlay() {
        loadingOverlay.animate()
                .alpha(0f)
                .setDuration(200)
                .withEndAction(() -> loadingOverlay.setVisibility(View.GONE))
                .start();
    }

    private void showResultMessage(String message) {
        resultMessageTextView.setText(message);
        resultMessageTextView.setVisibility(View.VISIBLE);
        resultMessageTextView.setAlpha(0f);
        resultMessageTextView.animate()
                .alpha(1f)
                .setDuration(300)
                .start();
    }

    private void hideResultMessage() {
        if (resultMessageTextView.getVisibility() == View.VISIBLE) {
            resultMessageTextView.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction(() -> resultMessageTextView.setVisibility(View.GONE))
                    .start();
        }
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
        field.setHintTextColor(ContextCompat.getColor(this, R.color.gray_400));
        field.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.blue_500)));
    }

    private class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

        public static final String EXTRA_QUERY = "query";
        public static final String EXTRA_PRINT_TYPE = "printType";
        private final String TAG = BookLoaderCallbacks.class.getSimpleName();

        @NonNull
        @Override
        public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
            Log.d(TAG, "onCreateLoader llamado");

            String queryString = "";
            String printType = "all";

            if (args != null) {
                queryString = args.getString(EXTRA_QUERY, "");
                printType = args.getString(EXTRA_PRINT_TYPE, "all");
                Log.i(TAG, "Query: " + queryString);
                Log.i(TAG, "PrintType: " + printType);
            } else {
                Log.w(TAG, "Bundle es NULL");
            }

            BookLoader loader = new BookLoader(MainActivity.this, queryString, printType);
            Log.d(TAG, "BookLoader creado");
            return loader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
            Log.d(TAG, "onLoadFinished llamado");
            Log.i(TAG, "Datos recibidos: " + (data != null ? data.size() + " libros" : "NULL"));



            hideLoadingOverlay();

            Log.i(TAG, "Ocultando overlay de carga");

            if (data == null || data.isEmpty()) {
                showResultMessage(getString(R.string.no_results));
                Log.w(TAG, "No hay resultados para mostrar");
                return;
            }

            ArrayList<BookInfo> booksList = new ArrayList<>(data);

            Log.i(TAG, "Preparando Intent para ResultsActivity");

            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putParcelableArrayListExtra("books", booksList);

            Log.i(TAG, "Libros en Intent: " + booksList.size());
            Log.i(TAG, "Iniciando ResultsActivity");

            startActivity(intent);

            titleEditText.setText("");
            authorEditText.setText("");

            LoaderManager.getInstance(MainActivity.this).destroyLoader(BOOK_LOADER_ID);

            Log.d(TAG, "onLoadFinished finalizado");
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
            Log.d(TAG, "onLoaderReset llamado");
        }
    }
}