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
 * Unit tests for {@link Image}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(Image)
class ImageTests {

    void testConstraints() {
        mockForConstraintsTests(Image,
                [new Image(title: "image", uri: "image.jpg", createdBy: "userId")])

        // test nullable & blank constraints
        def image = new Image()
        assert !image.validate()
        assert "nullable" == image.errors.title
        assert "nullable" == image.errors.uri
        assert "nullable" == image.errors.createdBy
        image.title = ""
        image.uri = ""
        image.createdBy = ""
        assert !image.validate()
        assert "blank" == image.errors.title
        assert "blank" == image.errors.uri
        assert "blank" == image.errors.createdBy
        image.title = "i"
        image.uri = "i.jpg"
        image.createdBy = "userId"
        assert image.validate()

        // test maxSize constraint
        image = new Image(title: "t" * 101, description: "d" * 256,
                uri: "u" * 201, createdBy: "c" * 256)
        assert !image.validate()
        assert "maxSize" == image.errors.title
        assert "maxSize" == image.errors.description
        assert "maxSize" == image.errors.uri
        assert "maxSize" == image.errors.createdBy
        image.title = "t" * 100
        image.description = "d" * 255 
        image.uri = "u" * 200
        image.createdBy = "c" * 255
        assert image.validate()

        // test unique constraint
        image = new Image(title: "title", uri: "image.jpg", createdBy: "userId")
        assert !image.validate()
        assert "unique" == image.errors.uri
        image.uri = "image2.jpg"
        assert image.validate()
    }
}
