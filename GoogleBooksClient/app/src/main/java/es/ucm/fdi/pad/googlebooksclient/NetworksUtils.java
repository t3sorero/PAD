package es.ucm.fdi.pad.googlebooksclient;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworksUtils {

    private static final String TAG = NetworksUtils.class.getSimpleName();

    private static final String BOOKS_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";
    private static final String API_KEY_PARAM = "key";


    public static URL buildURL(String queryString, String printType) {
        Uri builtUri = Uri.parse(BOOKS_BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, queryString).appendQueryParameter(MAX_RESULTS, "20").appendQueryParameter(PRINT_TYPE, printType).appendQueryParameter(API_KEY_PARAM, BuildConfig.GOOGLE_BOOKS_API_KEY).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.i(TAG, "URL: " + url.toString().replace(BuildConfig.GOOGLE_BOOKS_API_KEY, "***KEY***"));
        } catch (Exception e) {
            Log.e(TAG, "Error creando URL", e);

        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);

            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();

            Log.i(TAG, "Codigo de respuesta" + responseCode);

            if(responseCode != HttpURLConnection.HTTP_OK){
                Log.e(TAG, "Error en la peticion: " + responseCode);
                return null;

            }
            InputStream inputStream = httpURLConnection.getInputStream();
            return readStream(inputStream);
        }finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }
    }

    private static String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        //Si la respuesta esta vacia, devuelve null???
        if (response.length() == 0) {
            return null;
        }

        reader.close();
        return response.toString();
    }
}
