package com.example.riyagarg.finalminesweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.riyagarg.finalminesweeper.R;
import com.example.riyagarg.finalminesweeper.MinesweeperModel;


public class Cell extends View implements View.OnClickListener , View.OnLongClickListener{

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


}
