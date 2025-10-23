package es.ucm.fdi.pad.googlebooksclient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookResultAdapter extends RecyclerView.Adapter<BookResultAdapter.ViewHolder> {

    private static final String TAG = BookResultAdapter.class.getSimpleName();

    private final ArrayList<BookInfo> bookInfosList;

    private final Context context;

    public BookResultAdapter(Context context) {
        this.context = context;
        this.bookInfosList = new ArrayList<>();
    }

    public void setBooksData(List<BookInfo> data) {
        if (data != null) {
            bookInfosList.clear();
            bookInfosList.addAll(data);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        Log.i(TAG, "onCreateViewHolder llamado");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BookInfo book = bookInfosList.get(position);

        holder.titleTextView.setText(book.getTitle());

        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            holder.authorTextView.setText(book.getAuthors());
            holder.authorTextView.setVisibility(View.VISIBLE);
        } else {
            holder.authorTextView.setVisibility(View.GONE);
            Log.w(TAG, "No se encontraron autores para el libro " + book.getTitle());
        }

        if (book.getInfoLink() != null) {
            String url = book.getInfoLink().toString();

            if (url.startsWith("http://")) {
                url = url.replace("http://", "https://");
            } else if (!url.startsWith("https://")) {
                url = "https://" + url;
            }

            holder.linkTextView.setText(url);
            holder.linkTextView.setVisibility(View.VISIBLE);
        } else {
            holder.linkTextView.setVisibility(View.GONE);
            Log.w(TAG, "No se encontró enlace para el libro " + book.getTitle());
        }

        holder.itemView.setOnClickListener(v -> {
            if (book.getInfoLink() != null) {
                try {
                    String url = book.getInfoLink().toString();

                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }

                    if (url.startsWith("http://")) {
                        url = url.replace("http://", "https://");
                    }

                    Log.d(TAG, "Abriendo URL: " + url);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                    // Verificar que hay una app que pueda abrir el link
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                        Log.i(TAG, "URL abierta correctamente");
                    } else {
                        Toast.makeText(context, R.string.error_loading_link, Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "No se puede abrir el navegador");
                    }
                } catch (Exception e) {
                    Toast.makeText(context, R.string.error_loading_link, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error al abrir enlace: " + e.getMessage(), e);
                }
            } else {
                Toast.makeText(context, R.string.error_link_unavailable, Toast.LENGTH_SHORT).show();
                Log.w(TAG, "No hay enlace disponible");
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookInfosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView authorTextView;
        TextView linkTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            linkTextView = itemView.findViewById(R.id.linkTextView);
        }
    }
}