package io.holunda.testing.core

import org.camunda.bpm.engine.impl.ProcessEngineImpl
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl

typealias OnPreInit = (ProcessEngineConfigurationImpl) -> Unit
typealias OnPostInit = (ProcessEngineConfigurationImpl) -> Unit
typealias OnPostProcessEngineBuild = (ProcessEngineImpl) -> Unit
