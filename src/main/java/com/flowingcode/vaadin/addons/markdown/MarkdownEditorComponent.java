/*-
 * #%L
 * Markdown Editor Add-on
 * %%
 * Copyright (C) 2024-2025 Flowing Code
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

/**
 * React adapter for markdown-editor.
 */
@SuppressWarnings("serial")
@NpmPackage(value = "rehype-sanitize", version = "6.0.0")
@JsModule("./markdown-editor.tsx")
@Tag("markdown-editor")
class MarkdownEditorComponent extends BaseMarkdownComponent {

  /**
   * Constructor with empty content.
   */
  public MarkdownEditorComponent() {
    super("");
  }

  /**
   * Constructor with default content.
   *
   * @param content default content for the Markdown editor
   */
  public MarkdownEditorComponent(String content) {
    super(content);
  }

  /**
   * Returns the placeholder text for the Markdown editor.
   *
   * @return the placeholder text
   */
  public String getPlaceholder() {
    return getState("placeholder", String.class);
  }

  /**
   * Sets the placeholder text for the Markdown editor.
   *
   * @param placeholder the placeholder text
   */
  public void setPlaceholder(String placeholder) {
    setState("placeholder", placeholder);
  }

  /**
   * Returns the configured maximum character count for the Markdown editor.
   *
   * @return the configured maximum character count
   */
  public int getMaxLength() {
    return getState("maxLength", Integer.class);
  }

  /**
   * Sets the maximum character count for the Markdown editor.
   *
   * @param maxlength the maximum character count
   */
  public void setMaxLength(int maxlength) {
    setState("maxLength", maxlength);
  }

}
