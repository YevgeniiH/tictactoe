package ua.cn.defence1lab.tictactoe;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void newGameB(View view) {
		Intent intent = new Intent (this, GameActivity.class);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setCancelable(false)
        // заголовок
        .setTitle("Выход")
        // сообщение
        .setMessage("Вы действительно хотите выйти")
        // иконка
        .setIcon(android.R.drawable.ic_dialog_alert)
        // кнопка положительного ответа
        .setPositiveButton("Да",
        		new DialogInterface.OnClickListener()
		        {
		            public void onClick(DialogInterface dialog, int which) 
		            {
		                MainActivity.this.finish();    
		            }
		        })
        // кнопка отрицательного ответа
        .setNegativeButton("Нет", null)
        .show();
    }
	
	public void rulesB (View viwer) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
        // заголовок
        adb.setTitle("Правила")
        // сообщение
        .setMessage(R.string.rulesS)
        // иконка
        .setIcon(android.R.drawable.ic_dialog_info)
        // кнопка отрицательного ответа
        .setNeutralButton("OK", null)
        .show();
	}
	
	public void aboutB (View viwer) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
        // заголовок
        adb.setTitle("Про игру")
        // сообщение
        .setMessage(R.string.aboutS)
        // иконка
        .setIcon(android.R.drawable.ic_dialog_info)
        // кнопка отрицательного ответа
        .setNeutralButton("OK", null)
        .show();
	}
}
