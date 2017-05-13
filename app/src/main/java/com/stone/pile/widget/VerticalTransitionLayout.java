package com.stone.pile.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.stone.pile.R;

/**
 * Created by xmuSistone on 2017/5/12.
 */
public class VerticalTransitionLayout extends BaseTransitionLayout {

    private TextView textView1, textView2;

    protected int currentPosition = -1;
    protected int nextPosition = -1;

    private float textSize = 22;
    private int textColor = Color.BLACK;
    private int verticalDistance = 50;

    public VerticalTransitionLayout(Context context) {
        this(context, null);
    }

    public VerticalTransitionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTransitionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.scene);
        textSize = a.getFloat(R.styleable.scene_textSize, textSize);
        textColor = a.getColor(R.styleable.scene_textColor, textColor);
        verticalDistance = a.getDimensionPixelSize(R.styleable.scene_verticalDistance, verticalDistance);
        a.recycle();
    }

    @Override
    public void addViewWhenFinishInflate() {
        textView1 = new TextView(getContext());
        textView1.setGravity(Gravity.CENTER_VERTICAL);
        textView1.setTextSize(textSize);
        textView1.setTextColor(textColor);
        LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(textView1, lp1);

        textView2 = new TextView(getContext());
        textView2.setGravity(Gravity.CENTER_VERTICAL);
        textView2.setTextSize(textSize);
        textView2.setTextColor(textColor);
        LayoutParams lp2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(textView2, lp2);
    }

    @Override
    public void firstInit(String text) {
        this.textView1.setText(text);
        currentPosition = 0;
    }

    @Override
    public void onAnimationEnd() {
        currentPosition = nextPosition;
        TextView tmp = textView1;
        textView1 = textView2;
        textView2 = tmp;
    }

    /**
     * rate从零到1
     */
    @Override
    public void duringAnimation(float rate) {
        textView1.setAlpha(1 - rate);
        textView2.setAlpha(rate);

        if (nextPosition > currentPosition) {
            textView1.offsetTopAndBottom((int) (0 - verticalDistance * rate - textView1.getTop()));
            textView2.offsetTopAndBottom((int) (0 + verticalDistance * (1 - rate) - textView2.getTop()));
        } else {
            textView1.offsetTopAndBottom((int) (0 + verticalDistance * rate - textView1.getTop()));
            textView2.offsetTopAndBottom((int) (0 - verticalDistance * (1 - rate) - textView2.getTop()));
        }
    }

    @Override
    public void saveNextPosition(int position, String text) {
        this.nextPosition = position;
        this.textView2.setText(text);
        this.textView2.setAlpha(0);
    }
}
