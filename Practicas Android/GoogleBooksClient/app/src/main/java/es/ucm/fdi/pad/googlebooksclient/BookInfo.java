package es.ucm.fdi.pad.googlebooksclient;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookInfo implements Parcelable {

    private static final String TAG = BookInfo.class.getSimpleName();
    private final String title;
    private final String authors;
    private URL infoLink;

    public BookInfo(String title, String authors, URL infoLink) {
        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
    }

    //Mismo orden en lectura y escritura!!
    protected BookInfo(Parcel in) {
        title = in.readString();
        authors = in.readString();
        String urlString = in.readString();
        try {
            infoLink = urlString != null && !urlString.isEmpty() ? new URL(urlString) : null;
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error al deserializar URL " + urlString, e);
            infoLink=null;
        }
    }

    public static final Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
        @Override
        public BookInfo createFromParcel(Parcel in) {
            return new BookInfo(in);
        }

        @Override
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //Mismo orden en lectura y escritura!!
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(authors);
        dest.writeString(infoLink != null ? infoLink.toString() : null);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public URL getInfoLink() {
        return infoLink;
    }

    public static List<BookInfo> fromJsonResponse(String jsonResponse) {

        List<BookInfo> bookInfoList = new ArrayList<>();

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            Log.e(TAG, "JSON response es null o esta vacio");
            return bookInfoList;
        }

        try {

            JSONObject response = new JSONObject(jsonResponse);

            if (!response.has("items")) {
                Log.i(TAG, "No se encontraron items en la respuesta");
                return bookInfoList;
            }
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                try {

                    JSONObject book = items.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                    String title = volumeInfo.optString("title", "Sin título");

                    String authors = "";

                    if (volumeInfo.has("authors")) {
                        JSONArray authorArray = volumeInfo.getJSONArray("authors");
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < authorArray.length(); j++) {
                            if (j > 0) {
                                stringBuilder.append(", ");
                            }
                            stringBuilder.append(authorArray.getString(j));
                        }
                        authors = stringBuilder.toString();
                    } else {
                        authors = "Sin autores";
                    }

                    URL infoLink = null;
                    if (volumeInfo.has("infoLink")) {
                        try {
                            infoLink = new URL(volumeInfo.getString("infoLink"));
                        } catch (MalformedURLException e) {
                            Log.e(TAG, "URL invalida: " + title, e);
                        }
                    }

                    BookInfo bookInfo = new BookInfo(title, authors, infoLink);
                    bookInfoList.add(bookInfo);
                } catch (JSONException e) {
                    Log.e(TAG, "Error procesando el libro " + i, e);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON", e);
        }
        return bookInfoList;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", infoLink=" + infoLink +
                '}';
    }

}
