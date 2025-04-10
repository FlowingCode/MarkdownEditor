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
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import java.util.List;

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
    Button getContentButton = new Button("Show content",ev->Notification.show(mde.getValue()));
    Button setSampleContent = new Button("Set sample content",ev->{
      mde.setValue("""
# Markdown Editor Demo

## This is a heading

*This is a list item*

[Link to Vaadin](https://vaadin.com)

```java
// Sample Java code
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}
```

> This is a blockquote

**This is bold text**

_This is italic text_

~~This is strikethrough text~~
      """);
    });
    add(mde,new HorizontalLayout(getContentButton,setSampleContent));
  }
}
