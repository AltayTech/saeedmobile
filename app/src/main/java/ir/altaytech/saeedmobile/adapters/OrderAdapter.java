package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.Orders;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class OrderAdapter {
    List<Orders> productListItemNum;
    private Context mCtx;
    private List<Orders> productList;

    public static String[] dateAdopter(String inDate) {

        String date = inDate.substring(0, inDate.indexOf("-"));
        Log.e("date", date);

        String time = inDate.substring(inDate.indexOf("-"));
        Log.e("time", time);

        return new String[]{date, time};
    }

    public interface ClickListener {

        void onDecreaseButtonClicked(int position);

        void onLongClicked(int position);

        void onIncreaseCartClicked(int position);

        void onItemclicked(int position);

        void onRemoveItemClicked(int position);


    }

    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        private final String status;
        private Context mCtx;
        private List<Orders> orderList;
        private Orders product;
        private double itemPrice;


        public ProductsAdapter(Context mCtx, String status, List<Orders> productList, ClickListener listener) {

            this.listener = listener;
            this.status = status;
            this.mCtx = mCtx;
            this.orderList = productList;

        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.order_list, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            product = orderList.get(position);

            String[] dater = dateAdopter(product.getRegDateTime());


            holder.orderspirce.setText(String.valueOf(product.getTotal()));
            holder.orderDate.setText(dater[0]);
            holder.orderHour.setText(dater[1]);
            holder.orderStatus.setText(status);


        }

        @Override
        public int getItemCount() {
            return orderList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


            TextView orderStatus, orderspirce, orderDate, orderHour;
            TextView orderStatusText, orderspirceText, orderDateText, orderHourText;

            Typeface myTypefacefar_traffic = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                orderStatus = itemView.findViewById(R.id.orderStatus);
                orderspirce = itemView.findViewById(R.id.orderspirce);
                orderDate = itemView.findViewById(R.id.orderDate);
                orderHour = itemView.findViewById(R.id.orderHour);
                orderStatus.setTypeface(myTypefacefar_traffic);
                orderspirce.setTypeface(myTypefacefar_traffic);
                orderDate.setTypeface(myTypefacefar_traffic);
                orderHour.setTypeface(myTypefacefar_traffic);


                orderStatusText = itemView.findViewById(R.id.orderStatusText);
                orderspirceText = itemView.findViewById(R.id.orderspirceText);
                orderDateText = itemView.findViewById(R.id.orderDateText);
                orderHourText = itemView.findViewById(R.id.orderHourText);

                orderStatusText.setTypeface(myTypefacefar_traffic);
                orderspirceText.setTypeface(myTypefacefar_traffic);
                orderDateText.setTypeface(myTypefacefar_traffic);
                orderHourText.setTypeface(myTypefacefar_traffic);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);


            }

            @Override
            public void onClick(View v) {
                listenerRef.get().onItemclicked(getAdapterPosition());

            }

            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Hello Dialog")
                        .setMessage("LONG CLICK DIALOG WINDOW FOR ICON " + String.valueOf(getAdapterPosition()))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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