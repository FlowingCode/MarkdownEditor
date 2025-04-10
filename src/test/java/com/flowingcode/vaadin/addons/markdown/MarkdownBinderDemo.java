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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Markdown Editor with Binder")
@SuppressWarnings("serial")
@Route(value = "markdown-editor/binder", layout = MarkdownDemoView.class)
public class MarkdownBinderDemo extends VerticalLayout {

  public MarkdownBinderDemo() {
    setSizeFull();
    Binder<Bean> binder = new Binder<>();
    MarkdownEditor mde = new MarkdownEditor();
    mde.setSizeFull();
    mde.setPlaceholder("Enter Markdown here");
    mde.setMaxLength(500);

    binder.forField(mde).bind(Bean::getText, Bean::setText);
    binder.setBean(new Bean());

    Button getContentButton =
        new Button("Show content", ev -> Notification.show(binder.getBean().getText()));
    Button setSampleContent =
        new Button("Set sample content", ev -> mde.setValue("**Hello** _world_"));
    add(mde, new HorizontalLayout(getContentButton, setSampleContent));
  }

  private static class Bean {
    String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }

}
