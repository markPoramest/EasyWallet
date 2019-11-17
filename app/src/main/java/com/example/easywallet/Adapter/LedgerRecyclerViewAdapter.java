package com.example.easywallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easywallet.DB.LedgerItem;
import com.example.easywallet.R;

import java.util.List;

public class LedgerRecyclerViewAdapter extends RecyclerView.Adapter<LedgerRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private int mResource;
    private List<LedgerItem> mLedgerList;

    public LedgerRecyclerViewAdapter(Context mContext, int mResource, List<LedgerItem> mPlaceList) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mLedgerList = mPlaceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LedgerItem ledgerItem = mLedgerList.get(position);

        holder.describe.setText(ledgerItem.description);
        if (ledgerItem.amout>=0)
            holder.money.setText(ledgerItem.amout+"");
        else
            holder.money.setText(ledgerItem.amout*-1+"");
        holder.img.setImageResource(ledgerItem.img);

    }

    @Override
    public int getItemCount() {
        return mLedgerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView describe;
        private TextView money;
        private ImageView img;

        private LedgerItem ledgerItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.describe= itemView.findViewById(R.id.describe_Text);
            this.money = itemView.findViewById(R.id.money);
            this.img = itemView.findViewById(R.id.ledger_type);
        }

    }
}

