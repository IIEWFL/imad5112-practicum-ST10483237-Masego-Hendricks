package vcmsa.ci.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        val song = mutableListOf<String>()
        val artist = mutableListOf<String>()
        val rating = mutableListOf<Int>()
        val comments = mutableListOf<String>()
        var songCount = 0

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val addButton: Button = findViewById(R.id.btnAdd)
        val nextButton: Button = findViewById(R.id.btnNext)
        val exitButton: Button = findViewById(R.id.btnExit)


        addButton.setOnClickListener {
            songCount = 0
            showAddSongDialog()
        }

        //Set up next slide button details
        nextButton.setOnClickListener {
                val intent = Intent(this, DetailedView::class.java)
                startActivity(intent)}
        if (song.isNotEmpty())  {
                intent.putStringArrayListExtra("song", ArrayList(song))
                intent.putStringArrayListExtra("artist", ArrayList(artist))
                intent.putIntegerArrayListExtra("rating", ArrayList(rating))
                intent.putStringArrayListExtra("comments", ArrayList(comments))
            songCount
            if (songCount < 4){
                showAddSongDialog()
            }

            } else {
                Snackbar.make(nextButton, "Playlist is empty. Add items first.", Snackbar.LENGTH_SHORT).show()
            }


        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

    private fun showAddSongDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Song")

        val view = layoutInflater.inflate(R.layout.activity_dialog, null)
        val songNameEditText: EditText = view.findViewById(R.id.songNameEditText)
        val artistEditText: EditText = view.findViewById(R.id.artistEditText)
        val ratingEditText: EditText = view.findViewById(R.id.ratingEditText)
        val commentsEditText: EditText = view.findViewById(R.id.commentsEditText)

        builder.setView(view)
        builder.setPositiveButton("Add") { dialog, _ ->
            val songName = songNameEditText.text.toString().trim()
            val artist = artistEditText.text.toString().trim()
            val ratingStr = ratingEditText.text.toString().trim()
            val comments = commentsEditText.text.toString().trim()

            //Error Handling
            if (songName.isEmpty() || artist.isEmpty() || ratingStr.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), "song name, artist, and rating cannot be empty.", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating <= 0 || rating > 5) {
                Snackbar.make(findViewById(android.R.id.content), "Invalid Rating. Please enter a  number (1-5).", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }


            Snackbar.make(findViewById(android.R.id.content), "$songName added to the playlist.", Snackbar.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}








