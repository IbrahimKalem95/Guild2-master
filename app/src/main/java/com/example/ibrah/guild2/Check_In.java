package com.example.ibrah.guild2;

import android.content.Context;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Check_In extends AppCompatActivity {

    ArrayList<FoursquareVenue> venuesList;

    final String CLIENT_ID = "PDBPO5VOPZPXOVU14WNDAKSZ0OUD3LASVP43VDH3GQGTTRM2";
    final String CLIENT_SECRET = "4W5NDOBJCYZRCZ0WINSIAJD2IYVSNKZE2NBYWOGC4U3U5L1B";

    String latitude = null;
    String longtitude = null;

    ArrayAdapter<String> myAdapter;

    ListView listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__in);

        listData = (ListView)findViewById(R.id.listData);
        new FourquareService().execute();
    }

    public URL URLOlustur(URL url) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        //noinspection MissingPermission
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

        if (location == null)
        {
            Toast.makeText(getApplicationContext(),"Lokasyon bulunamadı",Toast.LENGTH_LONG).show();
        }else
        {
            Geocoder geocoder = new Geocoder(Check_In.this);
            try {
                latitude = String.valueOf(geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1).get(0).getLatitude());
                longtitude = String.valueOf(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1).get(0).getLongitude());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            url = new URL("https://api.foursquare.com/v2/venues/search?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll="+latitude + ","+longtitude);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private class FourquareService extends AsyncTask<URL, Void, String> {
        URL url;
        String forecastJsonStr = null;

        @Override
        protected String doInBackground(URL... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                urlConnection = (HttpURLConnection) URLOlustur(url).openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                if (inputStream == null)
                {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                {

                    sb.append(line + "\n");

                }
                if (sb.length() == 0)
                {
                    return null;
                }
                forecastJsonStr = sb.toString();
                return forecastJsonStr;
            }

            catch (IOException e)
            {
                Log.e("PlaceholderFragment", "Error ", e);

                return null;
            } finally{
                if (urlConnection != null)
                {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e)
                    {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }


        }
        @Override
        protected void onPostExecute(String result) {
            if (forecastJsonStr == null) {

            } else {

                venuesList = (ArrayList<FoursquareVenue>) parseFoursquare(forecastJsonStr);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {

                    listTitle.add(i, venuesList.get(i).getName() + ", " + venuesList.get(i).getCategory() + "" + venuesList.get(i).getCity());
                }
                myAdapter = new ArrayAdapter<String>(Check_In.this, R.layout.row_layout, R.id.listText, listTitle);
                listData.setAdapter(myAdapter);
            }
        }
    }


    private static ArrayList<FoursquareVenue> parseFoursquare(final String forecastJsonStr) {

        ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(forecastJsonStr);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        FoursquareVenue poi = new FoursquareVenue();
                        if (jsonArray.getJSONObject(i).has("name")) {
                            poi.setName(jsonArray.getJSONObject(i).getString("name"));

                            if (jsonArray.getJSONObject(i).has("location")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                        poi.setCity(jsonArray.getJSONObject(i).getJSONObject("location").getString("city"));
                                    }
                                    if (jsonArray.getJSONObject(i).has("categories")) {
                                        if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                                            }
                                        }
                                    }
                                    temp.add(poi);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<FoursquareVenue>();
        }
        return temp;
    }
}
