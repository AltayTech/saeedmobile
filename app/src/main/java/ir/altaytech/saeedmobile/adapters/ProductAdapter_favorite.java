package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
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
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class ProductAdapter_favorite {


    public interface ClickListener {


        void onLongClicked(int position);

        void onShoppingCartClicked(int position);

        void onItemlicked(int position);

        void onRemoveItemClicked(int position);


    }

    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        private Context mCtx;
        private List<Product> productList;


        public ProductsAdapter(Context mCtx, List<Product> productList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.product_list_favorite, parent, false);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            Product product = productList.get(position);

            //loading the image
            Glide.with(mCtx)
                    .load(product.getPicPath())
                    .into(holder.imageView);
            NumberFormat nf = new DecimalFormat("#.####");

            holder.textViewTitle.setText(product.getShortName());

            holder.textViewPrice.setText(String.valueOf(nf.format(product.getPrice())));

        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            private final Button removeItemButton;

            TextView textViewTitle, textViewPrice;
            ImageView imageView;

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                textViewTitle = itemView.findViewById(R.id.textViewTitle);

                textViewPrice = itemView.findViewById(R.id.textViewPrice);
                imageView = itemView.findViewById(R.id.imageView);
                removeItemButton = itemView.findViewById(R.id.removeButton);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

                imageView.setOnClickListener(this);
                removeItemButton.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                DatabaseManager.openDatabase(mCtx);

                if (v.getId() == removeItemButton.getId()) {

                    int itemPosition = getAdapterPosition();
                    int productId = productList.get(itemPosition).getId();
                    DatabaseManager.deleteRowFavorite(productId);
                    productList.remove(itemPosition);
                    notifyItemRemoved(itemPosition);

                } else {
                    listenerRef.get().onItemlicked(getAdapterPosition());

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