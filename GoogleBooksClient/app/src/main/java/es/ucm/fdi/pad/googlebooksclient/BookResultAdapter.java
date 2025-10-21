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

    private static String TAG = BookResultAdapter.class.getSimpleName();

    private ArrayList<BookInfo> bookInfosList;

    private Context context;
    private ViewHolder viewHolder;

    public BookResultAdapter(Context context) {
        this.context=context;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent, false);
        Log.i(TAG, "onCreateViewHolder llamado");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BookInfo book = bookInfosList.get(position);

        holder.titleTextView.setText(book.getTitle());
        if(book.getAuthors()!= null && !book.getAuthors().isEmpty()){
            holder.authorTextView.setText(book.getAuthors());
            holder.authorTextView.setVisibility(View.VISIBLE);
        }else{
            holder.authorTextView.setVisibility(View.GONE);
            Log.w(TAG, "No se encontraron autores para el libro " + book.getTitle());
        }
        if(book.getInfoLink() != null){
            holder.linkTextView.setText(book.getInfoLink().toString());
            holder.linkTextView.setVisibility(View.VISIBLE);
        }else{
            holder.linkTextView.setVisibility(View.GONE);
            Log.w(TAG, "No se encontro enlace para el libro " + book.getTitle());
        }

        holder.itemView.setOnClickListener(View -> {
            if(book.getInfoLink() != null){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getInfoLink().toString()));

                if(intent.resolveActivity(context.getPackageManager()) != null){
                    context.startActivity(intent);

                }else {
                    Toast.makeText(context, "No se puede abrir el navegador", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "No se puede abrir el navegador");
                }
            }else {
                Toast.makeText(context, "No hay enlace disponible", Toast.LENGTH_SHORT).show();
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
