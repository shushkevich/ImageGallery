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

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * Unit tests for {@link ImageController}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(ImageController)
@Mock([Image, ImageCategory])
class ImageControllerTests {

    void testList() {
        def model = controller.list(null)
        assert "[]" == model.imageData

        new Image(title: "image1", uri: "image1.jpg", createdBy: "user",
                description: "image1 desc", smallImageUri: "image1_small.jpg",
                thumbnailUri: "image1_thumb.jpg",).save()
        model = controller.list(null)
        def pathPrefix = "/${grailsApplication.config.gallery.uploadedImages.location}/"
        assert model.imageData == """[{"image":"${pathPrefix}image1_small.jpg",\
"thumb":"${pathPrefix}image1_thumb.jpg","link":"${pathPrefix}image1.jpg",\
"title":"image1","description":"image1 desc"}]"""
    }

    void testMyimages() {
        [new Image(title: "image1", uri: "image1.jpg", createdBy: "user1"),
                new Image(title: "image2", uri: "image2.jpg", createdBy: "user2")]*.save()

        def control = mockFor(AuthenticationService)
        control.demand.getUserId {request, response -> "user1"}
        controller.authenticationService = control.createMock()
        def model = controller.myimages()
        assert 1 == model.imageList.size()
        assert 1 == model.total
        control.verify()

        control.demand.getUserId {request, response -> "unknown"}
        controller.authenticationService = control.createMock()
        model = controller.myimages()
        assert 0 == model.imageList.size()
        assert 0 == model.total
        control.verify()
    }
    
    void testDelete() {
        def authenticationServiceControl = mockFor(AuthenticationService)
        authenticationServiceControl.demand.getUserId {request, response -> "user"}
        controller.authenticationService = authenticationServiceControl.createMock()
        def imageServiceControl = mockFor(ImageService)
        imageServiceControl.demand.delete {Long imageId, String userId, File dir -> }
        controller.imageService = imageServiceControl.createMock()

        controller.delete(1)
        assert "/image/myimages" == response.redirectedUrl
        authenticationServiceControl.verify()
        imageServiceControl.verify()
    }
}
