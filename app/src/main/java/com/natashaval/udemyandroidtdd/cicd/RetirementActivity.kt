package com.natashaval.udemyandroidtdd.cicd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.utils.async.AppCenterConsumer
import com.natashaval.udemyandroidtdd.databinding.ActivityRetirementBinding
import kotlin.math.pow

class RetirementActivity : AppCompatActivity() {
  private lateinit var binding: ActivityRetirementBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityRetirementBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val future = Crashes.hasCrashedInLastSession()
    future.thenAccept(AppCenterConsumer {
      if (it) {
        Toast.makeText(this, "Oops! Sorry about that crash!", Toast.LENGTH_LONG).show()
      }
    })

    binding.calculateButton.setOnClickListener {
      // Crashes.generateTestCrash()
      try {
        val interestRate = binding.interestEditText.text.toString().toFloat()
        val currentAge = binding.ageEditText.text.toString().toInt()
        val retirementAge = binding.retirementEditText.text.toString().toInt()
        val monthly = binding.monthlySavingsEditText.text.toString().toFloat()
        val current = binding.currentEditText.text.toString().toFloat()

        val properties: HashMap<String, String> = HashMap<String, String>()
        properties.put("interest_rate", interestRate.toString())
        properties.put("current_age", currentAge.toString())
        properties.put("retirement_age", retirementAge.toString())
        properties.put("monthly_savings", monthly.toString())
        properties.put("current_savings", current.toString())

        if (interestRate <= 0) {
          Analytics.trackEvent("wrong_interest_rate", properties)
        }
        if (retirementAge <= currentAge) {
          Analytics.trackEvent("wrong_age", properties)
        }

        val futureSavings =
          calculateRetirement(interestRate, current, monthly, (retirementAge - currentAge) * 12)

        binding.resultTextView.text =
          "At the current rate of $interestRate%, saving \$$monthly a month you will have \$${
            String.format(
              "%f",
              futureSavings
            )
          } by $retirementAge."
      } catch (ex: Exception) {
        Analytics.trackEvent(ex.message)
      }
    }
  }

  private fun calculateRetirement(
    interestRate: Float,
    currentSavings: Float,
    monthly: Float,
    numMonths: Int
  ): Float {
    var futureSavings = currentSavings * (1 + (interestRate / 100 / 12)).pow(numMonths)

    for (i in 1 .. numMonths) {
      futureSavings += monthly * (1 + (interestRate / 100 / 12)).pow(i)
    }

    return futureSavings
  }
}