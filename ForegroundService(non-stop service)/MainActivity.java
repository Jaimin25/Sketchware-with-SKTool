//Add this code on button1 onClick event to start the service
    Intent serviceIntent = new Intent(getApplicationContext(), ForegroundService.class);
    serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
    androidx.core.content.ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
		
//Add this code on button2 onClick event to stop the service
    Intent serviceIntent = new Intent(getApplicationContext(), ForegroundService.class);
    stopService(serviceIntent);
