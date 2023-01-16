import java.io.Serializable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Waed
 */
public class WeatherStations implements Serializable{
    
    private static String weatherStationId;
    private static String latitude;
    private static String longitude;
    
    private static Timer t;
    
    public static String tempertureReading;
    public static String pressureReading;
    public static String precipitionReading;
    public static String rainfallReading; 
    public static String humidityReading;
    public static String windSpeedReading;
    public static String windDirectionReading;
    public static String cloudHeightReading;
       
    public static int temperture;
    public static int pressure;
    public static int precipition;
    public static int rainfall;
    public static int humidity;
    public static int windSpeed;
    public static int windDirection;
    public static int cloudHeight;
    
    private String weatherStationDetails;
        
    public WeatherStations(String _weatherStationId, String _latitude, String _longitude) {
    
        weatherStationId = _weatherStationId;
        latitude = _latitude;
        longitude = _longitude;
        
        
        //According to  BME280 sensor developed by Bosch Sensortec
         //https://development.libelium.com/gases_pro_sensor_guide/sensors#temperature-humidity-and-pressure-sensor
         temperture = getRandomNumber(-40,85);

         //int randomTempertureInt =   Integer. parseInt(temperture);
         if (temperture <= 0) {

             humidity = getRandomNumber(68,100);

         } else if (temperture >= 60) {

             humidity = getRandomNumber(87,100);

         } else {

              humidity = getRandomNumber(0,100);

         }


         //According to https://weather.com/sports-recreation/fishing/news/fishing-barometer-20120328
         pressure = getRandomNumber(27,31);
         //////////////////////////////////////////////////////////////////////////////////////////////////////////

         //According to Wind Vane 
         //https://development.libelium.com/agriculture-sensor-guide/sensors#wind-vane
         windDirection = getRandomNumber(0,360);
         /////////////////////////////////////////////////////////////////////////////////////////////////////////

         // According to https://en.wikipedia.org/wiki/United_Kingdom_weather_records
         rainfall = getRandomNumber(0,341);
         /////////////////////////////////////////////////////////////////////////////////////////////////////////

         // According to https://en.wikipedia.org/wiki/Cloud#:~:text=High%20clouds%20form%20at%20altitudes,single%20genus%20cirrus%20(Ci).
         cloudHeight = getRandomNumber(5000,12200);
         /////////////////////////////////////////////////////////////////////////////////////////////////////////

          // According to https://physics.stackexchange.com/questions/485860/wind-speed-limit#:~:text=1%20Answer&text=answer%20was%20accepted%E2%80%A6-,According%20to%20Wikipedia%2C%20the%20highest%20wind%20speed%20not%20related%20to,about%20the%20speed%20of%20sound%22.
        //Highest speed mossible is 254 mph
         windSpeed = getRandomNumber(0,50);
         /////////////////////////////////////////////////////////////////////////////////////////////////////////

         //According to https://www.currentresults.com/Weather/United-Kingdom/average-yearly-precipitation.php#:~:text=Average%20Annual%20Precipitation%20for%20the%20United%20Kingdom&text=The%20annual%20amount%20of%20precipitation,885%20millimetres%20(33.7%20inches).
         precipition = getRandomNumber(0,45);
        
        tempertureReading = String.valueOf(temperture);
        pressureReading = String.valueOf(pressure);
        precipitionReading = String.valueOf(precipition);
        rainfallReading = String.valueOf(rainfall);
        humidityReading = String.valueOf(humidity);
        windSpeedReading = String.valueOf(windSpeed);
        windDirectionReading = String.valueOf(windDirection);
        cloudHeightReading = String.valueOf(cloudHeight);
        
        myTimer();
                 
    }
    
    private static void myTimer() {
    
        t = new Timer();
        
        t.schedule(new TimerTask() {
            
            String temp = "Waed";
            
            @Override
            public void run() {
 
                setTemperture(getMoreReadings(temperture));
                setPressure(getMoreReadings(pressure));
                setWindDirection(getMoreReadings(windDirection));
                setRainfall(getMoreReadings(rainfall));
                setCloudHeight(getMoreReadings(cloudHeight));
                setWindSpeed(getMoreReadings(windSpeed));
                setPrecipition(getMoreReadings(precipition));
                setHumidity(getMoreReadings(humidity));

            }
                        
        }, 0, 5000);
    }
    
    public static String getWeatherStationDetails() {
    
        String lineOfData = weatherStationId + "," 
                + latitude + ","
                + longitude + ","
                + tempertureReading + ","
                + pressureReading + ","
                + precipitionReading + ","
                + rainfallReading + ","
                + humidityReading + ","
                + windSpeedReading + ","
                + windDirectionReading + ","
                + cloudHeightReading;
        
        
        return lineOfData;
        
    }
    
    
    public static void setTemperture(String _temperture) {
    
        tempertureReading = _temperture;
        
    }
    
    public static String getTemperture() {
    
        return tempertureReading;
        
    }
    
    public static void setPressure(String _pressure) {
    
        pressureReading = _pressure;
        
    }
    
    public static String getPressure() {
    
        return pressureReading;
        
    }
    
     public static void setPrecipition(String _precipition) {
    
        precipitionReading = _precipition;
        
    }
    
    public static String getPrecipition() {
    
        return precipitionReading;
        
    }
    
    public static void setWindDirection(String _windDirection) {
    
        windDirectionReading = _windDirection;
        
    }
    
    public static String getWindDirection() {
    
        return windDirectionReading;
        
    }
    
    public static void setRainfall(String _rainfall) {
    
        rainfallReading = _rainfall;
        
    }
    
    public static String getRainfall() {
    
        return rainfallReading;
        
    }
    
    public static void setCloudHeight(String _cloudHeight) {
    
        cloudHeightReading = _cloudHeight;
        
    }
    
    public static String getCloudHeight() {
    
        return cloudHeightReading;
        
    }
    
    public static void setWindSpeed(String _windSpeed) {
    
        windSpeedReading = _windSpeed;
        
    }
    
    public static String getWindSpeed() {
    
        return windSpeedReading;
        
    }
    
    public static void setHumidity(String _humidity) {
    
        humidityReading = _humidity;
        
    }
    
    public static String getHumidity() {
    
        return humidityReading;
        
    }
    
    
    public static int getRandomNumber(int min, int max) {

        int randomDouble = (int) (Math.random() * (max - min + 1) + min); 
        
        return randomDouble;
    }
    
    public static String getMoreReadings(int initialNumber) {

        int randomDouble = (int) (Math.random() * (5 - 0 + 1) + 0); 
        
        int newReading = initialNumber + randomDouble;
        
        String convertRandomNumToString =  Integer.toString(newReading);
        
        return convertRandomNumToString;
    }
    
    public static String getId() {
        return weatherStationId;
    }
    
    public static String getLatitude() {
        return latitude;
    }
     
       public static String getLongitude() {
        return longitude;
    }
}
