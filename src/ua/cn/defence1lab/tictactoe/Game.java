package ua.cn.defence1lab.tictactoe;

public class Game implements ICheckWinner {
    private Player[] players;
    public Mass[][] field;
    public Mass[][] fldp;
    public Mass[][] fldc;
    private boolean started;
    public Player activePlayer;
    public int filled;
    private int MassCount;
    public int cntGames;
    public int cntXwin;
    public int cntOwin;
    public int gameMode;//0-two players; 1-one player
    private ICheckWinner[] winnerCheckers;

    public Game() {
        field = new Mass[3][3];
        fldp = new Mass[3][3];
        fldc = new Mass[3][3];
        MassCount = 0;
        for (int i = 0, l = field.length; i < l; i++) {
            for (int j = 0, l2 = field[i].length; j < l2; j++) {
                field[i][j] = new Mass();
                fldp[i][j] = new Mass();
                fldc[i][j] = new Mass();
                MassCount++;
            }
        }
        winnerCheckers = new ICheckWinner[4];
        winnerCheckers[0] = new CheckWinnerHorizontal(this);
        winnerCheckers[1] = new CheckWinnerVertical(this);
        winnerCheckers[2] = new CheckWinnerDiagonalLeft(this);
        winnerCheckers[3] = new CheckWinnerDiagonalRight(this);
        players = new Player[2];
        started = false;
        activePlayer = null;
        filled = 0;
        cntGames = 0;
        cntXwin = 0;
        cntOwin = 0;
        gameMode = 0;
    }

    public void start() {
        resetPlayers();
        started = true;
    }

    private void resetPlayers() {
        players[0] = new Player("X");
        players[1] = new Player("O");
        setCurrentActivePlayer(players[0]);
    }

    public Mass[][] getField() {
        return field;
    }

    public Player checkWinner() {
        for (ICheckWinner winChecker : winnerCheckers) {
            Player winner = winChecker.checkWinner();
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
    }

    private void switchPlayers() {
    	if (activePlayer == players[0]) {
			activePlayer = players[1];
		}else {
			activePlayer = players[0];
		}
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }

    public boolean isFieldFilled() {
        return MassCount == filled;
    }

    public void reset() {
        resetField();
        resetPlayers();
    }

    private void resetField() {
        for (int i = 0, l = field.length; i < l; i++) {
            for (int j = 0, l2 = field[i].length; j < l2; j++) {
                field[i][j].fill(null);
            }
        }
        filled = 0;
        resetFld();
    }
    
    public void resetFld() {
        for (int i = 0, l = fldp.length; i < l; i++) {
            for (int j = 0, l2 = fldp[i].length; j < l2; j++) {
            	fldp[i][j].fill(null);
            	fldc[i][j].fill(null);
            }
        }
    }

	@Override
	public void checkDanger(Mass[][] fdlp, Mass[][] fdlc) {
		for (ICheckWinner danChecker : winnerCheckers) {
            danChecker.checkDanger(fdlp, fdlc);
        }
	}
    
}
