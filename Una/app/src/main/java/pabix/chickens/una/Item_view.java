package pabix.chickens.una;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pabix.chickens.una.Database.ProjectVO;
import pabix.chickens.una.Management.UnaApplication;

public class Item_view extends RelativeLayout {

    @BindView(R.id.emoji)
    TextView emoji;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.description)
    TextView description;

    public Item_view() {
        super(UnaApplication.getContext());
        init();
    }

    private void init() {
        inflate(UnaApplication.getContext(), R.layout.activity_item_view, this);
        ButterKnife.bind(this);
    }

    public void bind(ProjectVO projectVO) {
        emoji.setText(projectVO.getName());
        title.setText(projectVO.getHost());
        date.setText(projectVO.getStartDate());
        description.setText(projectVO.getContents());
    }
}
