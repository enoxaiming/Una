/*
package pabix.chickens.una.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import pabix.chickens.una.Activity.NavigationDrawerActivity;
import pabix.chickens.una.Database.ProjectVO;
import pabix.chickens.una.HTTPConnection.Repo;
import pabix.chickens.una.HTTPConnection.getProjects;
import pabix.chickens.una.Management.URLManager;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

*/
/**
 * Created by JunHyeok on 2016. 12. 7..
 *//*


public class ProjectRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<ProjectVO,ProjectRecyclerViewAdapter.ViewHolder> {

    private AlertDialog alertDialog;
    private Realm mRealm = Realm.getDefaultInstance();

    public ProjectRecyclerViewAdapter(Context context, RealmResults<ProjectVO> realmResults, boolean automaticUpdate,
                                      boolean animateIdType, String animateExtraColumnName) {
        super(context, realmResults, automaticUpdate, animateIdType, animateExtraColumnName);
    }

    public class ViewHolder extends RealmViewHolder {
        public TextView usrName,proName,participant,contents,hash1,hash2,hash3;
        public Button submit,contact,store;
        public ImageView usrPhoto,works;
        public ViewHolder(View view) {
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

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.project_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(ProjectRecyclerViewAdapter.ViewHolder holder, int position) {

        final ProjectRecyclerViewAdapter.ViewHolder holder1 = holder;

        String userName = realmResults.get(position).getLauncher();
        String proName = realmResults.get(position).getProjectName();
        int participants = realmResults.get(position).getApplicants();
        String contents = realmResults.get(position).getContents();
        String userPhoto = realmResults.get(position).getId();
        String wants = realmResults.get(position).getWants();

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


    private void asyncRefreshAllQuotes() {
        AsyncTask<Void, Void, Void> remoteItem = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                deleteuserData();
                // Add some delay to the refresh/remove action.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                getProject();
                mRealm.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                realmRecyclerView.setRefreshing(false);
            }
        };
        remoteItem.execute();
    }

    private void asyncLoadMoreProjects() {
        AsyncTask<Void, Void, Void> remoteItem = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Add some delay to the refresh/remove action.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                mRealm.beginTransaction();

                //TODO 사이즈 받아와서 더이상 로드 안되게?
                */
/*
                for (int i = 0; i < 60; i++) {
                    QuoteModel quoteModel = mRealm.createObject(QuoteModel.class, i + 100); // That is to offset for primary key
                    quoteModel.setQuote(quotes.get((int) (quoteModel.getId() % quotes.size())));
                }
                *//*

                mRealm.commitTransaction();
                mRealm.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                realmRecyclerView.disableShowLoadMore();
            }
        };
        remoteItem.execute();
    }

    private void getProject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final getProjects getProjects = retrofit.create(getProjects.class);

        Call<List<Repo>> call = getProjects.getProject();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.e("cout",String.valueOf(response.body().size()));
                insertData(response.body().size(),response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void deleteuserData(){

        mRealm.beginTransaction();

        RealmResults<ProjectVO> projectList = mRealm.where(ProjectVO.class).findAll();
        projectList.deleteAllFromRealm();

        mRealm.commitTransaction();
    }


    private void insertData(int size,List<Repo> list) {
        mRealm.beginTransaction();
        ProjectVO projects = new ProjectVO();
        for(int count = 0 ; count < size; count++) {
            projects.setProject_idx(list.get(count).getProject_idx());
            projects.setApplicants(list.get(count).getApplicants());
            projects.setAvaliable(list.get(count).getIsAvaliable());
            projects.setContents(list.get(count).getContents());
            projects.setId(list.get(count).getId());
            projects.setWants(list.get(count).getWants());
            projects.setView_count(list.get(count).getView_count());
            projects.setLaunchDate(list.get(count).getLaunchDate());
            projects.setFinishDate(list.get(count).getFinishDate());
            projects.setSubscriber(list.get(count).getSubscriber());
            projects.setLike_count(list.get(count).getLike_count());
            projects.setProjectName(list.get(count).getProjectName());
            projects.setLauncher(list.get(count).getLauncher());
            mRealm.copyToRealmOrUpdate(projects);
        }
        mRealm.commitTransaction();
    }

}
*/
