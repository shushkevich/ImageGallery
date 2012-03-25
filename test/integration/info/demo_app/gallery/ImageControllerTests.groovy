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

import org.junit.Test

/**
 * Integration tests for {@link ImageController}.
 *
 * @author Sergei Shushkevich
 */
class ImageControllerTests {

    def grailsApplication

    @Test
    void testList() {
        new Image(title: "image1", uri: "image1.jpg", createdBy: "user",
                description: "image1 desc", smallImageUri: "image1_small.jpg",
                thumbnailUri: "image1_thumb.jpg",)
                .addToCategories(new ImageCategory(name: "Nature"))
                .save(flush: true)
        new Image(title: "image2", uri: "image2.jpg", createdBy: "user",
                description: "image2 desc", smallImageUri: "image2_small.jpg",
                thumbnailUri: "image2_thumb.jpg",)
                .addToCategories(new ImageCategory(name: "People"))
                .save(flush: true)
        def pathPrefix = "/${grailsApplication.config.gallery.uploadedImages.location}/"

        // list images restricted by category
        def controller = new ImageController()
        def model = controller.list("People")
        assert model.imageData == """[{"image":"${pathPrefix}image2_small.jpg",\
"thumb":"${pathPrefix}image2_thumb.jpg","link":"${pathPrefix}image2.jpg",\
"title":"image2","description":"image2 desc"}]"""
    }
}
