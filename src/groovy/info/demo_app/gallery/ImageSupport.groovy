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
 * Helper class that provides information about supported image formats.
 *
 * @author Sergei Shushkevich
 */
class ImageSupport {

    List supportedExtensions
    List supportedFormats
    Map extensionToFormat

    /**
     * Returns <code>true</code> if images with specified extension are supported
     * (case insensitive).
     *
     * @param extension image file extension
     *
     * @return <code>true</code> if supported, <code>false</code> otherwise
     */
    Boolean isExtensionSupported(String extension) {
        supportedExtensions.contains(extension?.toLowerCase())
    }

    /**
     * Converts file extension to image format, e.g. "jpg" -> "JPEG".
     *
     * @param extension file extension
     *
     * @return image format string
     *
     * @throws IllegalArgumentException if specified extension is not supported.
     */
    String convertExtensionToFormat(String extension) {
        def format = extensionToFormat[extension?.toLowerCase()]
        if (!format) {
            throw new IllegalArgumentException("Extension $extension doesn't supported.")
        }
        format
    }
}
