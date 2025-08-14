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

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
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
@JsModule("./fcMarkdownEditorConnector.js")
public class BaseMarkdownComponent extends ReactAdapterComponent implements HasSize {
  
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
  
  private void runBeforeClientResponse(SerializableConsumer<UI> command) {
    getElement().getNode().runWhenAttached(ui -> ui
        .beforeClientResponse(this, context -> command.accept(ui)));
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);

    runBeforeClientResponse(ui -> ui.getPage().executeJs(
        "window.Vaadin.Flow.fcMarkdownEditorConnector.observeThemeChange($0)",
        getElement()));
  }

  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);

    getUI().ifPresent(ui -> ui.getPage().executeJs(
        "window.Vaadin.Flow.fcMarkdownEditorConnector.unobserveThemeChange($0)",
        this.getElement()));
  }

}
