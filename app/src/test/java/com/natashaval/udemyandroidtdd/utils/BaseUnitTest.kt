package com.natashaval.udemyandroidtdd.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {
  @get:Rule
  var corotuinesTestRule = MainCoroutineScopeRule()

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

}