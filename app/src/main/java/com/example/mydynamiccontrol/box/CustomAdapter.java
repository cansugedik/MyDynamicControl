package com.example.mydynamiccontrol.box;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydynamiccontrol.R;

import java.util.List;

/**
 * Created by CANSU on 31.07.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context mContextCreate;
    private List<MyModel> mList;

    public CustomAdapter(Context mContext, List<MyModel> mList) {
        this.mContextCreate = mContext;
        this.mList = mList;
    }

    private MyModel getItem(int position) {

        return mList.get(position);
    }

    //Bu method adaptör oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view, mContextCreate);
    }

    //onCreateViewHolder’dan dönen verileri bağlama işlemini gerçekleştirildiği metotdur.
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    //Listemizin eleman sayısını döndüren metottur.
    @Override
    public int getItemCount() {
        return mList.size();
    }

    //Her bir satırımızın içinde bulunan bileşenleri tanımlama işleminin yapıldığı metottur.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textView;
        private Button btn;
        private RelativeLayout rowRelative;
        private LinearLayout slotarkaplan;
        private LinearLayout.LayoutParams params;
        private Context mContextCreate;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            mContextCreate = context;

            rowRelative = itemView.findViewById(R.id.rowRelative);
            slotarkaplan = itemView.findViewById(R.id.kutuArkaplan);
            textView = itemView.findViewById(R.id.lblKutu);
            btn = itemView.findViewById(R.id.btnKutu);

            btn.setOnClickListener(this);
            rowRelative.setOnClickListener(this);
            rowRelative.setOnLongClickListener(this);
        }

        public void bind(MyModel item) {

            textView.setText(item.getName());

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
            rowRelative.setLayoutParams(params);
        }

        private boolean btnDurum = true;
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            MyModel item = getItem(getAdapterPosition());

            if (v.getId() == btn.getId()) {

                    slotarkaplan.setBackgroundResource(R.color.colorBlue1);
                    textView.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);

            }

            if (v.getId() == rowRelative.getId()) {

                    textView.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    textView.setText(item.getName());
                    rowRelative.setVisibility(View.VISIBLE);
                slotarkaplan.setBackgroundResource(R.color.colorPrimary);

            }
        }

        @Override
        public boolean onLongClick(View v) {

            if (v.getId() == rowRelative.getId()) {
                MyModel item = getItem(getAdapterPosition());

                //kütüphanesini kullanarak bu sayfadaki verileri tek satırla diğer sayfaya göndermiş olduk
                //EventBus.getDefault().postSticky(new EventBusItemEvent(item));

                mContextCreate.startActivity(new Intent(mContextCreate, DetailActivity.class));
            }

            return false;
        }
    }
}
