/*
 * Created by Tareq Islam on 3/1/19 4:10 PM
 *
 *  Last modified 3/1/19 4:03 PM
 */

package com.mti.rxandroidsample.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mti.rxandroidsample.R;
import com.mti.rxandroidsample.model.GitHubRepo;
import android.support.annotation.NonNull;
import java.util.List;

/***
 * Created by mtita on 01,March,2019.
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    private ItemClickListener mItemClickListener; /*Interface for item click callback*/


    /* Interface for Item click callback */
    public interface ItemClickListener {

        void onClick(View view, int position, boolean isLongClick);
    }


    /*View Holder Innner Class*/
    class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        TextView textRepoName, textRepoDiscription,textLanguage,textStars;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textLanguage=itemView.findViewById(R.id.text_language);
            textRepoDiscription=itemView.findViewById(R.id.text_repo_description);
            textRepoName=itemView.findViewById(R.id.text_repo_name);
            textStars=itemView.findViewById(R.id.text_stars);

            itemView.setOnClickListener(this); /*for single click listener*/
            itemView.setOnCreateContextMenuListener(this);/*for long press listener*/
        }

        /*passing item click callback from view to interface ItemClickListener */
        @Override
        public void onClick(View v) {
            /*if listener available */
            if(mItemClickListener!=null)    mItemClickListener.onClick(v,getAdapterPosition(),false);
        }

        /* for long press */
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select the Action");
            menu.add(0,0,getAdapterPosition(),"New");
        }


        /*for using callback of item click in OnBindViewHolder()*/
        private void seItemClickListener(ItemClickListener itemClickListener) {
            mItemClickListener=itemClickListener;
        }

    }
    /*End of ViewHolder Class*/

    /*Member variables of adapter class */
    private Context mContext;
    private List<GitHubRepo> dataList;

    /*Constructor with click listener*/
    public ListItemAdapter( Context context, List<GitHubRepo> dataList,ItemClickListener itemClickListener) {
         mContext = context;
        this.dataList = dataList;
        mItemClickListener = itemClickListener;

    }

    /* for updating adapter on refresh*/
    public void updateAdapter(List<GitHubRepo> list){
        dataList=  list;
    }


    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.item_github_repo, viewGroup,false);

        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {


        holder.textStars.setText( dataList.get(position).stargazersCount + "");
        holder.textRepoName.setText( dataList.get(position).name);
        holder.textRepoDiscription.setText(dataList.get(position).description);
        holder.textLanguage.setText(dataList.get(position).language);

    }

    @Override
    public int getItemCount() {

            return dataList.size();
    }



}
