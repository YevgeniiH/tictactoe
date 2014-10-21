package ua.cn.defence1lab.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;



public class GameActivity extends Activity {

    private Game game;
    private Button[][] buttons = new Button[3][3];
    private TextView textViewCntGames = null;
    private TableLayout layout;

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
            button.setText(player.getName());
        }
        Player winner = g.checkWinner();
        if (winner != null) {
            gameOver(winner);
        }
        if (g.isFieldFilled()) {  
            gameOver();
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
        		Toast.makeText(this, "Игрок 'Х' победил в " + game.cntXwin+ "/3 матчах", 
        				Toast.LENGTH_SHORT).show();
			}else if (game.cntOwin > game.cntXwin) {
				Toast.makeText(this, "Игрок 'O' победил в " + game.cntOwin+ "/3 матчах", 
        				Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "Объявлена ничья!", Toast.LENGTH_SHORT).show();
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
    }
}
