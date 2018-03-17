package com.example.riyagarg.finalminesweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.riyagarg.finalminesweeper.MinesweeperModel;
import com.example.riyagarg.finalminesweeper.R;
import com.example.riyagarg.finalminesweeper.view.Cell;

/**
 * Created by riyagarg on 3/16/18.
 */

public class MinesweeperView extends View implements View.OnClickListener , View.OnLongClickListener {

    Cell cell;
    Context context;

    /*public Cell(Context context , int x , int y ){
        super(context);

        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }*/

    public MinesweeperView(Context context) {
        super(context);

        //cell.setPosition(x,y);
        this.context = context;

        MinesweeperModel.getInstance().createGrid(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        MinesweeperModel.getInstance().click(this.context.getXPos(), getYPos());
    }

    @Override
    public boolean onLongClick(View v) {
        MinesweeperModel.getInstance().flag(getXPos(), getYPos());

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Minesweeper", "Cell::onDraw");
        drawButton(canvas);

        if (isFlagged()) {
            drawFlag(canvas);
        } else if (isRevealed() && isBomb() && !isClicked()) {
            drawNormalBomb(canvas);
        } else {
            if (isClicked()) {
                if (getValue() == -1) {
                    drawBombExploded(canvas);
                } else {
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);
            }
        }
    }

    public void refresh() {
        invalidate();
    }

    private void drawBombExploded(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag( Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber( Canvas canvas ){
        Drawable drawable = null;

        switch (getValue() ){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


}


    /*private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x , y;
    private int position;

    public MinesweeperView(Context context){
        super(context);

        MinesweeperModel.getInstance().createGrid(context);
        //GridAdapter gridadap = new GridAdapter();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if( value == -1 ){
            isBomb = true;
        }

        this.value = value;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed() {
        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;

        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition( int x , int y ){
        this.x = x;
        this.y = y;

        this.position = y * MinesweeperModel.WIDTH + x;

        invalidate();
    }

    /*public MinesweeperView(Context context , AttributeSet attrs){
        super(context,attrs);

        MinesweeperModel.getInstance().createGrid(context);

        //setNumColumns(MinesweeperModel.WIDTH);
        setAdapter(new GridAdapter());
    }*/


    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 25;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return MinesweeperModel.getInstance().getCellAt(position);
        }
    }

}*/