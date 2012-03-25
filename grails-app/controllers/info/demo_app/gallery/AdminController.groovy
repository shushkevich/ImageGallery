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
 * Provides actions for gallery management.
 *
 * @author Sergei Shushkevich
 */
class AdminController {

    def imageService

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [imageList: Image.list(params), total: Image.count()]
    }

    def delete(long id) {
        try {
            imageService.delete(id, grailsApplication.config.gallery.uploadedImages.dir(servletContext))
            flash.message = message(code: "image.delete.success", args: [id])
        } catch (IllegalArgumentException e) {
            log.warn "Image ${id} doesn't exist."
            flash.error = message(code: "image.delete.error", args: [id])
        }

        redirect(action: "list")
    }
}
