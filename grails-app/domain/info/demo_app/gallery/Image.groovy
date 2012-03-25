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
 * Image domain class.
 *
 * @author Sergei Shushkevich
 */
class Image implements Serializable {

    String title
    String description
    String uri
    String thumbnailUri
    String smallImageUri
    Date dateCreated
    Date lastUpdated
    String createdBy

    static hasMany = [categories: ImageCategory]

    static constraints = {
        title blank: false, maxSize: 100
        description nullable: true, maxSize: 255
        uri blank: false, maxSize: 200, unique: true
        thumbnailUri nullable: true, blank: false, maxSize: 255
        smallImageUri nullable: true, blank: false, maxSize: 255
        dateCreated nullable: true
        lastUpdated nullable: true
        createdBy blank: false, maxSize: 255
    }

    static mapping = {
        categories joinTable: [name: "image_to_category", key: "image_id"]
        sort dateCreated: "desc"
    }

    static namedQueries = {
        byCategory {categoryName ->
            categories {
                eq "name", categoryName
            }
            order "dateCreated", "desc"
        }
    }
}
