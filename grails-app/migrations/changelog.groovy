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
databaseChangeLog = {

    changeSet(id: "1", author: "shushkevich") {
        createTable(tableName: "category") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoryPK")
            }
            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }
            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(id: "2", author: "shushkevich") {
        createTable(tableName: "image") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "imagePK")
            }
            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }
            column(name: "created_by", type: "varchar(255)") {
                constraints(nullable: "false")
            }
            column(name: "title", type: "varchar(100)") {
                constraints(nullable: "false")
            }
            column(name: "uri", type: "varchar(200)") {
                constraints(nullable: "false", unique: "true")
            }
            column(name: "small_image_uri", type: "varchar(255)")
            column(name: "thumbnail_uri", type: "varchar(255)")
            column(name: "description", type: "varchar(255)")
            column(name: "date_created", type: "timestamp")
            column(name: "last_updated", type: "timestamp")
        }
    }

    changeSet(id: "3", author: "shushkevich") {
        createTable(tableName: "image_to_category") {
            column(name: "image_id", type: "bigint") {
                constraints(nullable: "false")
            }
            column(name: "category_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(id: "4", author: "shushkevich") {
        addPrimaryKey(columnNames: "image_id, category_id", tableName: "image_to_category")
    }

    changeSet(id: "5", author: "shushkevich") {
        createIndex(indexName: "FK6DA5D15E9E0F5AF5", tableName: "image_to_category") {
            column(name: "image_id")
        }
    }

    changeSet(id: "6", author: "shushkevich") {
        addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "image_to_category",
                constraintName: "FK6DA5D15E9E0F5AF5", deferrable: "false", initiallyDeferred: "false",
                referencedColumnNames: "id", referencedTableName: "image", referencesUniqueColumn: "false")
    }

    changeSet(id: "7", author: "shushkevich") {
        createIndex(indexName: "FK6DA5D15EBC48CB90", tableName: "image_to_category") {
            column(name: "category_id")
        }
    }

    changeSet(id: "8", author: "shushkevich") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "image_to_category",
                constraintName: "FK6DA5D15EBC48CB90", deferrable: "false", initiallyDeferred: "false",
                referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")
    }
}
