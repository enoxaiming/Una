package pabix.chickens.una.Adapter;


import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import pabix.chickens.una.Item;
import pabix.chickens.una.Activity.NavigationDrawerActivity;
import pabix.chickens.una.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHyeok on 2016. 10. 17..
 */

public class ProjectRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private ArrayList<Item> itemList;

    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;
    private AlertDialog alertDialog;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 15;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private com.getbase.floatingactionbutton.FloatingActionsMenu fab;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public ProjectRecyclerAdapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener=onLoadMoreListener;
        itemList =new ArrayList<>();
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager){
        this.mLinearLayoutManager=linearLayoutManager;
    }

    public void setRecyclerView(final RecyclerView mView){
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (!isMoreLoading && (totalItemCount - visibleItemCount)<= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        mView.onScrollStateChanged(RecyclerView.SCROLL_STATE_IDLE);
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list, parent, false);
            return new StudentViewHolder(view);
        } else {
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false));
        }

    }

    //TODO get List
    public void addAll(List<Item> lst){
        itemList.clear();
        itemList.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(List<Item> lst){
        itemList.addAll(lst);
        notifyItemRangeChanged(0,itemList.size());
    }


    //TODO setText All about Project
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {
            Item singleItem = (Item) itemList.get(position);
            //((StudentViewHolder) holder).tvItem.setText(singleItem.getItem());
            ((StudentViewHolder) holder).store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog = new AlertDialog.Builder(NavigationDrawerActivity.contexts).create();
                    View view = View.inflate(NavigationDrawerActivity.contexts,R.layout.dialog_apply,null);
                    alertDialog.setView(view);
                    view.findViewById(R.id.applybtn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    view.findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });



        }
    }


    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading=isMoreLoading;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setProgressMore(final boolean isProgress) {
        if (isProgress) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    itemList.add(null);
                    notifyItemInserted(itemList.size() - 1);
                }
            });
        } else {
            itemList.remove(itemList.size() - 1);
            notifyItemRemoved(itemList.size());
        }
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView usrName,proName,participant,contents,hash1,hash2,hash3;
        public Button submit,contact,store;
        public ImageView usrPhoto,works;

        public StudentViewHolder(View v) {
            super(v);
            usrName = (TextView) v.findViewById(R.id.usrname);
            proName = (TextView) v.findViewById(R.id.proname);
            participant = (TextView) v.findViewById(R.id.participant);
            contents = (TextView) v.findViewById(R.id.contents);
            hash1 = (TextView) v.findViewById(R.id.hash1);
            hash2 = (TextView) v.findViewById(R.id.hash2);
            hash3 = (TextView) v.findViewById(R.id.hash3);
            submit = (Button) v.findViewById(R.id.submit);
            contact = (Button) v.findViewById(R.id.contact);
            store = (Button) v.findViewById(R.id.store);

            usrPhoto = (ImageView) v.findViewById(R.id.usrPhoto);
            works = (ImageView) v.findViewById(R.id.works);

        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pBar;
        public ProgressViewHolder(View v) {
            super(v);
            pBar = (ProgressBar) v.findViewById(R.id.pBar);
        }
    }

}
