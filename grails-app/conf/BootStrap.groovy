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
import info.demo_app.gallery.Image
import info.demo_app.gallery.ImageCategory

class BootStrap {

    def grailsApplication

    def init = {servletContext ->
        if (grailsApplication.config.gallery.demodata.populate) {
            def animalsCategory = new ImageCategory(name: "Animals").save()
            def architectureCategory = new ImageCategory(name: "Architecture").save()
            def foodCategory = new ImageCategory(name: "Food").save()
            def natureCategory = new ImageCategory(name: "Nature").save()
            def peopleCategory = new ImageCategory(name: "People").save()

            new Image(title: "Lion Cub", uri: "default/LionCub.jpg", createdBy: "userId",
                    smallImageUri: "default/LionCub_small.jpg", thumbnailUri: "default/LionCub_thumb.jpg",
                    description: "http://s3.amazonaws.com/everystockphoto/fspid20/11/34/19/2/africa-e510-olympus-1134192-o.jpg")
                    .addToCategories(animalsCategory)
                    .save()
            new Image(title: "Cherry Pie", uri: "default/CherryPie.jpg", createdBy: "userId",
                    smallImageUri: "default/CherryPie_small.jpg", thumbnailUri: "default/CherryPie_thumb.jpg",
                    description: "http://everystockphoto.s3.amazonaws.com/cherry_sweet_bake_752604_o.jpg")
                    .addToCategories(foodCategory)
                    .save()
            new Image(title: "Croissandwich", uri: "default/Croissandwich.jpg", createdBy: "userId",
                    smallImageUri: "default/Croissandwich_small.jpg", thumbnailUri: "default/Croissandwich_thumb.jpg",
                    description: "http://s3.amazonaws.com/estock/fspid5/135400/food-croissant-sandwich-135437-o.jpg")
                    .addToCategories(foodCategory)
                    .save()
            new Image(title: "DSC02411", uri: "default/DSC02411.jpg", createdBy: "userId",
                    smallImageUri: "default/DSC02411_small.jpg", thumbnailUri: "default/DSC02411_thumb.jpg",
                    description: "http://everystockphoto.s3.amazonaws.com/blackcomb_whistler_canada_93946_o.jpg")
                    .addToCategories(peopleCategory)
                    .save()
            new Image(title: "Pool at city hall", uri: "default/PoolAtCityHall.jpg", createdBy: "userId",
                    smallImageUri: "default/PoolAtCityHall_small.jpg", thumbnailUri: "default/PoolAtCityHall_thumb.jpg",
                    description: "http://everystockphoto.s3.amazonaws.com/toronto_canada_sookie_14859_o.jpg")
                    .addToCategories(architectureCategory)
                    .save()
            new Image(title: "Good morning, Santorini!", uri: "default/Santorini.jpg", createdBy: "userId",
                    smallImageUri: "default/Santorini_small.jpg", thumbnailUri: "default/Santorini_thumb.jpg",
                    description: "http://s3.amazonaws.com/estock/fspid4/8200/santorini-caldera-sunset-8253-o.jpg")
                    .addToCategories(architectureCategory)
                    .addToCategories(natureCategory)
                    .save()
            new Image(title: "Schwetzingen Castle Mosque", uri: "default/SchwetzingenCastleMosque.jpg", createdBy: "userId",
                    smallImageUri: "default/SchwetzingenCastleMosque_small.jpg", thumbnailUri: "default/SchwetzingenCastleMosque_thumb.jpg",
                    description: "http://s3.amazonaws.com/estock_dev/fspid11/51/87/51/schloss-building-tower-518751-o.jpg")
                    .addToCategories(architectureCategory)
                    .save()
            new Image(title: "The Harijan Village", uri: "default/TheHarijanVillage.jpg", createdBy: "userId",
                    smallImageUri: "default/TheHarijanVillage_small.jpg", thumbnailUri: "default/TheHarijanVillage_thumb.jpg",
                    description: "http://s3.amazonaws.com/estock/fspid11/84/02/93/iran-harijan-nature-840293-o.jpg")
                    .addToCategories(architectureCategory)
                    .addToCategories(natureCategory)
                    .save()
            new Image(title: "Modernist Pre-Fab", uri: "default/ModernistPreFab.jpg", createdBy: "userId",
                    smallImageUri: "default/ModernistPreFab_small.jpg", thumbnailUri: "default/ModernistPreFab_thumb.jpg",
                    description: "http://everystockphoto.s3.amazonaws.com/california_Californie_Architects_32265_o.jpg")
                    .addToCategories(architectureCategory)
                    .save()
        }
    }

    def destroy = {
    }
}
