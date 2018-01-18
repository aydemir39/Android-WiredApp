package com.app.wired;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class second extends AppCompatActivity {

    TextView tv1,tv2;
    String resultString;
    String s="";
    List<String> listemm2;
    List<words> listemm;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/carter-one/CarterOne.ttf");
        tv1=(TextView)findViewById(R.id.textView21);
        tv2=(TextView)findViewById(R.id.textView22);
        tv1.setTypeface(face);
        tv2.setTypeface(face);

        Intent intent=getIntent();
        Intent intent1=getIntent();
        tv1.setText(intent1.getStringExtra("key1").toUpperCase()+"\n"+intent.getStringExtra("key"));

        List<String> list = Arrays.asList(intent.getStringExtra("key").toLowerCase().split(" "));

        listemm=new ArrayList<>();
        listemm2=new ArrayList<>();

        Set<String> uniqueWords = new HashSet<String>(list);
        for (String word : uniqueWords) {

            if(word.equalsIgnoreCase("the")){ }
            else if(word.equalsIgnoreCase("to")){ }
            else if(word.equalsIgnoreCase("a")){ }
            else if(word.equalsIgnoreCase("not")){ }
            else if(word.equalsIgnoreCase("an")){ }
            else if(word.equalsIgnoreCase("but")){ }
            else if(word.equalsIgnoreCase("and")){ }
            else if(word.equalsIgnoreCase("and")){ }
            else if(word.equalsIgnoreCase("he")){ }
            else if(word.equalsIgnoreCase("she")){ }
            else if(word.equalsIgnoreCase("it")){ }
            else if(word.equalsIgnoreCase("i")){ }
            else if(word.equalsIgnoreCase("you")){ }
            else if(word.equalsIgnoreCase("we")){ }
            else if(word.equalsIgnoreCase("they")){ }
            else if(word.equalsIgnoreCase("of")){ }
            else if(word.equalsIgnoreCase("for")){ }
            else if(word.equalsIgnoreCase("in")){ }
            else if(word.equalsIgnoreCase("on")){ }
            else if(word.equalsIgnoreCase("at")){ }
            else if(word.equalsIgnoreCase("was")){ }
            else if(word.equalsIgnoreCase("were")){ }
            else if(word.equalsIgnoreCase("has")){ }
            else if(word.equalsIgnoreCase("have")){ }
            else if(word.equalsIgnoreCase("had")){ }
            else if(word.equalsIgnoreCase("this")){ }
            else if(word.equalsIgnoreCase("that")){ }
            else if(word.equalsIgnoreCase("his")){ }
            else if(word.equalsIgnoreCase("her")){ }
            else if(word.equalsIgnoreCase("him")){ }
            else if(word.equalsIgnoreCase("is")){ }
            else if(word.equalsIgnoreCase("are")){ }
            else if(word.equalsIgnoreCase("can")){ }
            else if(word.equalsIgnoreCase("or")){ }
            else if(word.equalsIgnoreCase("be")){ }
            else if(word.equalsIgnoreCase("could")){ }
            else if(word.equalsIgnoreCase("with")){ }
            else if(word.equalsIgnoreCase("from")){ }
            else if(word.equalsIgnoreCase("by")){ }
            else if(word.equalsIgnoreCase("as")){ }
            else if(word.equalsIgnoreCase("like")){}
            else if(word.equalsIgnoreCase("so")){ }
            else if(word.equalsIgnoreCase("its")){ }
            else if(word.equalsIgnoreCase("it's")){ }
            else if(word.equalsIgnoreCase("their")){ }
            else if(word.equalsIgnoreCase("my")){}
            else if(word.equalsIgnoreCase("mine")){ }
            else if(word.equalsIgnoreCase("your")){}
            else if(word.equalsIgnoreCase("yours")){ }
            else if(word.equalsIgnoreCase("our")){ }
            else if(word.equalsIgnoreCase("us")){}
            else if(word.equalsIgnoreCase("what")){ }
            else if(word.equalsIgnoreCase("when")){ }
            else if(word.contains(":")){ }
            else if(word.contains(",")){ }
            else if(word.contains("'")){ }
            else if(word.contains(".")){ }
            else if(word.contains("-")){}
            else if(word.contains("1")){ }
            else if(word.contains("2")){ }
            else if(word.contains("3")){ }
            else if(word.contains("4")){ }
            else if(word.contains("5")){}
            else if(word.contains("6")){ }
            else if(word.contains("7")){ }
            else if(word.contains("8")){ }
            else if(word.contains("9")){ }
            else if(word.contains("0")){ }





            else{
                words n1=new words();
                n1.icerik=word;
                n1.deger= Collections.frequency(list, word);

                listemm.add(n1);}


        }
        Collections.sort(listemm, new Comparator<words>() {
            @Override
            public int compare(words c2, words c1) {
                return Integer.compare( c1.deger,c2.deger);
            }
        });



        for (words ww:listemm)
        {


            Log.v("x",ww.icerik + ": " + ww.deger);



        }
       //String textToBeTranslated = "Hello world, yeah I know it is stereotye.";
        String languagePair = "en-tr"; //English to French ("<source_language>-<target_language>")
        //Executing the translation function


        new TranslatorBackgroundTask().execute(listemm.get(0).icerik,languagePair);
        new TranslatorBackgroundTask().execute(listemm.get(1).icerik,languagePair);
        new TranslatorBackgroundTask().execute(listemm.get(2).icerik,languagePair);
        new TranslatorBackgroundTask().execute(listemm.get(3).icerik,languagePair);
        new TranslatorBackgroundTask().execute(listemm.get(4).icerik,languagePair);

        //Log.v("xx",a1.get(0)+a1.get(1)+a1.get(2)+a1.get(3)+a1.get(4));

    }
    class TranslatorBackgroundTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            //String variables
            String textToBeTranslated = params[0];
            String languagePair = params[1];

            String jsonString;

            try {
                //Set up the translation call URL
                String yandexKey = "trnsl.1.1.20171107T164553Z.9624e1ec9ad6fc34.8355aeee696bdaf7c68363df86226fba5b10cb6a";
                String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + textToBeTranslated + "&lang=" + languagePair;
                URL yandexTranslateURL = new URL(yandexUrl);

                //Set Http Conncection, Input Stream, and Buffered Reader
                HttpURLConnection httpJsonConnection = (HttpURLConnection) yandexTranslateURL.openConnection();
                InputStream inputStream = httpJsonConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                //Set string builder and insert retrieved JSON result into it
                StringBuilder jsonStringBuilder = new StringBuilder();
                while ((jsonString = bufferedReader.readLine()) != null) {
                    jsonStringBuilder.append(jsonString + "\n");
                }

                //Close and disconnect
                bufferedReader.close();
                inputStream.close();
                httpJsonConnection.disconnect();

                //Making result human readable
                resultString = jsonStringBuilder.toString().trim();
                //Getting the characters between [ and ]
                resultString = resultString.substring(resultString.indexOf('[')+1);
                resultString = resultString.substring(0,resultString.indexOf("]"));
                //Getting the characters between " and "
                resultString = resultString.substring(resultString.indexOf("\"")+1);
                resultString = resultString.substring(0,resultString.indexOf("\""));


                Log.d("Translation Result:", resultString);

                return jsonStringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result)
        {


            s=resultString;
            listemm2.add(s);
            if(count==4){tv2.setText("Makalede En Çok Geçen 5 Kelime :"+"\n"+
                    listemm.get(0).icerik+" : "+listemm2.get(count-4).toLowerCase()+"           "+
                    listemm.get(1).icerik+" : "+listemm2.get(count-3).toLowerCase()+"\n"+
                    listemm.get(2).icerik+" : "+listemm2.get(count-2).toLowerCase()+"           "+
                    listemm.get(3).icerik+" : "+listemm2.get(count-1).toLowerCase()+"\n"+
                    listemm.get(4).icerik+" : "+listemm2.get(count).toLowerCase());}

            count++;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
