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
 * Unit tests for {@link ImageCategory}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(ImageCategory)
class ImageCategoryTests {

    void testConstraints() {
        mockForConstraintsTests(ImageCategory, [new ImageCategory(name: "test")])

        // test nullable & blank contraints
        def category = new ImageCategory()
        assert !category.validate()
        assert "nullable" == category.errors.name
        category.name = ""
        assert !category.validate()
        assert "blank" == category.errors.name
        category.name = "a"
        assert category.validate()

        // test maxSize constraint
        category.name = "a" * 51
        assert !category.validate()
        assert "maxSize" == category.errors.name
        category.name = "a" * 50
        assert category.validate()

        // test unique constraint
        category.name = "test"
        assert !category.validate()
    }
}
