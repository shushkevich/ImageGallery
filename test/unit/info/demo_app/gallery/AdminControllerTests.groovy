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
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * Unit tests for {@link AdminController}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(AdminController)
@TestMixin(DomainClassUnitTestMixin)
class AdminControllerTests {

    void testList() {
        mockDomain(Image, [
                [title: "image1", uri: "image1.jpg", createdBy: "user"],
                [title: "image2", uri: "image2.jpg", createdBy: "user"],
                [title: "image3", uri: "image3.jpg", createdBy: "user"]
        ])

        def model = controller.list()
        assert 3 == model.imageList.size()
        assert 3 == model.total
    }

    void testDelete() {
        def control = mockFor(ImageService)
        control.demand.delete {Long imageId, File dir -> }
        controller.imageService = control.createMock()

        controller.delete(1)
        assert "/admin/list" == response.redirectedUrl
        control.verify()
    }
}
