package ir.altaytech.saeedmobile.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.Group;
import ir.altaytech.saeedmobile.R;

public class ProductAdapter_category {

    public interface ClickListener {


        void onLongClicked(int position);


        void onItemlicked(int position);


    }

    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private final ClickListener listener;
        int row_index = 0;
        private Context mCtx;
        private List<Group> groupList;


        public ProductsAdapter(Context mCtx, List<Group> groupList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.groupList = groupList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.productlist_category, null);

            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, final int position) {
            Group group = groupList.get(position);

            //loading the image
//            Glide.with(mCtx)
//                    .load(group.getGroupPicPath())
//                    .into(holder.categoryIcon);

            holder.categoryTextView.setText(group.getTitle());

//
//            if (row_index == position) {
//                holder.row_linearlayout.setBackgroundColor(Color.parseColor("#FF6F00"));
//                holder.categoryTextView.setBackgroundColor(Color.parseColor("#FF6F00"));
//                holder.categoryTextView.setTextColor(Color.parseColor("#ffffff"));
//            } else {نئ
//                holder.row_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
//                holder.categoryTextView.setTextColor(Color.parseColor("#000000"));
//                holder.categoryTextView.setBackgroundColor(Color.parseColor("#ffffff"));
//
//            }

        }

        @Override
        public int getItemCount() {
            return groupList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView categoryTextView;
//            ImageView categoryIcon;
            ConstraintLayout row_linearlayout;

            private WeakReference<ClickListener> listenerRef;

            public ProductViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                categoryTextView = itemView.findViewById(R.id.categoryTextView);
//                categoryIcon = itemView.findViewById(R.id.categoryIcon);
                row_linearlayout = itemView.findViewById(R.id.mainCatLayout);


                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

//                likeButton.setOnClickListener(this);
//                categoryIcon.setOnClickListener(this);
//                shoppingCartButton.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
//                if (v.getId() == likeButton.getId()) {
//
//                    likeButton.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
////                    HomeFragment.getItemPosition();
////                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(HomeFragment.getItemPosition()), Toast.LENGTH_SHORT).show();
//
//                    listenerRef.get().onFavButtonClicked(getAdapterPosition());
//                } else if (v.getId() == shoppingCartButton.getId()) {
//
//                    int id= productListcategory.get(getAdapterPosition()).getId();
//                    boolean isExist = DatabaseManager.productIsExist(id, mCtx);
//                    if (isExist){
//                        shoppingCartButton.setBackgroundResource(R.drawable.ic_add_shopping_cart_red_24dp);
//
//                    }else {
//                        shoppingCartButton.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
//
//
//                    }
//
//
//                    listenerRef.get().onShoppingCartClicked(getAdapterPosition());
//
//                } else {
////                    Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                    listenerRef.get().onItemlicked(getAdapterPosition());
////                    Toast.makeText(v.getContext(), "item PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//
//                }
                listenerRef.get().onItemlicked(getAdapterPosition());
                row_index = getAdapterPosition();
                notifyDataSetChanged();

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