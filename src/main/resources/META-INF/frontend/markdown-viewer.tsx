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
import {type ReactElement} from 'react';
import {
  useState,
  useRef,
  Fragment,
  useEffect,
  useCallback
} from "react";


import MDEditor from "@uiw/react-md-editor";
import {ReactAdapterElement, type RenderHooks, type Root} from "Frontend/generated/flow/ReactAdapter";
import React from 'react';
import "@uiw/react-md-editor/markdown-editor.css";
import "@uiw/react-markdown-preview/markdown.css";
import rehypeSanitize from "rehype-sanitize";
import mermaid from "mermaid";
import { getCodeString } from "rehype-rewrite";

const randomid = () => parseInt(String(Math.random() * 1e15), 10).toString(36);
const Code = ({ inline, children = [], className, ...props }) => {
  const demoid = useRef(`dome${randomid()}`);
  const [container, setContainer] = useState(null);
  const isMermaid =
    className && /^language-mermaid/.test(className.toLocaleLowerCase());
  const code = children
    ? getCodeString(props.node.children)
    : children[0] || "";

  useEffect(() => {
    let isMounted = true;
    if (container && isMermaid && code) {
      mermaid
        .render(demoid.current, code)
        .then(({ svg, bindFunctions }) => {
          if (isMounted) {
            container.innerHTML = svg;
            if (bindFunctions) {
              bindFunctions(container);
            }
          }
        })
        .catch((error) => {
          if (isMounted) {
            container.innerHTML = "Mermaid render error";
          }
          console.log("error:", error);
        });
    }

    return () => {
      isMounted = false;
      if (container) {
        container.innerHTML = "";
      }
    };
  }, [container, isMermaid, code, demoid]);

  const refElement = useCallback((node) => {
    if (node !== null) {
      setContainer(node);
    }
  }, []);

  if (isMermaid) {
    return (
      <Fragment>
        <code id={demoid.current} style={{ display: "none" }} />
        <code className={className} ref={refElement} data-name="mermaid" />
      </Fragment>
    );
  }
  return <code className={className}>{children}</code>;
};


class MarkdownViewerElement extends ReactAdapterElement {
    private root: Root | null = null;
    private isInitialized: boolean = false;

    public override connectedCallback() {
        if (!this.isInitialized) {
            super.connectedCallback(); 
            this.root = this.reactRoot; 
            this.isInitialized = true;
        } else {
            this.update();
        }
    }

    public override disconnectedCallback() {
        if (this.root) {
            this.root.render(null);
        }
    }

    protected override update() {
        if (this.root) {
            this.root.render(this.render(this.createRenderHooks()));
        }
    }

    protected override render(hooks: RenderHooks): ReactElement | null {
      const [content] = hooks.useState<string>("content"); 
      
      return <MDEditor.Markdown key={content} source={content}
                              components={{
                                code: Code
                            }} />;
    }
}
  
customElements.define("markdown-viewer",MarkdownViewerElement);