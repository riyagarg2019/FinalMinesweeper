package com.example.riyagarg.finalminesweeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.riyagarg.finalminesweeper.MinesweeperModel;

/**
 * Created by riyagarg on 3/16/18.
 */

public class MinesweeperView extends View {

    private int value;
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
    }*/

}