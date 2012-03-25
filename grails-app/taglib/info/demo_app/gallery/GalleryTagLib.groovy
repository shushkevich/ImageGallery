/*
 * Copyright 2012 Sergei Shushkevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.demo_app.gallery

/**
 * Provides application specific tags.
 *
 * @author Sergei Shushkevich
 */
class GalleryTagLib {

    static namespace = "gallery"

    /**
     * Renders HTML <code>h1</code> tag with "prefix > title" content.
     *
     * @attr prefix if not specified application title will be used
     * @attr title REQUIRED page title
     */
    def pageHeader = {attrs, body ->
        if (!attrs.title) {
            throw new IllegalArgumentException(
                    "[title] attribute must be specified to for <gallery:pageHeader>!")
        }

        out << render(template: "/shared/pageHeader",
                model: [title: attrs.title, prefix: attrs.prefix])
    }

    /**
     * Renders HTML <code>img</code> tag for {@link Image} instance that refers to:
     * <ol>
     *     <li><code>thumbnailUri</code> property if not empty</li>
     *     <li><code>smallImageUri</code> property if not empty</li>
     *     <li><code>uri</code> property otherwise</li>
     * </ol>
     *
     * @attr image {@link Image} instance
     */
    def thumbnail = {attrs, body ->
        def image = attrs.remove("image")
        if (image) {
            attrs.file = image.thumbnailUri ?: image.smallImageUri ?: image.uri
            attrs.dir = grailsApplication.config.gallery.uploadedImages.location
            out << img(attrs)
        }
    }
}
