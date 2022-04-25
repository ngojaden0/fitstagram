package com.example.profile;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Extends the Adapter class to RecyclerView.Adapter
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList images;
    Context context;

    //Initializing picture information
    public Adapter(Context context, ArrayList images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        // Passing view to ViewHolder
        return new ViewHolder(view);
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        // TypeCast Object to int type
        int res = (int) images.get(position);
        holder.images.setImageResource(res);
    }

    @Override
    public int getItemCount() {
        // Returns number of items currently available in Adapter
        return images.size();
    }

    // Initializing the Views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ViewHolder(View view) {
            super(view);
            images = view.findViewById(R.id.imageView);
        }
    }
}
