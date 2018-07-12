package io.holunda.testing.core

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.ProcessEngineConfiguration.HISTORY_AUDIT
import org.camunda.bpm.engine.impl.history.HistoryLevel.HISTORY_LEVEL_AUDIT
import org.junit.Rule
import org.junit.Test

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
    .preInit("history=audit") { c -> c.historyLevel = HISTORY_LEVEL_AUDIT }
    .rule()

  @Test
  fun `history level is audit`() {
    assertThat(camunda.processEngineConfiguration.history).isEqualTo(HISTORY_AUDIT)
  }
}
