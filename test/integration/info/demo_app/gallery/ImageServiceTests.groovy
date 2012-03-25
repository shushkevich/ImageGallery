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
 * Integration tests for {@link ImageService}.
 *
 * @author Sergei Shushkevich
 */
class ImageServiceTests extends GroovyTestCase {

    def imageService

    void testSaveImage() {
        def cat1 = new ImageCategory(name: "category1").save(flush: true)
        def cat2 = new ImageCategory(name: "category2").save(flush: true)
        def tempdir = new File(System.getProperty("java.io.tmpdir"))

        imageService.saveImage("image1", "description1", [cat1.id, cat2.id],
                this.getClass().getResourceAsStream("image1.jpg").bytes,
                "jpg", tempdir, "user1")
        assert 1 == Image.count()
        def image = Image.list()[0]
        assert "image1" == image.title
        assert "description1" == image.description
        assert "user1" == image.createdBy
        assert image.uri != null
        assert image.smallImageUri != null
        assert image.thumbnailUri != null
        def imageCategories = image.categories
        assert 2 == imageCategories.size()
        assert imageCategories.every {[cat1, cat2].contains(it)}
        def imageFiles = [new File(tempdir, image.uri), new File(tempdir, image.smallImageUri),
                new File(tempdir, image.thumbnailUri)]
        assert imageFiles.every {it.exists()}
        imageFiles.each {it.delete()}
    }

    void testDelete() {
        def file = File.createTempFile("image-", ".jpg")
        def image = new Image(createdBy: "user", title: "image", uri: file.name).save(flush: true)

        shouldFail(IllegalArgumentException) {
            imageService.delete(image.id, "unknown-user", file.parentFile)
        }

        imageService.delete(image.id, "user", file.parentFile)
        assert 0 == Image.count()
        assert !file.exists()

        file = File.createTempFile("image-", ".jpg")
        image = new Image(createdBy: "user", title: "image", uri: file.name).save(flush: true)
        imageService.delete(image.id, file.parentFile)
        assert 0 == Image.count()
        assert !file.exists()
    }

    void testDeleteImage() {
        def file1 = File.createTempFile("image-", ".jpg")
        def file2 = File.createTempFile("image-", ".jpg")
        def file3 = File.createTempFile("image-", ".jpg")
        def image = new Image(createdBy: "user1", title: "image1", uri: file1.name,
                smallImageUri: file2.name, thumbnailUri: file3.name).save(flush: true)

        imageService.deleteImage(image, file1.parentFile)
        assert 0 == Image.count()
        assert !file1.exists()
        assert !file2.exists()
        assert !file3.exists()
    }
}
