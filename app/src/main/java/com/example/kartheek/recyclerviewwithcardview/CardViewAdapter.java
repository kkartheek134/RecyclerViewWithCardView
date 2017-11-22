package com.example.kartheek.recyclerviewwithcardview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kartheek on 11/21/2017.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MyViewHolder> {
    int pos;

    Context context;
    ArrayList moviename,date,rating,description;
    public CardViewAdapter(Context context, ArrayList moviename, ArrayList date, ArrayList rating, ArrayList description)
    {
        this.context=context;
        this.moviename=moviename;
        this.date=date;
        this.rating=rating;
        this.description=description;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,parent,false);
        MyViewHolder holer1= new MyViewHolder(view);
        return holer1;
    }

   // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mname.setText((CharSequence) moviename.get(position));
        holder.mdate.setText((CharSequence) date.get(position));
        holder.mrating.setText(rating.get(position).toString());
        holder.mdesc.setText((CharSequence) description.get(position));
        /*if(pos == position)
        {
            holder.cardView.setBackgroundColor(Color.GREEN);
            notifyDataSetChanged();
        }*/

        //cardView.setCardBackgroundColor(Color.GREEN);

    }

    @Override
    public int getItemCount() {
        return moviename.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname,mdate,mrating,mdesc;
        //CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mname = (TextView)itemView.findViewById(R.id.textView);
            mdate = (TextView)itemView.findViewById(R.id.textView2);
            mrating = (TextView)itemView.findViewById(R.id.textView3);
            mdesc = (TextView)itemView.findViewById(R.id.textView4);
            //cardView = (CardView)itemView.findViewById(R.id.card);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cardView.setCardBackgroundColor(Color.GREEN);
                    pos = getAdapterPosition();


                }
            });*/
        }

    }
}
