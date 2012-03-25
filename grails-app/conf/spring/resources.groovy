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
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import info.demo_app.gallery.ImageSupport

beans = {

    multipartResolver(CommonsMultipartResolver) {
        maxUploadSize = 3 * 1024 * 1024
    }

    imageSupport(ImageSupport) {
        supportedExtensions = ["jpg", "jpeg", "png"]
        supportedFormats = ["JPEG", "PNG"]
        extensionToFormat = [jpg: "JPEG", jpeg: "JPEG", png: "PNG"]
    }
}
