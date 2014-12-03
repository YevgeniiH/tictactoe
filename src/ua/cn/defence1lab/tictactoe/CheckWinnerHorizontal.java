package ua.cn.defence1lab.tictactoe;

public class CheckWinnerHorizontal implements ICheckWinner {
	private Game game;

	public CheckWinnerHorizontal(Game game) {
		this.game = game;
	}

	public Player checkWinner() {
		Mass[][] field = game.getField();
		Player currPlayer;
		Player lastPlayer = null;
		for (int i = 0, len = field.length; i < len; i++) {
			lastPlayer = null;
			int successCounter = 1;
			for (int j = 0, len2 = field[i].length; j < len2; j++) {
				currPlayer = field[i][j].getPlayer();
				if (currPlayer == lastPlayer
						&& (currPlayer != null && lastPlayer != null)) {
					successCounter++;
					if (successCounter == len2) {
						return currPlayer;
					}
				}
				lastPlayer = currPlayer;
			}
		}
		return null;
	}

	/*
	 * �������� �� ����������� ��������� ����� ���������� ��������� ���� � ��
	 * �������� ������ fldp ����� ����� ��������� ��� ���������� ��� ������
	 * ������ fld� ����� ����� ��������� ��� �� ��� ������ (non-Javadoc)
	 * 
	 */
	public void checkDanger(Mass[][] fldp, Mass[][] fldc) {
		Mass[][] field = game.getField();
		Player currPlayer = null;
		int k = -1;
		for (int i = 0, len = field.length; i < len; i++) {
			int successCounterP = 0;
			int successCounterC = 0;
			k = -1;
			for (int j = 0, len2 = field[i].length; j < len2; j++) {
				currPlayer = field[i][j].getPlayer();
				if (currPlayer != null) {
					if (currPlayer.getName() == "X") {
						successCounterP++;
						successCounterC = -1;
					}
					if (currPlayer.getName() == "O") {
						successCounterC++;
						successCounterP = -1;
					}
				} else {
					k = j;
				}
			}
			if (successCounterP == len - 1) {
				if (k != -1) {
					fldp[i][k].fill(new Player("X"));
					return;
				}
			}
			if (successCounterC == len - 1) {
				if (k != -1) {
					fldc[i][k].fill(new Player("O"));
					return;
				}
			}
		}
		return;
	}
}
