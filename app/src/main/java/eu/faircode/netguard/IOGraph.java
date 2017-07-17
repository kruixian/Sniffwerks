package eu.faircode.netguard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by Mian Kai on 12/7/2017.
 */

public class IOGraph extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        // first series is a line
        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.5) * 20*(Math.random()*10+1));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);

        DatabaseHelper dh = DatabaseHelper.getInstance(IOGraph.this);

        ArrayList<TimeInterval> intervalList = new ArrayList<TimeInterval>();
        ArrayList<String> timeList = dh.getTime();

        if(!timeList.isEmpty()) {
            //Log.d("packetcount", dh.getCount());
            long startTime = Long.parseLong(timeList.get(0));
            Log.d("start", Long.toString(startTime));
            long endTime = Long.parseLong(timeList.get(timeList.size() - 1));
            Log.d("end", Long.toString(endTime));
            long intervals = (endTime - startTime) / 1000;
            //Log.d("intervals", Long.toString(intervals));
            if(intervals > 1)
                intervalList.add(new TimeInterval(0, startTime, startTime + 1000));
            else
                intervalList.add(new TimeInterval(0, startTime, endTime));
            for (int i = 1; i <= intervals; i++) {
                if (i < intervals)
                    intervalList.add(new TimeInterval(i, intervalList.get(i - 1).getEnd(), intervalList.get(i - 1).getEnd() + 1000));
                else
                    intervalList.add(new TimeInterval(i, intervalList.get(i - 1).getEnd(), endTime));
            }

            for (int i = 0; i < timeList.size(); i++) {
                for (int j = 0; j < intervalList.size(); j++) {
                    if (Long.parseLong(timeList.get(i)) >= intervalList.get(j).getStart()) {
                        if (Long.parseLong(timeList.get(i)) <= intervalList.get(j).getEnd()) {
                            intervalList.get(j).incrementCount();
                        }
                    }
                }
            }

            for (TimeInterval t : intervalList) {
                Log.d("id ", Integer.toString(t.getId()));
                //Log.d("start ", Long.toString(t.getStart()));
                //Log.d("end ", Long.toString(t.getEnd()));
                Log.d("count ", Integer.toString(t.getCount()));
            }

            //for (String time : timeList)
            //Log.d("time ", time);
        }
    }
}