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

import cr.co.arquetipos.ImageTool

/**
 * Provides methods for {@link Image} manipulation.
 *
 * @author Sergei Shushkevich
 */
class ImageService {

    def imageSupport

    /**
     * Stores image file inside <code>targetDir</code>. Additionally creates image thumbnails.
     * Saves image details (title, description, etc.) in DB.
     *
     * @param title image title
     * @param description image description
     * @param categoryIds list of category IDs to assign image to
     * @param imageBytes byte data of image file
     * @param imageExtension image file extension
     * @param targetDir directory where image and thumbnails should be stored
     * @param userId owner of the image
     */
    void saveImage(String title, String description, List categoryIds, byte[] imageBytes,
            String imageExtension, File targetDir, String userId) {
        def imageTool = new ImageTool()
        imageTool.load(imageBytes)
        def imageType = imageSupport.convertExtensionToFormat(imageExtension)

        def originalImage = File.createTempFile("image-", ".$imageExtension", targetDir)
        imageTool.thumbnail(Float.MAX_VALUE)
        imageTool.writeResult(originalImage.absolutePath, imageType)
        
        def smallImage = File.createTempFile("image_small-", ".$imageExtension", targetDir)
        imageTool.thumbnailSpecial(imageTool.getWidth(), 350, 2, 1)
        imageTool.writeResult(smallImage.absolutePath, imageType)

        def thumbnail = File.createTempFile("image_thumb-", ".$imageExtension", targetDir)
        imageTool.thumbnailSpecial(imageTool.getWidth(), 50, 2, 2)
        imageTool.writeResult(thumbnail.absolutePath, imageType)

        def image = new Image(title: title, description: description, uri: originalImage.name,
                smallImageUri: smallImage.name, thumbnailUri: thumbnail.name, createdBy: userId)
        if (categoryIds) {
            ImageCategory.getAll(categoryIds).each {
                image.addToCategories(it)
            }
        }
        image.save()
    }

    /**
     * Deletes {@link Image} with ID specified by <code>imageId</code> parameter
     * taking into account image owner. Image file(s) will be also deleted.
     *
     * @param imageId image ID
     * @param userId ID of image owner
     * @param dir directory where image file(s) is stored
     *
     * @throws IllegalArgumentException in case when specified user doesn't own the image
     *         or image has already been deleted.
     */
    void delete(Long imageId, String userId, File dir) {
        def image = Image.findByIdAndCreatedBy(imageId, userId)
        if (image) {
            deleteImage(image, dir)
        } else {
            throw new IllegalArgumentException("Image with ID $imageId doesn't exist.")
        }
    }

    /**
     * Deletes {@link Image} with ID specified by <code>imageId</code> parameter.
     * Image file(s) will be also deleted.
     *
     * @param imageId image ID
     * @param dir directory where image file(s) is stored
     *
     * @throws IllegalArgumentException if image has already been deleted.
     */
    void delete(Long imageId, File dir) {
        def image = Image.get(imageId)
        if (image) {
            deleteImage(image, dir)
        } else {
            throw new IllegalArgumentException("Image with ID $imageId doesn't exist.")
        }
    }

    /**
     * Deletes specified {@link Image} and corresponding file(s).
     *
     * @param image {@link Image} instance
     * @param dir directory where image file(s) is stored
     */
    void deleteImage(Image image, File dir) {
        image.delete(flush: true)

        if (image.thumbnailUri) {
            new File(dir, image.thumbnailUri).delete()
        }
        if (image.smallImageUri) {
            new File(dir, image.smallImageUri).delete()
        }
        new File(dir, image.uri).delete()
    }
}
