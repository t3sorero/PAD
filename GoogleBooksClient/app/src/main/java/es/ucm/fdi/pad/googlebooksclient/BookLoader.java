package es.ucm.fdi.pad.googlebooksclient;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    private static final String TAG = BookLoader.class.getSimpleName();

    private String queryString;
    private String printType;

    public BookLoader(@NonNull Context context, String queryString, String printType) {
        super(context);
        this.queryString = queryString;
        this.printType = printType;
    }

    @Nullable
    @Override
    public List<BookInfo> loadInBackground() {
        Log.i(TAG, "loadInBackground - Query: " + queryString + ", PrintType: " + printType);

        if (queryString == null || queryString.trim().isEmpty()) {
            Log.w(TAG, "Query string is empty");
            return Collections.emptyList();
        }

        try {
            String bookInfoJson = getBookInfoJson(queryString, printType);
            if (bookInfoJson == null) {
                Log.e(TAG, "JSON response es null");
                return Collections.emptyList();
            }

            Log.i(TAG, "JSON response: " + bookInfoJson);

            List<BookInfo> bookInfoList = BookInfo.fromJsonResponse(bookInfoJson);

            Log.i(TAG, "Lista de libros: " + bookInfoList.toString());


            return bookInfoList;

        } catch (Exception e) {
            Log.e(TAG, "Error cargando libros", e);
            return Collections.emptyList();
        }
    }

    public void onStartLoading() {
        Log.i(TAG, "onStartLoading iniciado");
        forceLoad();
    }

    public String getBookInfoJson(String queryString, String printType) {

        try {
            URL bookInfoUrl = NetworksUtils.buildURL(queryString, printType);
            if (bookInfoUrl == null) {
                Log.e(TAG, "URL invalida");
                return null;
            }

            return NetworksUtils.getResponseFromHttpUrl(bookInfoUrl);

        } catch (IOException e) {
            Log.e(TAG, "Error cargando libros", e);
            return null;
        }
    }

}
