package com.example.traderapp.portfolio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traderapp.R;
import com.example.traderapp.models.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {

    private List<Item> mItemList;
    private Context mContext;

    public RecyclerViewAdaptor(Context context, List<Item> itemList){
        this.mItemList = itemList;
        this.mContext  = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout. list_row, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String symbol = mItemList.get(position).getSymbol();
        Double qtyDouble = mItemList.get(position).getQtyT2();
        Integer qty = (int) Math.round(qtyDouble);
        Double price  = mItemList.get(position).getLastPx();
        Double total  = qty * price;

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        holder.sembol.setText(symbol);
        holder.miktar.setText(Integer.toString(qty));
        holder.fiyat.setText(formatter.format(price));
        holder.tutar.setText(formatter.format(total));


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private String userId;

        TextView sembol,miktar,fiyat,tutar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sembol = itemView.findViewById(R.id.firstText);
            miktar = itemView.findViewById(R.id.secondText);
            fiyat  = itemView.findViewById(R.id.thirdText);
            tutar  = itemView.findViewById(R.id.fourthText);

        }
    }
}
