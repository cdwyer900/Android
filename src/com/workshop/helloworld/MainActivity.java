package com.workshop.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
//	Button btn;
	Button newGame;
	
	
	ImageView tl, tm, tr, ml, mm, mr, bl, bm, br;
	TextView tv;
	ImageView iv;
	Drawable redX, greenO, white;
	boolean isX = true;
	int win1 = 0;
	boolean xTurn = true;
	ImageView[] btns;
	int marks[] = new int[9];
	int counter = 0;
	int pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
  //      Toast.makeText(this, "It works!", Toast.LENGTH_LONG).show();
        
  //      btn = (Button) findViewById(R.id.button1);
        
        newGame = (Button) findViewById (R.id.new_game_btn);
        tv = (TextView) findViewById(R.id.textView1);
        tv.setTextSize(20.0f);
       // iv = (ImageView) findViewById (R.id.imageView1);
        redX = getResources().getDrawable(R.drawable.red_x);
  		greenO = getResources().getDrawable(R.drawable.green_o);
  		white = getResources().getDrawable(R.drawable.white);

        
        tl = (ImageView) findViewById (R.id.imageView1);
        tm = (ImageView) findViewById (R.id.imageView2);
        tr = (ImageView) findViewById (R.id.imageView3);
        ml = (ImageView) findViewById (R.id.imageView4);
        mm = (ImageView) findViewById (R.id.imageView5);
        mr = (ImageView) findViewById (R.id.imageView6);
        bl = (ImageView) findViewById (R.id.imageView7);
        bm = (ImageView) findViewById (R.id.imageView8);
        br = (ImageView) findViewById (R.id.imageView9);
        
        btns = new ImageView[] {tl,tm,tr,ml,mm,mr,bl,bm,br};
       
        
	     newGame.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		 playNewGame();
    		
	    	 }
        });

		
	     for (int i = 0; i <btns.length; i++)
	     {
	    	 btns[i].setId(i);
	    	 btns[i].setOnClickListener(new OnClickListener() {
	    		 public void onClick(View v) {
	    			 setImage(v);
	    		 }
	    	 });
	     }
    }
        
    public void setImage( View v)
    {
    	ImageView ivCopy = (ImageView) v;
    	if (isX) {
    		marks[ivCopy.getId()]=4;
    		ivCopy.setImageDrawable(redX);
    	}
    	else {
    		marks[ivCopy.getId()]=1;
    		ivCopy.setImageDrawable(greenO);
    	}
    	ivCopy.setClickable(false);
    	if (testForWin(isX))
    		winGame(isX);
    	else
    	{	
    		isX = !isX;
    		if (isX)
    			tv.setText("X plays");
    		else
    			tv.setText("O plays");
    	}
    }
    
    public void playNewGame() {
    	  win1 = 0;
    	  
    	  for (int i=0; i <btns.length; i++)
          {	
          	btns[i].setImageDrawable(white);
          	btns[i].setClickable(true);
          	marks[i]=0;
          }
    	  	  
    	  tv.setText("X plays");
      }
  
    public boolean testForWin(boolean isX)
    {
    	boolean win = false;
    	int win1[] = new int[8];
    	win1[0]= marks[0]+marks[1]+marks[2];
    	win1[1]= marks[3]+marks[4]+marks[5];
    	win1[2]= marks[6]+marks[7]+marks[8];
    	win1[3]= marks[0]+marks[3]+marks[6];
    	win1[4]= marks[1]+marks[4]+marks[7];
    	win1[5]= marks[2]+marks[5]+marks[8];
    	win1[6] = marks[0]+marks[4]+marks[8];
    	win1[7] = marks[2]+marks[4]+marks[6];
    	
    	
    	for (int i = 0; i<win1.length; i++)
    	{
    		if (isX)
    		{
    			if (win1[i]==12)
    				win = true;
    				break;
    		}
    		else
    			if (win1[i] ==3)
    				win = true;
    				break;
    	}	
    	
    	return win;
    	
    }
    
      public void winGame(boolean isX) {
    	  if (isX)
    		 tv.setText("X wins!");
    	  else
    		  tv.setText("O wins!"); 
    	  
    	  for (int i=0; i <btns.length; i++)
          {	
          	btns[i].setClickable(false);
          }
      }
 	  
    
}
