package protect.budgetwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_reward.*


class RewardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

        scratchCard.setOnScratchListener { scratchCard, visiblePercent ->
            if (visiblePercent > 0.3) {
                scratchCard.setVisibility(View.GONE)
            }
        }
        scratchCard2.setOnScratchListener { scratchCard, visiblePercent ->
            if (visiblePercent > 0.3) {
                scratchCard.setVisibility(View.GONE)
            }
        }
    }
}
