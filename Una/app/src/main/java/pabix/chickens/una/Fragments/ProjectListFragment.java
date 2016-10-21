package pabix.chickens.una.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pabix.chickens.una.Item;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.ProjectRecyclerAdapter;
import pabix.chickens.una.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends Fragment implements ProjectRecyclerAdapter.OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    private List<Item> mList;
    private ProjectRecyclerAdapter mAdapter;

    @BindView(R.id.nav_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.nav_recyclerview)
    RecyclerView mRecyclerView;

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
        FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu)view.findViewById(R.id.multiple_actions);
        floatingActionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.nav_recyclerview);
        mList = new ArrayList();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.nav_swiperefresh);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(UnaApplication.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProjectRecyclerAdapter(this);
        mAdapter.setLinearLayoutManager(mLayoutManager);
        mAdapter.setRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
        return view;
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

    //아이템 새로고침
    private void refreshItems() {
        mList.clear();
        for (int i = 1; i <= 20; i++) {
            mList.add(new Item("Item " + i));
        }
        mAdapter.addAll(mList);
        mSwipeRefreshLayout.setRefreshing(false); // 새로고침 후 다시 리프레시 버튼 올라가기
    }

    public void onLoadMore() {
        mAdapter.setProgressMore(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                mAdapter.setProgressMore(false);
                int start = mAdapter.getItemCount();
                int end = start + 15;
                for (int i = start + 1; i <= end; i++) {
                    mList.add(new Item("Item " + i));
                }
                mAdapter.addItemMore(mList);
                mAdapter.setMoreLoading(false);
            }
        },2000);
    }
}
