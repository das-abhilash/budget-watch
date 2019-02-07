package protect.budgetwatch.info

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate1_layout.*
import protect.budgetwatch.R
import protect.budgetwatch.evaluate.EvaluateSlide3

class EvaluateSlide1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate1_layout)

        btn_luxury.setOnClickListener {
            val intent = Intent(this, EvaluateSlide2::class.java)
            startActivity(intent)
            finish()
        }

        btn_necessities.setOnClickListener {
            val intent = Intent(this, EvaluateSlide3::class.java)
            startActivity(intent)
            finish()
        }

    }


}
