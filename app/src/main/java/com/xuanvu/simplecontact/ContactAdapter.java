package com.xuanvu.simplecontact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mContacts;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnClickItemListener onClickListener;


    public ContactAdapter(Context mContext, int mInflater, List<Contact> mContacts, OnClickItemListener onClickListener) {
        this.mContacts = mContacts;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.mInflater = LayoutInflater.from( mContext );
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate( R.layout.item_contact, viewGroup, false );
        return new ContactViewHolder( itemView, onClickListener );

    }


    public interface OnClickItemListener {
        void onItemRecyclerClicked(int postion, int actions);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int i) {
        holder.textView.setText( mContacts.get( i ).getmFullname() );
    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        OnClickItemListener onClickItemListener;


        public ContactViewHolder(@NonNull View itemView, final OnClickItemListener onClickNodeListener) {
            super( itemView );
            textView = itemView.findViewById( R.id.tv_fullname );

            this.onClickItemListener = onClickItemListener;

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickNodeListener.onItemRecyclerClicked( getAdapterPosition(), 1 );
                }
            } );


        }
    }
}