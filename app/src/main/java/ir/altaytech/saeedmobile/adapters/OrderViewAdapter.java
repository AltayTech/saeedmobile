package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.OrderDetail;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class OrderViewAdapter {


    public interface ClickListener {

        void onDecreaseButtonClicked(int position);

        void onLongClicked(int position);

        void onIncreaseCartClicked(int position);

        void onItemclicked(int position);

        void onRemoveItemClicked(int position);


    }


    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        private Context mCtx;
        private List<OrderDetail> productList;
        private OrderDetail product;
        private double itemPrice;


        public ProductsAdapter(Context mCtx, List<OrderDetail> productList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.order_items_list, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            product = productList.get(position);

            holder.shoppingCartProductPrice.setText(String.valueOf(product.getFinalPrice()));
            holder.textViewNumber.setText(String.valueOf(product.getQuantity()));
            holder.shoppingCartProductName.setText(product.getProductName());
            holder.shoppingCartProductUnitPrice.setText(String.valueOf(product.getPrice()));


        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView shoppingCartProductPrice, textViewNumber, shoppingCartProductName, shoppingCartProductUnitPrice;

            Typeface myTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                shoppingCartProductPrice = itemView.findViewById(R.id.shoppingCartProductPrice);
                textViewNumber = itemView.findViewById(R.id.textViewNumber);
                shoppingCartProductName = itemView.findViewById(R.id.shoppingCartProductName);
                shoppingCartProductUnitPrice = itemView.findViewById(R.id.shoppingCartProductUnitPrice);

                shoppingCartProductUnitPrice.setTypeface(myTypeface);
                shoppingCartProductName.setTypeface(myTypeface);
                textViewNumber.setTypeface(myTypeface);
                shoppingCartProductPrice.setTypeface(myTypeface);

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
                        .setMessage("LONG CLICK DIALOG WINDOW FOR ICON " + getAdapterPosition())
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