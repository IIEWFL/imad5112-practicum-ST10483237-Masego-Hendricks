package vcmsa.ci.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class DetailedView : AppCompatActivity() {


        private lateinit var song: ArrayList<String>
        private lateinit var artist: ArrayList<String>
        private lateinit var rating: ArrayList<Int>
        private lateinit var comments: ArrayList<String>
        private lateinit var songView: TextView
        private lateinit var averageView: TextView


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_detailed_view)



            song=  intent.getStringArrayListExtra("item") ?: arrayListOf()
            artist = intent.getStringArrayListExtra("category") ?: arrayListOf()
            rating = intent.getIntegerArrayListExtra("quantity") ?: arrayListOf()
            comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
            songView = findViewById(R.id.songView)
            averageView = findViewById(R.id.averageView)




            val displayButton: Button = findViewById(R.id.btnDisplay)
            val averageButton: Button = findViewById(R.id.btnAverage)
            val returnButton: Button = findViewById(R.id.btnReturn)
            val exitButton: Button = findViewById(R.id.btnExit2)
            val songView : TextView = findViewById(R.id.songView)
            val averageView: TextView = findViewById(R.id.averageView)


            displayButton.setOnClickListener {
               displayView()
                    }

            //Calculate Average rating
            averageButton.setOnClickListener {
                calculateAverageRating()
                }


            returnButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            exitButton.setOnClickListener {
                finishAffinity()
                exitProcess(0)
            }
        }

        private fun displayView() {
            val stringBuilder = StringBuilder()
            if (song.isNotEmpty()) {
                for (i in song.indices) {
                    stringBuilder.append("Song: ${song[i]}\n")
                    stringBuilder.append("Artist: ${artist[i]}\n")
                    stringBuilder.append("Rating: ${rating[i]}\n")
                    stringBuilder.append("Comments: ${comments[i]}\n\n")
                }
                songView.text = stringBuilder.toString()


            } else {
                songView.text = "Playist is empty."
            }
        }

        private fun calculateAverageRating() {
             if (rating.isNotEmpty()) {
            val average = rating.average()  // returns Double
            averageView.text = "Average Rating: %.2f".format(average)
        } else {
            averageView.text = "No ratings to calculate average."
        }
    }

}





