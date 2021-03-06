package ua.cn.defence1lab.tictactoe;

public class CheckWinnerDiagonalLeft implements ICheckWinner {
	private Game game;

	public CheckWinnerDiagonalLeft(Game game) {
		this.game = game;
	}

	public Player checkWinner() {
		Mass[][] field = game.getField();
		Player currPlayer;
		Player lastPlayer = null;
		int successCounter = 1;
		for (int i = 0, len = field.length; i < len; i++) {
			currPlayer = field[i][i].getPlayer();
			if (currPlayer != null) {
				if (lastPlayer == currPlayer) {
					successCounter++;
					if (successCounter == len) {
						return currPlayer;
					}
				}
			}
			lastPlayer = currPlayer;
		}
		return null;
	}

	/*
	 * �������� �� ����������� ��������� ����� ���������� ��������� ���� � ��
	 * �������� ������ fldp ����� ����� ��������� ��� ���������� ��� ������
	 * ������ fld� ����� ����� ��������� ��� �� ��� ������ (non-Javadoc)
	 * 
	 * @see
	 * ua.cn.defence1lab.tictactoe.ICheckWinner#checkDanger(ua.cn.defence1lab
	 * .tictactoe.Mass[][])
	 */
	public void checkDanger(Mass[][] fldp, Mass[][] fldc) {
		Mass[][] field = game.getField();
		Player currPlayer;
		int successCounterP = 0;
		int successCounterC = 0;
		int k=-1;
		for (int i = 0; i < field.length; i++) {
			currPlayer = field[i][i].getPlayer();
			//k = -1;
			if (currPlayer != null) {
				if (currPlayer.getName() == "X") {
					successCounterP++;
					successCounterC = -1;
				}else
				if (currPlayer.getName() == "O") {
					successCounterC++;
					successCounterP = -1;
				}
			} else {
				k = i;
			}
			if (successCounterP == field.length - 1) {
				if (k != -1) {
					fldp[k][k].fill(new Player("X"));
					return;
				}
			}else
			if (successCounterC == field.length - 1) {
				if (k != -1) {
					fldc[k][k].fill(new Player("O"));
					return;
				}
			}
		}
		return;
	}
}
