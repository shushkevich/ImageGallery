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

import grails.web.JSONBuilder
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang.StringUtils

/**
 * Provides actions for images manipulation (upload, list, delete).
 *
 * @author Sergei Shushkevich
 */
class ImageController {

    static defaultAction = "list"

    def authenticationService
    def imageService
    def imageSupport

    def list(String category) {
        def pathPrefix = "$request.contextPath/$grailsApplication.config.gallery.uploadedImages.location/"
        def images
        if (category) {
            images = Image.byCategory(category).list()
        } else {
            images = Image.list(max: 20)
        }

        def imageData = new JSONBuilder().build {
            array {
                images.each {img ->
                    entry image: "$pathPrefix${img.smallImageUri ?: img.uri}",
                            thumb: "$pathPrefix${img.thumbnailUri ?: img.uri}",
                            link: "$pathPrefix$img.uri", title: img.title,
                            description: img.description ? img.description.encodeAsHTML() : ""
                }
            }
        }

        [imageData: imageData.toString()]
    }

    def myimages() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def userId = authenticationService.getUserId(request, response)
        [imageList: Image.findAllByCreatedBy(userId, params),
                total: Image.countByCreatedBy(userId)]
    }

    def delete(long id) {
        def userId = authenticationService.getUserId(request, response)
        try {
            imageService.delete(id, userId,
                    grailsApplication.config.gallery.uploadedImages.dir(servletContext))
            flash.message = message(code: "image.delete.success", args: [id])
        } catch (IllegalArgumentException e) {
            log.warn "Image ${id} doesn't exist (or doesn't belong to user ${userId})."
            flash.error = message(code: "image.delete.error", args: [id])
        }

        redirect(action: "myimages")
    }

    def uploadFlow = {
        start {
            on("next") {
                MultipartFile file = request.getFile("file")
                if (!file || file.isEmpty()) {
                    flash.error = message(code: "imageUpload.emptyFile.error")
                    return error()
                }

                def extension = FilenameUtils.getExtension(file.originalFilename)
                if (!imageSupport.isExtensionSupported(extension)) {
                    flash.error = message(code: "imageUpload.unsupportedExtension.error", args: [extension])
                    return error()
                }

                [imageBytes: file.getBytes(), extension: extension,
                        title: StringUtils.substring(FilenameUtils.getBaseName(file.originalFilename), 0, 100)]
            }.to "details"
        }
        details {
            on("back").to "start"
            on("next") {ImageUploadCommand cmd ->
                flow.title = cmd.title
                flow.description = cmd.description
                if (!cmd.validate()) {
                    flash.errors = cmd.errors
                    return error()
                }
            }.to "categories"
            on("cancel").to "imageList"
        }
        categories {
            on("back").to "details"
            on("next") {
                flow.categories = params.list("category")
            }.to "processImage"
        }
        processImage {
            action {
                imageService.saveImage(flow.title, flow.description,
                        flow.categories, flow.imageBytes, flow.extension,
                        grailsApplication.config.gallery.uploadedImages.dir(servletContext),
                        authenticationService.getUserId(request, response))
            }
            on("success").to "end"
            on(Exception).to "start"
        }
        end {
            redirect(action: "myimages")
        }
    }
}
