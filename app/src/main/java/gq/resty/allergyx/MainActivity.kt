package gq.resty.allergyx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val csvText = assets
            .open("[2022-02-26]アレルギー検査結果_exported_1 (4).csv")
            .bufferedReader()
            .use { it.readText() }

        val outList = CSVReader(StringReader(csvText))
            .apply { skip(1) }
            .use{it.readAll() }
            .apply { sortByDescending { it[1].toFloat() } }
            .map { "${it[0]} 》 ${it[1]}" }

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, outList)
        val list = findViewById<ListView>(R.id.listView)
        list.adapter = adapter
    }
}