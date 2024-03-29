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
import org.apache.commons.fileupload.FileUploadBase

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        name home: "/"(controller: "image", action: "list")

        "/images/$category?"(controller: "image", action: "list")

        "/myimages"(controller: "image", action: "myimages")

        "500"(view: "/uploadSizeLimitError", exception: FileUploadBase.SizeLimitExceededException)
        "500"(view: '/error')
    }
}
