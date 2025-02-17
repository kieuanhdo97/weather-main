package com.aniketjain.weatherapp.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aniketjain.weatherapp.R;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {

    List<Item> itemList;
    Context context;

    Activity activity;

    public ItemRecyclerAdapter(List<Item> itemList, Context context, Activity activity) {
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.titleName.setText(item.getTitle());

        holder.parentLayout.setOnClickListener(v -> {

            holder.parentLayout.setBackgroundColor(Color.GRAY);
            new Thread(() -> {
                SystemClock.sleep(100);
                holder.parentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.mainBGColor));
            }).start();
            Intent intent = new Intent();
            intent.putExtra("result_key", item.getTitle());
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            titleName = itemView.findViewById(R.id.itemTitle);
        }

    }
}