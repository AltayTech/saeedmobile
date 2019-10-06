package ir.altaytech.saeedmobile.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ir.altaytech.saeedmobile.Model.Transaction;
import ir.altaytech.saeedmobile.Model.other.WalletTransaction;
import ir.altaytech.saeedmobile.R;

/**
 * Created by Altay on 2/7/2018.
 */

public class TransactionAdapter {
    private Context mCtx;

    public interface ClickListener {


        void onLongClicked(int position);


        void onItemlicked(int position);

    }

    public static class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.MemberViewHolder> {

        private final ClickListener listener;
        private Context mCtx;
        private List<Transaction> transactionList;


        public TransactionsAdapter(Context mCtx, List<Transaction> transactionList, ClickListener listener) {
            this.listener = listener;

            this.mCtx = mCtx;
            this.transactionList = transactionList;
        }

        @Override
        public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.members_list, null);

            return new MemberViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MemberViewHolder holder, final int position) {
            Transaction transaction = transactionList.get(position);

//            //loading the image
//            Glide.with(mCtx)
//                    .load(product.getImage())
//                    .into(holder.imageView);

            holder.textViewName.setText(transaction.getBalance());
            holder.textViewUsername.setText(String.valueOf(transaction.getBalance()));

        }

        @Override
        public int getItemCount() {
            return transactionList.size();
        }


        class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView textViewName, textViewUsername;

            private WeakReference<ClickListener> listenerRef;

            public MemberViewHolder(View itemView) {
                super(itemView);

                listenerRef = new WeakReference<>(listener);

                textViewName = itemView.findViewById(R.id.textViewName);
                textViewUsername = itemView.findViewById(R.id.textViewUserName);

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