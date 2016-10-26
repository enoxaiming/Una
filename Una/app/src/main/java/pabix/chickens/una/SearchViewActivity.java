package pabix.chickens.una;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmViewHolder;
import pabix.chickens.una.Database.ProjectVO;
import pabix.chickens.una.Management.UnaApplication;

/**
 * Created by JunHyeok on 2016. 10. 26..
 */

public class SearchViewActivity extends AppCompatActivity {

    private RealmSearchView realmSearchView;
    private BlogRecyclerViewAdapter adapter;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        resetRealm();
        loadBlogData();

        realmSearchView = (RealmSearchView) findViewById(R.id.search_view);
        adapter = new BlogRecyclerViewAdapter(Realm.getDefaultInstance(), "title");
        realmSearchView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }

    private void loadBlogData() {

    }

    private RealmConfiguration getRealmConfig() {
        return new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    private void resetRealm() {
        Realm.deleteRealm(getRealmConfig());
    }

    public class BlogRecyclerViewAdapter
            extends RealmSearchAdapter<ProjectVO, BlogRecyclerViewAdapter.ViewHolder> {

        public BlogRecyclerViewAdapter(
                Realm realm,
                String filterColumnName) {
            super(UnaApplication.getContext(), realm, filterColumnName);
        }

        public class ViewHolder extends RealmSearchViewHolder {

            private Item_view item_view;

            public ViewHolder(FrameLayout container, TextView footerTextView) {
                super(container, footerTextView);
            }

            public ViewHolder(Item_view item_view) {
                super(item_view);
                this.item_view = item_view;
            }
        }

        @Override
        public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
            ViewHolder vh = new ViewHolder(new Item_view());
            return vh;
        }

        @Override
        public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
            final ProjectVO blog = realmResults.get(position);
            viewHolder.item_view.bind(blog);
        }

        @Override
        public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
            View v = inflater.inflate(R.layout.footer_view, viewGroup, false);
            return new ViewHolder(
                    (FrameLayout) v,
                    (TextView) v.findViewById(R.id.footer_text_view));
        }

        @Override
        public void onBindFooterViewHolder(ViewHolder holder, int position) {
            super.onBindFooterViewHolder(holder, position);
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }
            );
        }
    }
}
