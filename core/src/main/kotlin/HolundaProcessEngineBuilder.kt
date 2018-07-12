package io.holunda.testing.core

import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngineConfiguration
import org.camunda.bpm.engine.impl.ProcessEngineImpl
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration
import org.camunda.bpm.engine.impl.history.HistoryLevel
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.mock.MockExpressionManager
import java.util.function.Consumer

class HolundaProcessEngineBuilder {

  private val configuration = object : StandaloneInMemProcessEngineConfiguration() {
    init {
      setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL)
      setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
      isJobExecutorActivate = false
      setDbMetricsReporterActivate(false)
      setExpressionManager(MockExpressionManager())

      // init with empty list
      setProcessEnginePlugins(mutableListOf())
      setCustomBatchJobHandlers(mutableListOf())
      setCustomEventHandlers(mutableListOf())
      setCustomFormEngines(mutableListOf())
      setCustomIncidentHandlers(mutableListOf())
      setCustomJobHandlers(mutableListOf())
      customPostBPMNParseListeners = mutableListOf()
      customPreBPMNParseListeners = mutableListOf()
    }
  }

  fun plugin(plugin: ProcessEnginePlugin) = configuration.processEnginePlugins.add(plugin).let { this }

  fun preInit(name: String, block: OnPreInit) = plugin(createPlugin(name=name, onPreInit = block))
  fun onPostInit(name: String, block: OnPostInit) = plugin(createPlugin(name=name, onPostInit = block))

  fun engine() = configuration.buildProcessEngine() as ProcessEngineImpl

  fun rule() = ProcessEngineRule(engine())

}

fun createPlugin(
  name: String,
  onPreInit: OnPreInit = Consumer { },
  onPostInit: OnPostInit = Consumer { },
  onPostProcessEngineBuild: OnPostProcessEngineBuild = Consumer { }) = object : AbstractProcessEnginePlugin() {

  override fun toString(): String = name
  override fun preInit(processEngineConfiguration: ProcessEngineConfigurationImpl) = onPreInit.accept(processEngineConfiguration).let { Unit }
  override fun postInit(processEngineConfiguration: ProcessEngineConfigurationImpl) = onPostInit.accept(processEngineConfiguration).let { Unit }
  override fun postProcessEngineBuild(processEngine: ProcessEngine) = onPostProcessEngineBuild.accept(processEngine as ProcessEngineImpl).let { Unit }

}
