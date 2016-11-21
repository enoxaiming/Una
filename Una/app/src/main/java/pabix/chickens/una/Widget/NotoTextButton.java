package pabix.chickens.una.Widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by JunHyeok on 2016. 11. 21..
 */

public class NotoTextButton extends Button {
    public NotoTextButton(Context context) {
        super(context);
        setType(context);
    }

    public NotoTextButton(Context context, AttributeSet attrs){
        super(context, attrs);
        setType(context);
    }

    public NotoTextButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NotoTextButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"NotoSansKR-Regular-Hestia.otf"));
    }
}
