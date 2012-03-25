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

import grails.test.mixin.TestFor

/**
 * Unit tests for {@link GalleryTagLib}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(GalleryTagLib)
class GalleryTagLibTests {

    void testPageHeader() {
        assertOutputMatches(/(?s)<h1>.*Title.*<\/h1>/,
                '<gallery:pageHeader title="Title"/>')
        assertOutputMatches(/(?s)<h1>.*Prefix.*Title.*<\/h1>/,
                '<gallery:pageHeader title="Title" prefix="Prefix"/>')
        shouldFail {
            applyTemplate('<gallery:pageHeader/>')
        }
    }

    void testThumbnail() {
        assertOutputEquals('', '<gallery:thumbnail image="${image}"/>',
                [image: null])
        assertOutputEquals('<img src="/upload/thumb.jpg"  />', '<gallery:thumbnail image="${image}"/>',
                [image: new Image(thumbnailUri: "thumb.jpg")])
        assertOutputEquals('<img src="/upload/small.jpg"  />', '<gallery:thumbnail image="${image}"/>',
                [image: new Image(smallImageUri: "small.jpg")])
        assertOutputEquals('<img src="/upload/big.jpg"  />', '<gallery:thumbnail image="${image}"/>',
                [image: new Image(uri: "big.jpg")])
    }
}
