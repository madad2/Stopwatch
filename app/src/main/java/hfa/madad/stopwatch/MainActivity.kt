package hfa.madad.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer
    var running = false
    var offset = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = findViewById(R.id.stopwatch)

        //Кнопка start запускает секундомер, если он не работал
        val startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        //Кнопка pause останавливает секундомер, если он работал
        val pauseButton = findViewById<Button>(R.id.pause)
        pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        //Кнопка reset обнуляет offset и базовое время
        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    //Обновляет время stopwatch.base
    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    //Сохраняет offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
}