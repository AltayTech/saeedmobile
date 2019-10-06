package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
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

import ir.altaytech.saeedmobile.Model.ShoppingCardProduct;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class ProductAdapterShoppingCart {


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
        private List<ShoppingCardProduct> productList;
        private ShoppingCardProduct product;
        private double itemPrice;


        public ProductsAdapter(Context mCtx, List<ShoppingCardProduct> productList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.product_list_shopping_cart, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            product = productList.get(position);


            //loading the image
            Glide.with(mCtx)
                    .load(product.getProductPicPath())
                    .into(holder.shoppingCartProductImage);
            NumberFormat nf = new DecimalFormat("#.####");

            holder.shoppingCartProductPrice.setText(String.valueOf(nf.format(product.getPrice() * product.getQuantity())));
            holder.textViewNumber.setText(String.valueOf(product.getQuantity()));
            holder.shoppingCartProductName.setText(product.getProductName());
//            holder.shoppingCartProductUnitPrice.setText(String.valueOf(product.getPrice()));


        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            private final Button removeItemButton;
            ImageView shoppingCartProductImage;

            TextView shoppingCartProductPrice, textViewNumber, shoppingCartProductName;

//                    , shoppingCartProductUnitPrice;

            Button reduceUnitButton;
            Button increaseUnitButton;
            Typeface myTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                shoppingCartProductImage = itemView.findViewById(R.id.shoppingCartProductImage);
                shoppingCartProductPrice = itemView.findViewById(R.id.shoppingCartProductPrice);
                textViewNumber = itemView.findViewById(R.id.textViewNumber);
                shoppingCartProductName = itemView.findViewById(R.id.shoppingCartProductName);
//                shoppingCartProductUnitPrice = itemView.findViewById(R.id.shoppingCartProductUnitPrice);

                reduceUnitButton = itemView.findViewById(R.id.reduceUnitButton);
                increaseUnitButton = itemView.findViewById(R.id.increaseUnitButton);
                removeItemButton = itemView.findViewById(R.id.removeButton);

//                shoppingCartProductUnitPrice.setTypeface(myTypeface);
                shoppingCartProductName.setTypeface(myTypeface);
                textViewNumber.setTypeface(myTypeface);
                shoppingCartProductPrice.setTypeface(myTypeface);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

                reduceUnitButton.setOnClickListener(this);
                increaseUnitButton.setOnClickListener(this);
                removeItemButton.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

//                DatabaseManager.openDatabase(mCtx);
                if (v.getId() == reduceUnitButton.getId()) {
//
////                    reduceUnitButton.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//                    HomeFragment.getItemPosition();
////                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(HomeFragment.getItemPosition()), Toast.LENGTH_SHORT).show();
//
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getProductId();
//                    int itemNumNewNum;
//                    List<Product2> productListItemNum = DatabaseManager.readRowShoppingCart(productId, mCtx);
//
//                    if (productListItemNum.get(0).getNum() > 0) {
//                        itemNumNewNum = productListItemNum.get(0).getNum() - 1;
//
//                    } else {
//                        itemNumNewNum = 0;
//                    }
//                    DatabaseManager.updateRowNumberShoppingCart(itemNumNewNum, productId);
//                    textViewNumber.setText(String.valueOf(itemNumNewNum));
//
//                    double itemUnitPrice = productListItemNum.get(0).getPrice();
//
//                    itemPrice = itemNumNewNum * itemUnitPrice;
//                    shoppingCartProductPrice.setText(String.valueOf(itemPrice));
//
                    listenerRef.get().onDecreaseButtonClicked(getAdapterPosition());
//
                } else if (v.getId() == increaseUnitButton.getId()) {
//
////                    increaseUnitButton.setBackgroundResource(R.drawable.ic_add_shopping_cart_red_24dp);
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getProductId();
//
//                    List<Product2> productListItemNum = DatabaseManager.readRowShoppingCart(productId, mCtx);
//                    int itemNumNewNum = productListItemNum.get(0).getNum() + 1;
//                    DatabaseManager.updateRowNumberShoppingCart(itemNumNewNum, productId);
//                    textViewNumber.setText(String.valueOf(itemNumNewNum));
//
//                    double itemUnitPrice = productListItemNum.get(0).getPrice();
//
//                    itemPrice = itemNumNewNum * itemUnitPrice;
//                    shoppingCartProductPrice.setText(String.valueOf(itemPrice));
//
                    listenerRef.get().onIncreaseCartClicked(getAdapterPosition());
//
                } else if (v.getId() == removeItemButton.getId()) {
//
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getProductId();
//                    DatabaseManager.deleteRowShoppingCart(productId);
//                    productList.remove(itemPosition);
//                    notifyItemRemoved(itemPosition);
//
                    listenerRef.get().onRemoveItemClicked(getAdapterPosition());
//
                } else {
//                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    listenerRef.get().onItemclicked(getAdapterPosition());
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