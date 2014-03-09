package com.alarm.tsnooze;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Menu extends ListActivity{

	String[] transportation = { "Train", "Public Transit", "Go bus"};
	public static int number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, transportation));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String display = transportation[position];
		
		if(position == 0)
		{
			number = 1000;
		}
		
		if(position == 1)
		{
			number = 200;
		}
		
		if(position == 2)
		{
			number = 500;
		}
		
	
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("Menu.number", number);
		
		try
		{
		Class menuClass = Class.forName("com.alarm.tsnooze." + display);
		Intent menuIntent = new Intent(Menu.this, menuClass);
		startActivity(menuIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		//Intent showActivity = new Intent (getApplicationContext(), MainActivity.class);
		//startActivity(showActivity);
		
		//showActivity.putExtra("number", number);
		//finish();
		
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		 
        //Sending data to another Activity
        nextScreen.putExtra("number", number);

        startActivity(nextScreen);
	}

}
