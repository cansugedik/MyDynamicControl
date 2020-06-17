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

public class CustomAdapterLeft extends RecyclerView.Adapter<CustomAdapterLeft.ViewHolder> {

    private Context mContextLeft;
    private List<MyModel> mList;

    public CustomAdapterLeft(Context mContext, List<MyModel> mList) {
        this.mContextLeft = mContext;
        this.mList = mList;
    }

    private MyModel getItem(int position) {

        //MyModel selectedProduct = mList.get(position);

        return mList.get(position);
    }

    @NonNull
    @Override
    public CustomAdapterLeft.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterLeft.ViewHolder holder, int position) {

        holder.bind(getItem(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private Button btnKutu;
        private RelativeLayout rowRelative;
        private LinearLayout kutuArkaplan;

        LinearLayout.LayoutParams params;

        public ViewHolder(View itemView) {
            super(itemView);

            rowRelative = itemView.findViewById(R.id.rowRelative);
            kutuArkaplan = itemView.findViewById(R.id.kutuArkaplan);
            textView = itemView.findViewById(R.id.lblKutu);

            btnKutu = itemView.findViewById(R.id.btnKutu);

            kutuArkaplan.setOnClickListener(this);
        }

        //@Override
        public void bind(MyModel item) {

            textView.setText(item.getName());

                if (item.getId() <= 0) {

                    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);

                    rowRelative.setVisibility(View.INVISIBLE);
                    rowRelative.setLayoutParams(params);
                }
                if(item.getId() > 0) {

                    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);

                    kutuArkaplan.setBackgroundResource(R.color.colorGray);
                    btnKutu.setVisibility(View.GONE);
                    rowRelative.setLayoutParams(params);
                }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == kutuArkaplan.getId()) {

                MyModel item = getItem(getAdapterPosition());

                Toast.makeText(mContextLeft, String.valueOf(item.getId())+". satır", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
