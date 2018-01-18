package com.app.wired;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<MostRecent> liste;

    Element ee4;
    List<String> listeMetin;
    MostRecent n1;
    TextView tvbaslik, tvicerik, tvbaslik2, tvicerik2, tvbaslik3, tvicerik3, tvbaslik4, tvicerik4, tvbaslik5, tvicerik5;
    ImageView img, img2, img3, img4, img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = new ArrayList<>();
        listeMetin = new ArrayList<>();


        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/carter-one/CarterOne.ttf");



        tvbaslik = (TextView) findViewById(R.id.textView1);
        tvbaslik.setTypeface(face);
        tvicerik = (TextView) findViewById(R.id.textview2);

        tvbaslik2 = (TextView) findViewById(R.id.textView3);
        tvbaslik2.setTypeface(face);
        tvicerik2 = (TextView) findViewById(R.id.textview4);

        tvbaslik3 = (TextView) findViewById(R.id.textView5);
        tvbaslik3.setTypeface(face);
        tvicerik3 = (TextView) findViewById(R.id.textview6);

        tvbaslik4 = (TextView) findViewById(R.id.textView7);
        tvbaslik4.setTypeface(face);
        tvicerik4 = (TextView) findViewById(R.id.textview8);

        tvbaslik5 = (TextView) findViewById(R.id.textView9);
        tvbaslik5.setTypeface(face);
        tvicerik5 = (TextView) findViewById(R.id.textview10);

        if (CheckInternet.isNetwork(MainActivity.this)) {

           new getir().execute();


        } else {

            Toast.makeText(getApplicationContext(), "Lütfen internet bağlantınızı kontrol ediniz.", Toast.LENGTH_LONG).show();

        }
        img = (ImageView) findViewById(R.id.imageView1);
        img.setEnabled(false);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img2.setEnabled(false);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img3.setEnabled(false);
        img4 = (ImageView) findViewById(R.id.imageView4);
        img4.setEnabled(false);
        img5 = (ImageView) findViewById(R.id.imageView5);
        img5.setEnabled(false);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tv.setText(listeMetin.get(0));
                Intent myIntent = new Intent(MainActivity.this, second.class);
                myIntent.putExtra("key", listeMetin.get(0)); //Optional parameters
                myIntent.putExtra("key1", liste.get(0).baslikText); //Optional parameters
                myIntent.putExtra("key2", liste.get(0).imageurl); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, second.class);
                myIntent.putExtra("key", listeMetin.get(1)); //Optional parameters
                myIntent.putExtra("key1", liste.get(1).baslikText); //Optional parameters
                MainActivity.this.startActivity(myIntent);

                //tv.setText(listeMetin.get(1));
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, second.class);
                myIntent.putExtra("key", listeMetin.get(2)); //Optional parameters
                myIntent.putExtra("key1", liste.get(2).baslikText); //Optional parameters
                MainActivity.this.startActivity(myIntent);

              //  tv.setText(listeMetin.get(2));
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, second.class);

                myIntent.putExtra("key", listeMetin.get(3)); //Optional parameters
                myIntent.putExtra("key1", liste.get(3).baslikText); //Optional parameters
                MainActivity.this.startActivity(myIntent);

               // tv.setText(listeMetin.get(3));
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, second.class);
                myIntent.putExtra("key", listeMetin.get(4)); //Optional parameters
                myIntent.putExtra("key1", liste.get(4).baslikText); //Optional parameters
                MainActivity.this.startActivity(myIntent);

               // tv.setText(listeMetin.get(4));

                /*List<String> list = Arrays.asList(listeMetin.get(0).split(" "));

                List<words> listemm=new ArrayList<>();

                Set<String> uniqueWords = new HashSet<String>(list);
                for (String word : uniqueWords) {
                    words n1=new words();

                    n1.icerik=word;
                    n1.deger=Collections.frequency(list, word);
                    listemm.add(n1);
                }
                Collections.sort(listemm, new Comparator<words>() {
                    @Override
                    public int compare(words c2, words c1) {
                        return Integer.compare( c1.deger,c2.deger);
                    }
                });

                for (words ww:listemm){
                    Log.v("x",ww.icerik + ": " + ww.deger);
                }*/


            }
        });
    }

    class getir extends AsyncTask<Void, Void, Void> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Makaleler Yükleniyor...");
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... id) {

            try {
                Document doc = Jsoup.connect("https://www.wired.com/most-recent/").get();
                for (int i = 0; i < 5; i++) {

                    ee4 = doc.select("li.archive-item-component").get(i);
                    n1 = new MostRecent();
                    n1.baslikText = ee4.select("h2").text();
                    n1.icerikText = ee4.select("p").text();

                    if (ee4.select("img").toString().trim() == "") {

                        n1.imageurl = null;
                        n1.bm = null;

                    } else {


                        n1.imageurl = ee4.select("img").attr("src").toString();
                        InputStream input = new java.net.URL(n1.imageurl).openStream();
                        n1.bm = BitmapFactory.decodeStream(input);
                    }
                    n1.link = ee4.select("a").first().attr("href");


                    liste.add(n1);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (liste.get(0).bm != null)
                img.setImageBitmap(liste.get(0).bm);
            if (liste.get(1).bm != null)
                img2.setImageBitmap(liste.get(1).bm);
            if (liste.get(2).bm != null)
                img3.setImageBitmap(liste.get(2).bm);
            if (liste.get(3).bm != null)
                img4.setImageBitmap(liste.get(3).bm);
            if (liste.get(4).bm != null)
                img5.setImageBitmap(liste.get(4).bm);

            tvbaslik.setText(liste.get(0).baslikText);
            tvbaslik2.setText(liste.get(1).baslikText);
            tvbaslik3.setText(liste.get(2).baslikText);
            tvbaslik4.setText(liste.get(3).baslikText);
            tvbaslik5.setText(liste.get(4).baslikText);

            tvicerik.setText(liste.get(0).icerikText);
            tvicerik2.setText(liste.get(1).icerikText);
            tvicerik3.setText(liste.get(2).icerikText);
            tvicerik4.setText(liste.get(3).icerikText);
            tvicerik5.setText(liste.get(4).icerikText);
            pd.dismiss();
            new getirMetin().execute();

        }
    }

    class getirMetin extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                for (int i = 0; i < 5; i++) {
                    Document doc2 = Jsoup.connect("https://www.wired.com" + liste.get(i).link).get();
                    String y = "";
                    Elements eee11 = doc2.select("article").first().children();
                    for (Element e : eee11) {
                        if (e.tagName() == "h1") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "h2") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "h3") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();
                        }
                        if (e.tagName() == "h4") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();
                        }
                        if (e.tagName() == "p") {
                            y += System.getProperty("line.separator");
                            y += e.text();
                            y += System.getProperty("line.separator");
                        }
                        if (e.tagName() == "section") {
                            for (Element a : e.children()) {
                                if (a.tagName() == "h3") {
                                    y += System.getProperty("line.separator");
                                    y += e.text().toUpperCase();
                                    y += System.getProperty("line.separator");
                                }
                                if (a.tagName() == "p") {
                                    y += System.getProperty("line.separator");
                                    y += e.text();
                                    y += System.getProperty("line.separator");
                                }
                            }
                        }
                    }
                    Elements eee1 = doc2.select("article").first().children().first().children();
                    for (Element e : eee1) {
                        if (e.tagName() == "h1") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "h2") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "h3") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "h4") {
                            y += System.getProperty("line.separator");
                            y += e.text().toUpperCase();

                        }
                        if (e.tagName() == "p") {
                            y += System.getProperty("line.separator");
                            y += e.text();
                            y += System.getProperty("line.separator");
                        }
                        if (e.tagName() == "section") {
                            for (Element a : e.children()) {
                                if (e.tagName() == "h1") {
                                    y += System.getProperty("line.separator");
                                    y += e.text().toUpperCase();

                                }
                                if (e.tagName() == "h2") {
                                    y += System.getProperty("line.separator");
                                    y += e.text().toUpperCase();

                                }
                                if (e.tagName() == "h3") {
                                    y += System.getProperty("line.separator");
                                    y += e.text().toUpperCase();

                                }
                                if (a.tagName() == "h4") {
                                    y += System.getProperty("line.separator");
                                    y += e.text().toUpperCase();
                                    y += System.getProperty("line.separator");
                                }
                                if (a.tagName() == "p") {
                                    y += System.getProperty("line.separator");
                                    y += e.text();
                                    y += System.getProperty("line.separator");
                                }
                            }
                        }
                    }

                    listeMetin.add(y);
                }
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            img.setEnabled(true);
            img2.setEnabled(true);
            img3.setEnabled(true);
            img4.setEnabled(true);
            img5.setEnabled(true);
        }
    }
}
