package com.ssnatech.kiasvayuvajra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)

        viewPager.adapter = CheesePagerAdapter(Cheeses.CheeseStrings)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val originStation: Spinner = findViewById(R.id.orig)
        val destStation: Spinner = findViewById(R.id.dest)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            originStation.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            destStation.adapter = adapter
        }


        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position % 3 == 0)
                tab.text = "Stations"
            else if (position % 3 == 1)
                tab.text = "Orig -> Dest"
            else
                tab.text = "Dest -> Orig"

            //tab.text = "${(position % 3)}"
        }.attach()
    }

    class CheesePagerAdapter(private val cheeseStrings: Array<String>) : RecyclerView.Adapter<CheeseViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
            return CheeseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stations, parent, false))
        }

        override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
            holder.cheeseName.text = cheeseStrings[position]
        }

        override fun getItemCount() = cheeseStrings.size
    }

    class CheeseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cheeseName: TextView = view.findViewById(R.id.cheese_name)
    }

    object Cheeses {
        val CheeseStrings = arrayOf(
            "Abbaye de Belloc",
            "Abbaye du Mont des Cats",
            "Abertam"
        )
    }
}

