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

import com.flowingcode.vaadin.addons.markdown.BaseMarkdownComponent.DataColorMode;
import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.HasSize;

/**
 * Markdown component that allows editing the contents.
 */
@SuppressWarnings("serial")
public class MarkdownEditor
    extends AbstractCompositeField<MarkdownEditorComponent, MarkdownEditor, String>
    implements HasSize {

  /**
   * Constructor with empty content.
   */
  public MarkdownEditor() {
    this("");
  }

  /**
   * Constructor with initial value.
   *
   * @param initialValue the initial content for the Markdown editor
   */
  public MarkdownEditor(String initialValue) {
    super("");
    getEditor().addContentChangeListener(newValue -> setModelValue(newValue, true));
    getEditor().setContent(initialValue);
  }


  @Override
  protected MarkdownEditorComponent initContent() {
    return new MarkdownEditorComponent();
  }

  /**
   * Returns the editor component.
   *
   * @return the editor component
   */
  protected final MarkdownEditorComponent getEditor() {
    return getContent();
  }

  /**
   * Returns the placeholder text for the Markdown editor.
   *
   * @return the placeholder text
   */
  public String getPlaceholder() {
    return getEditor().getPlaceholder();
  }

  /**
   * Sets the placeholder text for the Markdown editor.
   *
   * @param placeholder the placeholder text
   */
  public void setPlaceholder(String placeholder) {
    getEditor().setPlaceholder(placeholder);
  }

  /**
   * Returns the configured maximum character count for the Markdown editor.
   *
   * @return the configured maximum character count
   */
  public int getMaxLength() {
    return getEditor().getMaxLength();
  }

  /**
   * Sets the maximum character count for the Markdown editor.
   *
   * @param maxLength the maximum character count
   */
  public void setMaxLength(int maxLength) {
    getEditor().setMaxLength(maxLength);
  }

  /**
   * Sets the color mode of the Markdown component.
   *
   * @param mode the color mode of the component
   */
  public void setDataColorMode(DataColorMode mode) {
    getEditor().setDataColorMode(mode);
  }

  @Override
  protected void setPresentationValue(String newPresentationValue) {
    getEditor().setContent(newPresentationValue);
  }

}
