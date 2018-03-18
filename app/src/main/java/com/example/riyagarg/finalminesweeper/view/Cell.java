package com.example.riyagarg.finalminesweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.riyagarg.finalminesweeper.R;
import com.example.riyagarg.finalminesweeper.MinesweeperModel;

import static java.security.AccessController.getContext;


public class Cell {
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x , y;
    private int position;
    private int value;

    private MinesweeperView view;

    /*public MinesweeperView(Context context){
        super(context);

        MinesweeperModel.getInstance().createGrid(context);
        //GridAdapter gridadap = new GridAdapter();
    }*/


    public Cell(Context context , int x , int y ){
        //super(context);

        this.x = x;
        this.y = y;

        //MinesweeperModel.getInstance().createGrid(context);
        //this.view = new MinesweeperView(context);


    }

    /*public Cell(Context context){
        //super(context);

        //this.x = x;
        //this.y = y;
        //setPosition(x,y);
        //MinesweeperModel.getInstance().createGrid(context);
        //this.view = new MinesweeperView(context);


    }*/

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
        //invalidate();
        view.Refresh();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;
        view.Refresh();
        //invalidate();
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

        //invalidate();
        view.Refresh();
    }
    public void refresh(){
        view.Refresh();
    }

    public void restart(){
        MinesweeperModel.instance = null;
    }
}
/*public class Cell extends View implements View.OnClickListener , View.OnLongClickListener{

    MinesweeperView view = new MinesweeperView(getContext());

    public Cell(Context context , int x , int y ){
        super(context);

        view.setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        MinesweeperModel.getInstance().click( view.getXPos(), view.getYPos() );
    }

    @Override
    public boolean onLongClick(View v) {
        MinesweeperModel.getInstance().flag( view.getXPos() , view.getYPos() );

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Minesweeper" , "Cell::onDraw");
        drawButton(canvas);

        if( view.isFlagged() ){
            drawFlag(canvas);
        }else if( view.isRevealed() && view.isBomb() && !view.isClicked() ){
            drawNormalBomb(canvas);
        }else {
            if( view.isClicked() ){
                if( view.getValue() == -1 ){
                    drawBombExploded(canvas);
                }else {
                    drawNumber(canvas);
                }
            }else{
                drawButton(canvas);
            }
        }
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

        switch (view.getValue() ){
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


}*/
