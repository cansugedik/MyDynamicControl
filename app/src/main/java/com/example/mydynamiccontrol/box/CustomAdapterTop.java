package com.example.mydynamiccontrol.box;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydynamiccontrol.R;

import java.util.List;

/**
 * Created by CANSU on 2.08.2018.
 */

public class CustomAdapterTop extends RecyclerView.Adapter<CustomAdapterTop.ViewHolder> {

    private Context mContext;
    private static List<MyModel> mList;

    public CustomAdapterTop(Context mContext, List<MyModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    private static MyModel getItem(int position) {

        MyModel selectedProduct = mList.get(position);

        return mList.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterTop.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView lblKutu;
        private Button btnKutu;
        private RelativeLayout rowRelative;
        private LinearLayout kutuArkaplan;

        LinearLayout.LayoutParams params;
        private Context mContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            mContext = context;

            rowRelative = itemView.findViewById(R.id.rowRelative);
            kutuArkaplan= itemView.findViewById(R.id.kutuArkaplan);
            lblKutu = itemView.findViewById(R.id.lblKutu);
            btnKutu = itemView.findViewById(R.id.btnKutu);
        }

        public void bind(MyModel item) {

            lblKutu.setText(item.getName());

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);

            kutuArkaplan.setBackgroundResource(R.color.colorGray);
            btnKutu.setVisibility(View.GONE);
            rowRelative.setLayoutParams(params);

            kutuArkaplan.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == kutuArkaplan.getId()) {

                MyModel item = getItem(getAdapterPosition());

                Toast.makeText(mContext, String.valueOf(item.getId())+". sütun", Toast.LENGTH_SHORT).show();

            }

        }


    }
}
