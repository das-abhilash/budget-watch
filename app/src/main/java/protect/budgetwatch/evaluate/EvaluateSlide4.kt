package protect.budgetwatch.evaluate

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate4_layout.*
import protect.budgetwatch.GoalActivity
import protect.budgetwatch.R

class EvaluateSlide4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate4_layout)
        btn_sure.setOnClickListener {
            val intent = Intent(this, EvaluateSlide5::class.java)
            startActivity(intent)
            finish()
        }
        btn_whitelist.setOnClickListener {
            val intent = Intent(this, GoalActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
