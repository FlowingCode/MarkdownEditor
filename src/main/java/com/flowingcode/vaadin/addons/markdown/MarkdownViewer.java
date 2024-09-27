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

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.react.ReactAdapterComponent;

/**
 * Component for displaying Markdown text
 */
@SuppressWarnings("serial")
@JsModule("./markdown-viewer.tsx")
@Tag("markdown-viewer")
public class MarkdownViewer extends BaseMarkdownComponent {

  /**
   * Constructor with empty content.
   */
  public MarkdownViewer() {
    super("");
  }
  
  /**
   * Constructor that receives the default content.
   * 
   * @param content default content for the Markdown Viewer
   */
  public MarkdownViewer(String content) {
    super(content);
  }

}
