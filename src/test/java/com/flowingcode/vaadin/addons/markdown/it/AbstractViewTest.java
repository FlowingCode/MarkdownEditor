/*-
 * #%L
 * Markdown Editor Add-on
 * %%
 * Copyright (C) 2024 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package com.flowingcode.vaadin.addons.markdown.it;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.parallel.ParallelTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Base class for ITs
 *
 * <p>The tests use Chrome driver (see pom.xml for integration-tests profile) to run integration
 * tests on a headless Chrome. If a property {@code test.use .hub} is set to true, {@code
 * AbstractViewTest} will assume that the TestBench test is running in a CI environment. In order to
 * keep the this class light, it makes certain assumptions about the CI environment (such as
 * available environment variables). It is not advisable to use this class as a base class for you
 * own TestBench tests.
 *
 * <p>To learn more about TestBench, visit <a
 * href="https://vaadin.com/docs/v10/testbench/testbench-overview.html">Vaadin TestBench</a>.
 */
public abstract class AbstractViewTest extends ParallelTest {
  private static final int SERVER_PORT = 8080;

  private final String route;

  @Rule public ScreenshotOnFailureRule rule = new ScreenshotOnFailureRule(this, true);

  public AbstractViewTest() {
    this("");
  }

  protected AbstractViewTest(String route) {
    this.route = route;
  }

  @BeforeClass
  public static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @Override
  @Before
  public void setup() throws Exception {
    if (isUsingHub()) {
      super.setup();
    } else {
      setDriver(TestBench.createDriver(new ChromeDriver()));
    }
    getDriver().get(getURL(route));
  }

  /**
   * Returns deployment host name concatenated with route.
   *
   * @return URL to route
   */
  private static String getURL(String route) {
    return String.format("http://%s:%d/%s", getDeploymentHostname(), SERVER_PORT, route);
  }

  /** Property set to true when running on a test hub. */
  private static final String USE_HUB_PROPERTY = "test.use.hub";

  /**
   * Returns whether we are using a test hub. This means that the starter is running tests in
   * Vaadin's CI environment, and uses TestBench to connect to the testing hub.
   *
   * @return whether we are using a test hub
   */
  private static boolean isUsingHub() {
    return Boolean.TRUE.toString().equals(System.getProperty(USE_HUB_PROPERTY));
  }

  /**
   * If running on CI, get the host name from environment variable HOSTNAME
   *
   * @return the host name
   */
  private static String getDeploymentHostname() {
    return isUsingHub() ? System.getenv("HOSTNAME") : "localhost";
  }
}
