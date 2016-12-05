package pabix.chickens.una.Adapter;


import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pabix.chickens.una.Item;
import pabix.chickens.una.Activity.NavigationDrawerActivity;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHyeok on 2016. 10. 17..
 */

public class ProjectRecyclerAdapter extends RecyclerView.Adapter<ProjectRecyclerAdapter.MyViewHolder>{
    /*private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private ArrayList<Item> itemList;
    private ArrayList<String> userNameList;
    private ArrayList<String> proNameList;
    private ArrayList<Integer> participantsList;
    private ArrayList<String> contentsList;
    private ArrayList<String> userPhotoList;
    private ArrayList<String> worksList;

    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;
    private AlertDialog alertDialog;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 7;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private com.getbase.floatingactionbutton.FloatingActionsMenu fab;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public ProjectRecyclerAdapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener=onLoadMoreListener;
        itemList =new ArrayList<>();
        userNameList = new ArrayList<>();
        proNameList = new ArrayList<>();
        participantsList = new ArrayList<>();
        contentsList = new ArrayList<>();
        userPhotoList = new ArrayList<>();
        worksList = new ArrayList<>();
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
        return userNameList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
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
    public void addAll(List<Item> lst,List<String> list1,List<String> list2,List<Integer> list3,List<String> list4,List<String> list5,List<String> list6){
        itemList.clear();
        userNameList.clear();
        proNameList.clear();
        participantsList.clear();
        contentsList.clear();
        userPhotoList.clear();
        worksList.clear();

        itemList.addAll(lst);
        userNameList.addAll(list1);
        proNameList.addAll(list2);
        participantsList.addAll(list3);
        contentsList.addAll(list4);
        userPhotoList.addAll(list5);
        worksList.addAll(list6);
        notifyDataSetChanged();
    }

    public void addItemMore(List<Item> lst,List<String> list1,List<String> list2,List<Integer> list3,List<String> list4,List<String> list5,List<String> list6){
        itemList.addAll(lst);
        userNameList.addAll(list1);
        proNameList.addAll(list2);
        participantsList.addAll(list3);
        contentsList.addAll(list4);
        userPhotoList.addAll(list5);
        worksList.addAll(list6);
        notifyItemRangeChanged(0,itemList.size());
        notifyItemRangeChanged(0,userNameList.size());
        notifyItemRangeChanged(0,proNameList.size());
        notifyItemRangeChanged(0,participantsList.size());
        notifyItemRangeChanged(0,contentsList.size());
        notifyItemRangeChanged(0,userPhotoList.size());
        notifyItemRangeChanged(0,worksList.size());
    }


    //TODO setText All about Project
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {
            //Item singleItem = (Item) itemList.get(position);
            String userName = userNameList.get(position);
            String proName = proNameList.get(position);
            int participants = participantsList.get(position);
            String contents = contentsList.get(position);
            String userPhoto = userPhotoList.get(position);
            Log.e("se",contents);
            String wants = worksList.get(position);

            String URL = "https://graph.facebook.com/" + userPhoto +"/picture?type=large";

            ((StudentViewHolder) holder).usrName.setText(userName);
            ((StudentViewHolder) holder).proName.setText(proName);
            ((StudentViewHolder) holder).participant.setText(String.valueOf(participants));
            ((StudentViewHolder) holder).contents.setText(contents);
            Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(((StudentViewHolder) holder).usrPhoto);

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
        return userNameList.size();
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
    }*/



    private List<String> userNameList;
    private List<String> proNameList;
    private List<Integer> participantsList;
    private List<String> contentsList;
    private List<String> userPhotoList;
    private List<String> worksList;
    private AlertDialog alertDialog;


    public ProjectRecyclerAdapter(List<String> userNameList,List<String> proNameList,List<Integer> participantsList,List<String> contentsList,List<String> userPhotoList,List<String> worksList) {
        this.userNameList = userNameList;
        this.proNameList = proNameList;
        this.participantsList = participantsList;
        this.contentsList = contentsList;
        this.userPhotoList = userPhotoList;
        this.worksList = worksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_list, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ProjectRecyclerAdapter.MyViewHolder holder, int position) {

        final ProjectRecyclerAdapter.MyViewHolder holder1 = holder;

        String userName = userNameList.get(position);
        String proName = proNameList.get(position);
        int participants = participantsList.get(position);
        String contents = contentsList.get(position);
        String userPhoto = userPhotoList.get(position);
        Log.e("se",contents);
        String wants = worksList.get(position);

        String URL = "https://graph.facebook.com/" + userPhoto +"/picture?type=large";

        holder1.usrName.setText(userName);
        holder1.proName.setText(proName);
        holder1.participant.setText(String.valueOf(participants));
        holder1.contents.setText(contents);
        Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(holder1.usrPhoto);

        //((StudentViewHolder) holder).tvItem.setText(singleItem.getItem());
        holder1.store.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return userNameList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView usrName,proName,participant,contents,hash1,hash2,hash3;
        public Button submit,contact,store;
        public ImageView usrPhoto,works;

        public MyViewHolder(View view) {
            super(view);
            usrName = (TextView) view.findViewById(R.id.usrname);
            proName = (TextView) view.findViewById(R.id.proname);
            participant = (TextView) view.findViewById(R.id.participant);
            contents = (TextView) view.findViewById(R.id.contents);
            hash1 = (TextView) view.findViewById(R.id.hash1);
            hash2 = (TextView) view.findViewById(R.id.hash2);
            hash3 = (TextView) view.findViewById(R.id.hash3);
            submit = (Button) view.findViewById(R.id.submit);
            contact = (Button) view.findViewById(R.id.contact);
            store = (Button) view.findViewById(R.id.store);

            usrPhoto = (ImageView) view.findViewById(R.id.usrPhoto);
            works = (ImageView) view.findViewById(R.id.works);
        }
    }

}
