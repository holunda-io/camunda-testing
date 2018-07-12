package io.holunda.testing.example.maven;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@RequiredArgsConstructor
public class MavenJavaExampleProcess {

  private final RuntimeService runtimeService;

  public ProcessInstance start() {
    return runtimeService.startProcessInstanceByKey("MavenJavaExampleProcess");
  }
}
