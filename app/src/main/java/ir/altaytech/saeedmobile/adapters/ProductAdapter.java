package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class ProductAdapter {
    private Context mCtx;
    private List<Product2> productList;

    public interface ClickListener {

        void onFavButtonClicked(int position);

        void onLongClicked(int position);

        void onShoppingCartClicked(int position);

        void onItemlicked(int position);

    }

    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        Intent productViewIntent;
        private Context mCtx;
        private List<Product2> productList;


        public ProductsAdapter(Context mCtx, List<Product2> productList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.product_list, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            Product2 product = productList.get(position);

            //loading the image
            Glide.with(mCtx)
                    .load(product.getImage())
                    .into(holder.imageView);
            NumberFormat nf = new DecimalFormat("#.####");

            holder.textViewTitle.setText(product.getTitle());
//            holder.textViewShortDesc.setText(product.getShortdesc());
//            holder.textViewRating.setText(String.valueOf(product.getRating()));
            holder.textViewPrice.setText(String.valueOf(nf.format(product.getPrice())));

        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
            ImageView imageView;
            Button likeButton;
            Button shoppingCartButton;

            //            Typeface myTypeface2 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/irsans.ttf");
            Typeface myTypefacefar_traffic = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");
            Typeface myTypefacefar_trafficBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/b_traffic_bold.ttf");

            //            Typeface myTypefacebyekan = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/byekan2.ttf");
//            Typeface myTypefacebyekan_bold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/byekan_bold.ttf");
            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                textViewTitle = itemView.findViewById(R.id.textViewTitle);
//                textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
//                textViewRating = itemView.findViewById(R.id.textViewRating);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
                imageView = itemView.findViewById(R.id.imageView);
                likeButton = itemView.findViewById(R.id.likeButton);
                shoppingCartButton = itemView.findViewById(R.id.shoppingCartButton);
                textViewTitle.setTypeface(myTypefacefar_traffic);
                textViewPrice.setTypeface(myTypefacefar_trafficBold);


                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

                likeButton.setOnClickListener(this);
                imageView.setOnClickListener(this);
                shoppingCartButton.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                if (v.getId() == likeButton.getId()) {

                    likeButton.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//                    HomeFragment.getItemPosition();
//                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(HomeFragment.getItemPosition()), Toast.LENGTH_SHORT).show();

                    listenerRef.get().onFavButtonClicked(getAdapterPosition());

                } else {
//                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    listenerRef.get().onItemlicked(getAdapterPosition());
//                    Toast.makeText(v.getContext(), "item PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

                }


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