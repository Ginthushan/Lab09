package com.example.lab9csci2020;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private LineChart<Integer, Integer> lineChart;

    public void initialize(){
        Long periodStart = LocalDate.of(1970,1,1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        Long periodEnd = LocalDate.of(2015,12,31).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        List<Float> google_price = new ArrayList<>();
        List<Float> apple_price = new ArrayList<>();


        //Getting google stock price and adding it to Chart

        try{
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=" + periodStart + "&period2="+ periodEnd +"&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = in.readLine();
            while ((line = in.readLine()) != null){
                String[] data = line.split(",");
                google_price.add(Float.parseFloat(data[4]));
            }
            in.close();


            XYChart.Series series = new XYChart.Series();
            series.setName("Google Stock Price");

            for (int i = 0; i < google_price.size(); i++) {
                String x = String.valueOf(i);

                series.getData().add(new XYChart.Data(x , google_price.get(i)));
            }

            lineChart.getData().add(series);
            lineChart.setCreateSymbols(false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Getting apple stock price and adding it to Chart

        try{
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=" + periodStart + "&period2="+ periodEnd +"&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = in.readLine();
            while ((line = in.readLine()) != null){
                String[] data = line.split(",");
                apple_price.add(Float.parseFloat(data[4]));
            }
            in.close();


            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Apple Stock Price");

            for (int i = 0; i < google_price.size(); i++) {
                String x = String.valueOf(i);
                series1.getData().add(new XYChart.Data(x , apple_price.get(i)));
            }

            lineChart.getData().add(series1);
            lineChart.setCreateSymbols(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}