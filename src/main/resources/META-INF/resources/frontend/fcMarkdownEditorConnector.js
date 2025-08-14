/*-
 * #%L
 * Markdown Editor Add-on
 * %%
 * Copyright (C) 2025 Flowing Code
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
(function() {

	window.Vaadin.Flow.fcMarkdownEditorConnector = {
		observeThemeChange: markDownEditor => {
			// Check whether the connector was already initialized for markDownEditor
			if (markDownEditor.$connector) {
				return;
			}

			markDownEditor.$connector = {};

			const supportedTheme = theme => ['light', 'dark'].includes(theme);

			const setDataColorMode = theme => {
				if (supportedTheme(theme)) {
					markDownEditor.setAttribute('data-color-mode', theme);
				} else {
					// force light theme which is Vaadin's default theme
					markDownEditor.setAttribute('data-color-mode', 'light');
				}
			};

			// Get theme from html element when using Vaadin's @Theme annotation
			const mainTheme = document.documentElement.getAttribute('theme');
			if (supportedTheme(mainTheme)) {
				setDataColorMode(mainTheme);
			} else {
				// set light theme by default
				markDownEditor.setAttribute('data-color-mode', 'light');
			}

			// options for the observer (which mutations to observe)
			const config = { attributes: true };

			// callback function to execute when mutations are observed
			const callback = (mutationList, observer) => {
				for (const mutation of mutationList) {
					if (mutation.type === 'attributes' && mutation.attributeName === 'theme') {
						const themeName = mutation.target.getAttribute(mutation.attributeName);
						console.log("theme", themeName);
						setDataColorMode(themeName);
					}
				}
			};

			// create an observer instance linked to the callback function
			markDownEditor.$connector.themeChangeObserver = new MutationObserver(callback);

			// observe html tag for configured mutations
			markDownEditor.$connector.themeChangeObserver.observe(document.documentElement, config);

			// observe body tag for configured mutations
			markDownEditor.$connector.themeChangeObserver.observe(document.body, config);
		},
		unobserveThemeChange: markDownEditor => {
			// stop observing the target node for configured mutations
			if (markDownEditor.$connector.themeChangeObserver) {
				markDownEditor.$connector.themeChangeObserver.disconnect();
				markDownEditor.$connector.themeChangeObserver = null;
			}
		}
	}
})();
