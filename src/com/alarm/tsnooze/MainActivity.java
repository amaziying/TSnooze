package com.alarm.tsnooze;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.widget.Toast;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager; 
import android.media.RingtoneManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.os.Vibrator;
import android.os.Message;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.Geofence.Builder;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;  
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;  
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;  
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;


public class MainActivity extends Activity implements LocationListener{

	private GoogleMap map;
	private double lat;
	private double lon;
	private int distance;
	private SupportMapFragment mapFragment;
	private double lt;
	private double ln;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pinpoint your destination!");
        if (IsGooglePlay()){
        	setContentView(R.layout.activity_main);
        	setUpMap();
        	
        	map.setOnMapClickListener(new OnMapClickListener() {  
                private Object context;

				@Override  
                public void onMapClick(LatLng point) {  
                     // TODO Auto-generated method stub  
                	map.clear();
                    map.addMarker(new MarkerOptions().position(point).title(  
                               point.toString()));  
                  lat = point.latitude;
                  lon = point.longitude;
                  
                  CircleOptions circleOptions = new CircleOptions()
                  .center( new LatLng(lat, lon) )
                  .radius(100)
                  .fillColor(0x40ff0000)
                  .strokeColor(Color.TRANSPARENT)
                  .strokeWidth(2);
                 
                // Get back the mutable Circle
                Circle circle = map.addCircle(circleOptions);
                
                Marker stopMarker = map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title("Fence"));
                System.out.println("hello");
                }  
                
                
                
           });
        }
        
        
    }
                  
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private Boolean IsGooglePlay() {
    	
    	int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    	
    	if (status == ConnectionResult.SUCCESS){
    		return(true);
    	}
    	else {
    		Toast.makeText(this, "Google Play is not available", Toast.LENGTH_SHORT).show();
    		
    	}
    	return(false);
    }
    
    private void setUpMap(){
    	
    	if (map == null){
    	map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
    	}
    	
    	if (map != null){
        	// map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
     
        LocationManager ln = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        String provider = ln.getBestProvider(new Criteria(), true);
        
        if (provider == null){
        	
        	onProviderDisabled(provider);
        	
        }
        
        Location loc = ln.getLastKnownLocation(provider);
        
        if (loc != null){
        	onLocationChanged(loc);
        }
    	}
    	
    }
    
    
    
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		lt = location.getLatitude();
		ln = location.getLongitude();
		
		LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLng(latlng));
		map.animateCamera(CameraUpdateFactory.zoomTo(10));
		
	}
	
	

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
