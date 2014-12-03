package ua.cn.defence1lab.tictactoe;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;



public class GameActivity extends Activity {

    private Game game;
    private Button[][] buttons = new Button[3][3];
    private TextView textViewCntGames = null;
    private TableLayout layout;
	private Random r;

    public GameActivity() {
        game = new Game();
        game.start();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameactivity);
        layout = (TableLayout) findViewById(R.id.main_l);
        buildGameField();
        textViewCntGames = (TextView) findViewById(R.id.textView1);
        resetFldButtons();
    }
    
    public void onClickButton(View view) {
        Button button = (Button) view;
        Game g = game;
        int x=0,y=0;
        Player player = g.getCurrentActivePlayer();
        switch (button.getId()) {
		case R.id.button1:
			x = 0;	y = 0;
			break;
		case R.id.button2:
			x = 0;	y = 1;
			break;	
		case R.id.button3:
			x = 0;	y = 2;
			break;
		case R.id.button4:
			x = 1;	y = 0;
			break;
		case R.id.button5:
			x = 1;	y = 1;
			break;	
		case R.id.button6:
			x = 1;	y = 2;
			break;
		case R.id.button7:
			x = 2;	y = 0;
			break;
		case R.id.button8:
			x = 2;	y = 1;
			break;	
		case R.id.button9:
			x = 2;	y = 2;
			break;
		}
        if (game.makeTurn(x, y)) {
        	if (player.getName() == "X") {
        		button.setBackgroundResource(R.drawable.x);
			}else {
				button.setBackgroundResource(R.drawable.o);
			}
        }
        Player winner = g.checkWinner();
        if (winner != null) {
            gameOver(winner);
        }
        if (g.isFieldFilled()) {  
            gameOver();
        }
        //AI turn
        if (game.gameMode == 1 && game.filled != 0 && game.getCurrentActivePlayer().getName() != "X") {
        	r = new Random();
        	int x1 = -1, y1 = -1;
        	x=-1;
        	y=-1;
        	game.resetFld();
        	game.checkDanger(game.fldp, game.fldc);
        	for (int i = 0, len = game.fldc.length; i < len; i++) {
                for (int j = 0, len2 = game.fldc[i].length; j < len2; j++) {
                    Player curPl = game.fldc[i][j].getPlayer();
                    if (curPl != null) {
                    	if (curPl.getName() == "O") {
    						x = i;
    						y = j;
    						//Toast.makeText(this, "OOOO  x="+x+"  y="+y, Toast.LENGTH_SHORT).show();
    					}
					}
                }
            }
        		for (int i = 0, len = game.fldp.length; i < len; i++) {
                    for (int j = 0, len2 = game.fldp[i].length; j < len2; j++) {
                        Player curPl = game.fldp[i][j].getPlayer();
                        if (curPl != null) {
                        	if (curPl.getName() == "X") {
        						x1 = i;
        						y1 = j;
        					}
    					}
                    }
                }
        	if ((x == -1 && y == -1) && (x1 == -1 && y1 == -1)) {
            	if (!game.field[1][1].isFilled()) {
            		x=1;
            		y=1;
                }else {
                	x=r.nextInt(3);
                	y=r.nextInt(3);
				}
			}else if (x == -1 && y == -1) {
				x=x1;
				y=y1;
			}
        	while (!game.makeTurn(x, y)) {
        		x=r.nextInt(3);
            	y=r.nextInt(3);
			}
        	Button b=null;
        	if (x==0 && y==0) {
				b = (Button) findViewById(R.id.button1);
			}else	if (x==0 && y==1) {
				b = (Button) findViewById(R.id.button2);
			}else	if (x==0 && y==2) {
				b = (Button) findViewById(R.id.button3);
			}else	if (x==1 && y==0) {
				b = (Button) findViewById(R.id.button4);
			}else	if (x==1 && y==1) {
				b = (Button) findViewById(R.id.button5);
			}else	if (x==1 && y==2) {
				b = (Button) findViewById(R.id.button6);
			}else	if (x==2 && y==0) {
				b = (Button) findViewById(R.id.button7);
			}else	if (x==2 && y==1) {
				b = (Button) findViewById(R.id.button8);
			}else	if (x==2 && y==2) {
				b = (Button) findViewById(R.id.button9);
			}
        	if (player.getName() == "X") {
        		b.setBackgroundResource(R.drawable.o);
			}else {
				b.setBackgroundResource(R.drawable.x);
			}
            winner = g.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
            if (g.isFieldFilled()) {  
                gameOver();
            }
		}//End AI turn
    }
    
    public void selectTPM(View v){
    	if (game.gameMode == 1) {
    		game.gameMode = 0;
    		game.reset();
    		resetFldButtons();
    		game.cntGames = 0;
        	game.cntXwin = 0;
        	game.cntOwin = 0;
        	textViewCntGames.setText(game.cntGames + "/3");
		}
    }
    
    public void selectVSAM(View v){
    	if (game.gameMode == 0) {
    		game.gameMode = 1;
    		game.reset();
    		resetFldButtons();
    		game.cntGames = 0;
        	game.cntXwin = 0;
        	game.cntOwin = 0;
        	textViewCntGames.setText(game.cntGames + "/3");
		}
    }
    
    private void buildGameField() {
        Mass[][] field = game.getField();
        buttons[0][0] = (Button) findViewById(R.id.button1);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);
        
    }

    private void gameOver(Player player) {
        CharSequence text = "Победил '" + player.getName() + "'";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
        checkLabelWins(player);
    }

    private void gameOver() {
        CharSequence text = "Ничья";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
        Player player = new Player("N");
        checkLabelWins(player);
    }
    /*
     * Mетод проверяющий количество сыграных игр и отображает его в лейбле
     */
    private void checkLabelWins(Player player){
    	if (player.getName() == "N") {
    		game.cntGames++;
			textViewCntGames.setText(game.cntGames + "/3");
		}
    	if (player.getName() == "X") {
			game.cntXwin++;
			game.cntGames++;
			textViewCntGames.setText(game.cntGames + "/3");
		}
        if (player.getName() == "O") {
        	game.cntOwin++;
        	game.cntGames++;
        	textViewCntGames.setText(game.cntGames + "/3");
		}
        if (game.cntGames == 3) {
        	if (game.cntXwin > game.cntOwin) {
        		Toast t = Toast.makeText(this, "Игрок 'Х' победил в " + game.cntXwin+ "/3 матчах", 
        				Toast.LENGTH_SHORT);
        				t.setGravity(Gravity.CENTER, 0, 0);
        				t.show();
			}else if (game.cntOwin > game.cntXwin) {
				Toast t = Toast.makeText(this, "Игрок 'O' победил в " + game.cntOwin+ "/3 матчах", 
        				Toast.LENGTH_SHORT);
						t.setGravity(Gravity.CENTER, 0, 0);
						t.show();
			}else {
				Toast t = Toast.makeText(this, "Объявлена ничья!", 
						Toast.LENGTH_SHORT);
						t.setGravity(Gravity.CENTER, 0, 0);
						t.show();
			}
        	game.cntGames = 0;
        	game.cntXwin = 0;
        	game.cntOwin = 0;
        	textViewCntGames.setText(game.cntGames + "/3");
		}
    }
    /*
     * Перерисовка игрового поля
     */
    private void refresh() {
        Mass[][] field = game.getField();
        for (int i = 0, len = field.length; i < len; i++) {
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                if (field[i][j].getPlayer() == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
        resetFldButtons();
    }
    
    private void resetFldButtons(){
    	Button button= (Button) findViewById(R.id.button1);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button2);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button3);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button4);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button5);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button6);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button7);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button8);
    	button.setBackgroundResource(R.drawable.n);
    	button= (Button) findViewById(R.id.button9);
    	button.setBackgroundResource(R.drawable.n);
    }
}
