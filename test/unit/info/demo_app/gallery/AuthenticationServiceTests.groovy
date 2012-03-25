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
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockHttpSession

/**
 * Unit tests for {@link AuthenticationService}.
 *
 * @author Sergei Shushkevich
 */
@TestFor(AuthenticationService)
class AuthenticationServiceTests {

    void testGetUserId() {
        def request = new MockHttpServletRequest()
        def response = new MockHttpServletResponse()

        service.getUserId(request, response)
        def cookie = response.getCookie(AuthenticationService.USER_ID_COOKIE_KEY)
        assert cookie != null
        assert cookie.value != null
        assert cookie.path == "/"
        assert cookie.maxAge == 60 * 60 * 24 * 365
    }

    void testCreateUserId() {
        def id = service.createUserId(new MockHttpSession())
        assert id != null
        assert id.size() == 40
    }
}
