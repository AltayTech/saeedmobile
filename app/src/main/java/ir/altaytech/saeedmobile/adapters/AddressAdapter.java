package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.CustomersAddress;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */
public class AddressAdapter {

    public interface ClickListener {

        void onLongClicked(int position);

        void onItemlicked(int position);


    }

    public static class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.CityViewHolder> {

        private final ClickListener listener;
        private Context mCtx;
        private List<CustomersAddress> cityList;

        public AddressesAdapter(Context mCtx, List<CustomersAddress> cityList, ClickListener listener) {
            this.listener = listener;
            this.mCtx = mCtx;
            this.cityList = cityList;
        }

        @NonNull
        @Override
        public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.item_address_list, parent, false);

            return new CityViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
            CustomersAddress customersAddress = cityList.get(position);

            holder.textViewCiryName.setText(customersAddress.getTitle());


        }

        @Override
        public int getItemCount() {
            return cityList.size();
        }

        class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView textViewCiryName;


            Typeface myTypefacebyekan = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/byekan2.ttf");
            Typeface myTypefacebyekan_bold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/byekan_bold.ttf");
            private WeakReference<ClickListener> listenerRef;

            CityViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                textViewCiryName = itemView.findViewById(R.id.cityName);

                textViewCiryName.setTypeface(myTypefacebyekan_bold);


                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

            }

            @Override
            public void onClick(View v) {
                listenerRef.get().onItemlicked(getAdapterPosition());

            }

            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("توجه")
                        .setMessage("برای مشاهده اطلاعات سالن روی آن کلیک کنید")
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.create().show();
                listenerRef.get().onLongClicked(getAdapterPosition());


                return false;
            }
        }


    }

}