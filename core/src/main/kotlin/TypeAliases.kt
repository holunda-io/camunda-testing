package io.holunda.testing.core

import org.camunda.bpm.engine.impl.ProcessEngineImpl
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl
import java.util.function.Consumer

typealias OnPreInit = Consumer<ProcessEngineConfigurationImpl>
typealias OnPostInit = Consumer<ProcessEngineConfigurationImpl>
typealias OnPostProcessEngineBuild = Consumer<ProcessEngineImpl>
