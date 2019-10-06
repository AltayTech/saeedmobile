package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.AmazingProduct;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class AmazingProductAdapter {
    private Context mCtx;
    private List<AmazingProduct> amazingProductList;

    public interface ClickListener {

        void onFavButtonClicked(int position);

        void onLongClicked(int position);

        void onShoppingCartClicked(int position);

        void onItemlicked(int position);

    }

    public static class AmazingProductsAdapter extends RecyclerView.Adapter<AmazingProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        private Context mCtx;
        private List<AmazingProduct> amazingProductList;


        public AmazingProductsAdapter(Context mCtx, List<AmazingProduct> amazingProductList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.amazingProductList = amazingProductList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.item_amazingproduct_list, parent, false);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            AmazingProduct amazingProduct = amazingProductList.get(position);

            //loading the image
            Glide.with(mCtx)
                    .load(amazingProduct.getPicPath())
                    .into(holder.imageView);
            String price = amazingProduct.getPrice() + " " + "تومان";
            String finalPrice = amazingProduct.getFinalPrice() + " " + "تومان";

            holder.textViewTitle.setText(amazingProduct.getShortName());
            holder.textViewPrice.setText(price);
            holder.textViewPrice.setPaintFlags(holder.textViewPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewFinalPrice.setText(finalPrice);

        }

        @Override
        public int getItemCount() {
            return amazingProductList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView textViewTitle, textViewPrice, textViewFinalPrice;
            ImageView imageView;

            Typeface myTypefacefar_traffic = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");
            Typeface myTypefacefar_trafficBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/b_traffic_bold.ttf");

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                textViewTitle = itemView.findViewById(R.id.textViewTitle);

                textViewPrice = itemView.findViewById(R.id.textViewPrice);
                textViewFinalPrice = itemView.findViewById(R.id.textViewFinalPrice);
                imageView = itemView.findViewById(R.id.imageView);
                textViewTitle.setTypeface(myTypefacefar_traffic);
                textViewPrice.setTypeface(myTypefacefar_trafficBold);
                textViewFinalPrice.setTypeface(myTypefacefar_trafficBold);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

                imageView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                listenerRef.get().onItemlicked(getAdapterPosition());


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