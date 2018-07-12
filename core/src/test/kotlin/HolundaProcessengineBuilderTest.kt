package io.holunda.testing.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import java.util.function.Consumer

class StandAloneEngineTest {

  @get:Rule
  val camunda = HolundaProcessEngineBuilder().rule()

  @Test
  fun `can start process engine`() {
    assertThat(camunda.processEngine).isNotNull
  }
}

class PreInitTest {

  @get:Rule
  val camunda = HolundaProcessEngineBuilder()
    .preInit(name="retries=10", block = Consumer { it.defaultNumberOfRetries = 10 })
    .rule()

  @Test
  fun `default retries modified`() {
    assertThat(camunda.processEngineConfiguration.defaultNumberOfRetries).isEqualTo(10)
  }
}
