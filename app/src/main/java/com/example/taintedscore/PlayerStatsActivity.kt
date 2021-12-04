package com.example.taintedscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.Chart

class PlayerStatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_stats)
        val chartWL: AnyChartView = findViewById(R.id.pie_chart_wlrank)
        chartWL.setChart(setPieChart())
    }

    fun setPieChart(): Chart {
        val pie = AnyChart.pie()
        val datalist: MutableList<DataEntry> = ArrayList()
        datalist.add(ValueDataEntry("Ankh", 6))
        datalist.add(ValueDataEntry("Horrified", 10))
        datalist.add(ValueDataEntry("Dune:Imperium", 3))
        pie.data(datalist)
        pie.title("Total plays:").outline()
        pie.fill("aquastyle").select(0)
        return pie
    }

}


