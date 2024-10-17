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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.markdown.BaseMarkdownComponent.DataColorMode;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Markdown Editor Demo")
@SuppressWarnings("serial")
@Route(value = "markdown-editor/editor-demo", layout = MarkdownDemoView.class)
public class MarkdownEditorDemo extends VerticalLayout {

  public MarkdownEditorDemo() {
    setSizeFull();
    MarkdownEditor mde = new MarkdownEditor();
    mde.setSizeFull();
    mde.setPlaceholder("Enter Markdown here");
    mde.setMaxLength(500);
    mde.setDataColorMode(DataColorMode.LIGTH);
    ComboBox<String> cb = new ComboBox<String>();
    cb.setItems("Dark","Light","Automatic");
    cb.setLabel("Color mode");
    cb.setValue("Light");
    cb.addValueChangeListener(ev->{
      switch(ev.getValue()) {
        case "Dark":
          mde.setDataColorMode(DataColorMode.DARK);
          break;
        case "Light":
          mde.setDataColorMode(DataColorMode.LIGHT);
          break;
        case "Automatic":
          mde.setDataColorMode(DataColorMode.AUTO);
          break;
        default:
          break;
      }
    });
    add(mde,cb);
  }
}
