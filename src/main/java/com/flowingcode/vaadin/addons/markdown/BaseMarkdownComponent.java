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
package com.flowingcode.vaadin.addons.markdown;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.react.ReactAdapterComponent;
import com.vaadin.flow.function.SerializableConsumer;

/**
 * Base class for Markdown based Components.
 */
@SuppressWarnings("serial")
@CssImport("./styles/markdown-editor-styles.css")
@NpmPackage(value = "mermaid", version = "11.2.1")
@NpmPackage(value = "@uiw/react-md-editor", version = "4.0.4")
@NpmPackage(value = "dompurify", version = "3.1.6")
public class BaseMarkdownComponent extends ReactAdapterComponent implements HasSize {
  
  /**
   * Defines the color schemes for the Markdown component.
   *
   * The color mode can be set using the {@link #setDataColorMode(DataColorMode)} method.
   *
   * <ul>
   *   <li>{@link #DARK}: Dark color scheme.
   *   <li>{@link #LIGHT}: Light color scheme.
   *   <li>{@link #AUTO}: Automatically detects the color scheme based on the user's system settings.
   * </ul>
   */
  public enum DataColorMode {
    DARK,
    LIGHT,
    /**
     * @deprecated Use LIGHT instead
     */
    @Deprecated
    LIGTH,
    AUTO};
  
  private String content;
  
  /**
   * Base constructor that receives the content of the markdown component.
   * 
   * @param content content to be used in the Markdown component
   */
  public BaseMarkdownComponent(String content) {
    setContent(content);
    this.addContentChangeListener(c->this.content = c);
  }
  
  /**
   * Sets the content of the Markdown component.
   * 
   * @return the content of the Markdown component
   */
  public String getContent() {
    return content;
  }

  /**
   * Gets the content of the Markdown component.
   * 
   * @param content retrieved from the state of the component
   */
  public void setContent(String content) {
    this.content = content;
    setState("content", content);
  }
  
  /**
   * Adds the specified listener for the content change event.
   * 
   * @param listener the listener callback for receiving content changes
   */
  public void addContentChangeListener(SerializableConsumer<String> listener) {
    addStateChangeListener("content", String.class, listener);
  }
  
  /**
   * Sets the color mode of the Markdown component.
   * 
   * @param mode the color mode of the component
   */
  public void setDataColorMode(DataColorMode mode) {
    switch (mode) {
      case DARK:
        getElement().setAttribute("data-color-mode", "dark");
        break;
      case LIGTH:
      case LIGHT:
        getElement().setAttribute("data-color-mode", "light");
        break;
      case AUTO:
        getElement().removeAttribute("data-color-mode");
        break;
    }
  }
  
}
