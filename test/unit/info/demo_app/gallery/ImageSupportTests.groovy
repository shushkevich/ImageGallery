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

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin

/**
 * Unit tests for {@link ImageSupport}.
 *
 * @author Sergei Shushkevich
 */
@TestMixin(GrailsUnitTestMixin)
class ImageSupportTests {

    void testIsExtensionSupported() {
        def imageSupport = new ImageSupport(supportedExtensions: ["png"])
        assert imageSupport.isExtensionSupported("png")
        assert imageSupport.isExtensionSupported("Png")
        assert !imageSupport.isExtensionSupported("tiff")
        assert !imageSupport.isExtensionSupported(null)
    }

    void testConvertExtensionToFormat() {
        def imageSupport = new ImageSupport(
                extensionToFormat: [jpg: "JPEG", jpeg: "JPEG", png: "PNG"])
        assert "JPEG" == imageSupport.convertExtensionToFormat("jpg")
        assert "JPEG" == imageSupport.convertExtensionToFormat("jPeG")
        assert "PNG" == imageSupport.convertExtensionToFormat("PNG")
        shouldFail(IllegalArgumentException) {
            imageSupport.convertExtensionToFormat("gif")
        }
        shouldFail(IllegalArgumentException) {
            imageSupport.convertExtensionToFormat(null)
        }
    }
}
