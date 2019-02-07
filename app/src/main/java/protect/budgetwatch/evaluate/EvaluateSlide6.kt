package protect.budgetwatch.evaluate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.evaluate6_layout.*
import protect.budgetwatch.BudgetActivity
import protect.budgetwatch.GoalActivity
import protect.budgetwatch.R

class EvaluateSlide6 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluate6_layout)
        val amount = intent.extras.getString("amount").toInt()
        val sharedpreferences = getSharedPreferences("yenom", Context.MODE_PRIVATE)
        val unbudgetedAmount = sharedpreferences.getInt("unbudgeted_amount", 0)
        val canAfford = unbudgetedAmount != 0 && amount / unbudgetedAmount < 0.3

        if (canAfford) {
            tv_result.text = "Go ahead and  \uD83C\uDF81  yourself ;)"
        } else {
            tv_result.text =
                "We love you spirit  ✨ Work hard and earn enough bucks to buy what you want. Now we redirect you to your goal screen."
        }

        btn_thnx.setOnClickListener {
            val intent: Intent = if (canAfford) {
                Intent(this, BudgetActivity::class.java)
            } else {
                Intent(this, GoalActivity::class.java)
            }
            startActivity(intent)
            finish()
        }

    }
}