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

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie
import org.apache.commons.codec.digest.DigestUtils
import javax.servlet.http.HttpSession

/**
 * Provides methods for user authentication.
 *
 * @author Sergei Shushkevich
 */
class AuthenticationService {

    public static final String USER_ID_COOKIE_KEY = "gallery_user_id"

    /**
     * Returns user ID from cookies. If cookie doesn't exist, creates it.
     *
     * @param request
     * @param response
     *
     * @return user ID
     */
    String getUserId(HttpServletRequest request, HttpServletResponse response) {
        def userIdCookie = request.cookies.find {
            it.name == USER_ID_COOKIE_KEY && it.value
        }

        if (!userIdCookie) {
            userIdCookie = new Cookie(USER_ID_COOKIE_KEY, createUserId(request.session))
            userIdCookie.setPath("/")
            userIdCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(userIdCookie)
        }

        return userIdCookie.value
    }

    /**
     * Generates user ID using current session ID and time.
     *
     * @param session user's current HTTP session
     *
     * @return SHA-1 hex string
     */
    protected String createUserId(HttpSession session) {
        DigestUtils.shaHex(session.getId() + System.currentTimeMillis())
    }
}
