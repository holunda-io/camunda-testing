package io.holunda.testing.example.maven;

import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.testing.core.HolundaProcessEngineBuilder;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@Slf4j
@Deployment(resources = "MavenJavaExampleProcess.bpmn")
public class MavenJavaExampleProcessTest {

  @Rule
  public final ProcessEngineRule camunda = new HolundaProcessEngineBuilder()
    .preInit("foo", c -> log.info("Hi, I am in the plugin #foo  on preInit!"))
    .rule();

  private MavenJavaExampleProcess process;

  @Before
  public void setUp() throws Exception {
    process = new MavenJavaExampleProcess(camunda.getRuntimeService());
  }

  @Test
  public void can_start() {
    ProcessInstance processInstance = process.start();

    assertThat(camunda.getTaskService().createTaskQuery().singleResult().getName())
      .isEqualTo("Do stuff");
  }
}
