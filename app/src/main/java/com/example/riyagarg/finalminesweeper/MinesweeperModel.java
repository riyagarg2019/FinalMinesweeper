package com.example.riyagarg.finalminesweeper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.riyagarg.finalminesweeper.view.Cell;
import com.example.riyagarg.finalminesweeper.util.Generate;
import com.example.riyagarg.finalminesweeper.view.MinesweeperView;

import java.util.Random;

import static com.example.riyagarg.finalminesweeper.util.Generate.generate;


public class MinesweeperModel {

    public static MinesweeperModel instance;

    public static final int BOMB_NUMBER = 3;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;


    private Context context;

    //private MinesweeperView view = new MinesweeperView(context);

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

    public static MinesweeperModel getInstance() {
        if( instance == null ){
            instance = new MinesweeperModel();
        }
        return instance;
    }

    private MinesweeperModel(){ }

    public void createGrid(Context context){
        //Log.e("GameEngine","createGrid is working");
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = generate(BOMB_NUMBER,WIDTH, HEIGHT);
        //PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context,GeneratedGrid);
    }

    private void setGrid( final Context context, final int[][] grid ){
        for( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( MinesweeperGrid[x][y] == null ){
                    //MinesweeperGrid[x][y] = new Cell(x,y);
                    MinesweeperGrid[x][y] = new Cell(context, x,y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].refresh();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return MinesweeperGrid[x][y];
    }

    public Cell getCellAt( int x , int y ){
        return MinesweeperGrid[x][y];
    }

    public void click( int x , int y ){
        if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x,y).isClicked() ){
            getCellAt(x,y).setClicked();

            if( getCellAt(x,y).getValue() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCellAt(x,y).isBomb() ){
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd(){
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;
        for ( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlagged() ){
                    notRevealed--;
                }

                if( getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb() ){
                    bombNotFound--;
                }
            }
        }

        if( bombNotFound == 0 && notRevealed == 0 ){
            Toast.makeText(context, R.string.WIN, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag( int x , int y ){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).refresh();
    }

    private void onGameLost(){
        // handle lost game
        Toast.makeText(context, R.string.LOSE, Toast.LENGTH_SHORT).show();

        for ( int x = 0 ; x < WIDTH ; x++ ) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt(x,y).setRevealed();
            }
        }
    }


}
