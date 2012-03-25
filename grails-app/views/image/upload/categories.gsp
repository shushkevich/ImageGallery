%{--
  - Copyright 2012 Sergei Shushkevich
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  --}%
<%@ page import="info.demo_app.gallery.ImageCategory" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
    </head>

    <body>
        <gallery:pageHeader title="${message(code: 'imageUpload.step3.title')}"
                prefix="${message(code: 'imageUpload.step3.title.prefix')}"/>

        <g:form class="main-content">
            <g:message code="imageUpload.categories.preface"/>
            <g:each in="${ImageCategory.list()}" var="category">
                <div>
                    <g:checkBox name="category" value="${category.id}"
                            id="cat_${category.id}" checked="false"/>
                    <label for="cat_${category.id}">${category.name.encodeAsHTML()}</label>
                </div>
            </g:each>

            <div class="space-before">
                <g:submitButton name="back" value="${message(code: 'common.back')}"/>
                <g:submitButton name="next" value="${message(code: 'common.next')}"/>
            </div>
        </g:form>
    </body>
</html>
