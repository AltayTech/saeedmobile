package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
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

import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.Property;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class ProductAdapterSearch {
    List<Property> productListItemNum;
    private Context mCtx;
    private List<Product> productList;

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
        private List<Product> productList;
        private Product product;
        private double itemPrice;


        public ProductsAdapter(Context mCtx, List<Product> productList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.itme_search_product_list, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            product = productList.get(position);


            //loading the image
            Glide.with(mCtx)
                    .load(product.getPicPath())
                    .into(holder.shoppingCartProductImage);

//            holder.shoppingCartProductPrice.setText(String.valueOf(product.getPrice()));
//            holder.textViewNumber.setText(String.valueOf(product.getNum()));
            holder.shoppingCartProductName.setText(product.getShortName());
//            holder.shoppingCartProductUnitPrice.setText(String.valueOf(product.getPrice()));


        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

//            private final Button removeItemButton;
            ImageView shoppingCartProductImage;
            Typeface myTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/far_traffic.ttf");

            TextView shoppingCartProductPrice
//                    , textViewNumber
                    , shoppingCartProductName
//                    , shoppingCartProductUnitPrice
                    ;
//            Button reduceUnitButton;
//            Button increaseUnitButton;

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                shoppingCartProductImage = itemView.findViewById(R.id.imageViewSearchItem);
//                shoppingCartProductPrice = itemView.findViewById(R.id.shoppingCartProductPrice);
//                textViewNumber = itemView.findViewById(R.id.textViewNumber);
                shoppingCartProductName = itemView.findViewById(R.id.textViewTitle);
//                shoppingCartProductUnitPrice = itemView.findViewById(R.id.shoppingCartProductUnitPrice);
//
//                reduceUnitButton = itemView.findViewById(R.id.reduceUnitButton);
//                increaseUnitButton = itemView.findViewById(R.id.increaseUnitButton);
//                removeItemButton = itemView.findViewById(R.id.removeButton);
                shoppingCartProductName.setTypeface(myTypeface);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

//                reduceUnitButton.setOnClickListener(this);
//                increaseUnitButton.setOnClickListener(this);
//                removeItemButton.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                DatabaseManager.openDatabase(mCtx);
//                if (v.getId() == reduceUnitButton.getId()) {
//
////                    reduceUnitButton.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//                    HomeFragment.getItemPosition();
////                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(HomeFragment.getItemPosition()), Toast.LENGTH_SHORT).show();
//
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getId();
//                    int itemNumNewNum;
//                    List<Product2> productListItemNum = DatabaseManager.readRow(productId, mCtx);
//
//                    if (productListItemNum.get(0).getNum()>0){
//                         itemNumNewNum = productListItemNum.get(0).getNum() - 1;
//
//                    }else{
//                         itemNumNewNum=0;
//                    }
//                    DatabaseManager.updateRowNumber(itemNumNewNum, productId);
//                    textViewNumber.setText(String.valueOf(itemNumNewNum));
//
//                    double itemUnitPrice = productListItemNum.get(0).getPrice();
//
//                    itemPrice = itemNumNewNum * itemUnitPrice;
//                    shoppingCartProductPrice.setText(String.valueOf(itemPrice));
//
//                    listenerRef.get().onDecreaseButtonClicked(getAdapterPosition());
//
//                } else if (v.getId() == increaseUnitButton.getId()) {
//
////                    increaseUnitButton.setBackgroundResource(R.drawable.ic_add_shopping_cart_red_24dp);
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getId();
//
//                    List<Product2> productListItemNum = DatabaseManager.readRow(productId, mCtx);
//                    int itemNumNewNum = productListItemNum.get(0).getNum() + 1;
//                    DatabaseManager.updateRowNumber(itemNumNewNum, productId);
//                    textViewNumber.setText(String.valueOf(itemNumNewNum));
//
//                    double itemUnitPrice = productListItemNum.get(0).getPrice();
//
//                    itemPrice = itemNumNewNum * itemUnitPrice;
//                    shoppingCartProductPrice.setText(String.valueOf(itemPrice));
//
//                    listenerRef.get().onIncreaseCartClicked(getAdapterPosition());
//
//                }else if (v.getId() == removeItemButton.getId()) {
//
//                    int itemPosition = getAdapterPosition();
//                    int productId = productList.get(itemPosition).getId();
//                    DatabaseManager.deleteRow(productId);
//                    productList.remove(itemPosition);
//                    notifyItemRemoved(itemPosition);
//
//                    listenerRef.get().onRemoveItemClicked(getAdapterPosition());
//
//                } else {
//                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    listenerRef.get().onItemclicked(getAdapterPosition());
//                    Toast.makeText(v.getContext(), "item PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

//                }


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