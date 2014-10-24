package ua.cn.defence1lab.tictactoe;

public interface ICheckWinner {
	public Player checkWinner();
	public void checkDanger(Mass[][] fldp, Mass[][] fldc);
}
