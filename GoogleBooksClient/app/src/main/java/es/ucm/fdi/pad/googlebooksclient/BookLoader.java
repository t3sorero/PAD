package es.ucm.fdi.pad.googlebooksclient;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.Collections;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {
    public BookLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<BookInfo> loadInBackground() {
        return Collections.emptyList();
    }

    public void onStartLoading(){
        forceLoad();
    }

    public String getBookInfoJson(String queryString, String printType){

        //peticion servivio usando HttpUrlConection
        return null;
    }

}
