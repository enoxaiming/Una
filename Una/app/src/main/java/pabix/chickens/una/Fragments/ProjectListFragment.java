package pabix.chickens.una.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.util.List;
import java.util.logging.Handler;

import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import pabix.chickens.una.Activity.NavigationDrawerActivity;
import pabix.chickens.una.Activity.ProjectAddActivity;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    private Realm mRealm = Realm.getDefaultInstance();

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;

    private RealmRecyclerView realmRecyclerView;
    private RealmConfiguration realmConfiguration;
    private ProjectRecyclerViewAdapter projectRecyclerViewAdapter;
    private String type;

    public ProjectListFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProjectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectListFragment newInstance() {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(view);
        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.multiple_actions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationDrawerActivity.contexts, ProjectAddActivity.class));
            }
        });



        RealmResults<ProjectVO> projectVOs = mRealm.where(ProjectVO.class).findAll();
        Log.e("project",String.valueOf(projectVOs.size()));
        projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(getContext(),projectVOs,false,false,null);
        realmRecyclerView.setAdapter(projectRecyclerViewAdapter);

        realmRecyclerView.setOnRefreshListener(
                new RealmRecyclerView.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        android.os.Handler handler = new android.os.Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                asyncRefreshAllQuotes();
                                realmRecyclerView.setRefreshing(false);
                            }
                        },1000);

                    }
                }
        );

        return view;
    }

    private void asyncRefreshAllQuotes() {
        deleteuserData();
        AsyncTask<Void, Void, Void> remoteItem = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Add some delay to the refresh/remove action.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                getProject();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class ProjectRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<ProjectVO,RealmViewHolder> {

        private AlertDialog alertDialog;
        private Realm mRealm = Realm.getDefaultInstance();

        public ProjectRecyclerViewAdapter(Context context, RealmResults<ProjectVO> realmResults, boolean automaticUpdate,
                                          boolean animateIdType, String animateExtraColumnName) {
            super(context, realmResults, automaticUpdate, animateIdType, animateExtraColumnName);
        }

        public class NormalViewHolder extends RealmViewHolder {

            public TextView usrName,proName,participant,contents;
            public Button submit,contact,store;
            public ImageView usrPhoto,works;
            public LinearLayout hash;

            public NormalViewHolder(View view) {
                super(view);
                usrName = (TextView) view.findViewById(R.id.usrname);
                proName = (TextView) view.findViewById(R.id.proname);
                participant = (TextView) view.findViewById(R.id.participant);
                contents = (TextView) view.findViewById(R.id.contents);
                submit = (Button) view.findViewById(R.id.submit);
                contact = (Button) view.findViewById(R.id.contact);
                store = (Button) view.findViewById(R.id.store);

                usrPhoto = (ImageView) view.findViewById(R.id.usrPhoto);
                works = (ImageView) view.findViewById(R.id.works);

                hash = (LinearLayout)view.findViewById(R.id.hashlinear);
            }
        }

        public class AnnounceViewHolder extends RealmViewHolder {
            public TextView textView;
            public MultiStateToggleButton mstb;
            public AnnounceViewHolder (View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.announce);
                mstb = (MultiStateToggleButton)view.findViewById(R.id.mstb_multi_id);
            }
        }

        @Override
        public RealmViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {

            if (viewType == ITEM_TYPE_NORMAL) {
                View v = inflater.inflate(R.layout.project_list, viewGroup, false);
                return new NormalViewHolder(v);
            }
            else if (viewType == ITEM_TYPE_HEADER) {
                View headerRow = inflater.inflate(R.layout.fragment_announcement,viewGroup,false);
                return new AnnounceViewHolder(headerRow); // view holder for header items
            }
            else
                return null;
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0 ) {
                return ITEM_TYPE_HEADER;
            }
            else
                return ITEM_TYPE_NORMAL;
        }

        @Override
        public int getItemCount() {

            return realmResults.size()+1;
        }
        @Override
        public void onBindRealmViewHolder(RealmViewHolder holder, int position) {

            int itemType = getItemViewType(position);

            if (itemType == ITEM_TYPE_NORMAL) {

                int max = realmResults.size();

                position = max  - position;

                String userName = realmResults.get(position).getLauncher();
                Log.e("realm",realmResults.get(position).getLauncher());
                String proName = realmResults.get(position).getProjectName();
                int participants = realmResults.get(position).getApplicants();
                String contents = realmResults.get(position).getContents();
                final String userPhoto = realmResults.get(position).getId();
                String wants = realmResults.get(position).getWants();

                String URL = "https://graph.facebook.com/" + userPhoto +"/picture?type=large";

                NormalViewHolder holder1 = (NormalViewHolder)holder;
                holder1.usrName.setText(userName);
                holder1.proName.setText(proName);
                holder1.participant.setText(String.valueOf(participants));
                holder1.contents.setText(contents);
                TextView textView = new TextView(UnaApplication.getContext());
                textView.setText("#"+wants);
                textView.setTextColor(getResources().getColor(R.color.texts));
                textView.setBackgroundResource(R.color.back);
                //textView.setPaddingRelative(20,10,20,10);
                holder1.hash.addView(textView);
                Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(holder1.usrPhoto);

                //((StudentViewHolder) holder).tvItem.setText(singleItem.getItem());
                holder1.submit.setOnClickListener(new View.OnClickListener() {
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
                holder1.contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url ="https://www.facebook.com/messages/" + userPhoto ;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            } else if (itemType == ITEM_TYPE_HEADER) {
                ((AnnounceViewHolder)holder).textView.setText("공지");
                ((AnnounceViewHolder)holder).mstb.setElements(R.array.planets_array,0);
            }


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
                /*
                for (int i = 0; i < 60; i++) {
                    QuoteModel quoteModel = mRealm.createObject(QuoteModel.class, i + 100); // That is to offset for primary key
                    quoteModel.setQuote(quotes.get((int) (quoteModel.getId() % quotes.size())));
                }
                */
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
        mRealm.close();
    }









}
