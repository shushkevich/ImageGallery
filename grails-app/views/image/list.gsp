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
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <r:require module="galleria"/>
        <r:script>
            $(function() {
                Galleria.loadTheme("${resource(dir: 'js/galleria/themes/custom', file: 'galleria.custom.js')}");
                $("#galleria").galleria({dataSource: ${imageData}});
            });
        </r:script>
    </head>

    <body>
        <gallery:pageHeader title="${params.category ?: message(code: 'menu.home')}"/>

        <div id="galleria" style="height: 400px;"></div>

        <div class="space-before">
            <g:link action="upload" title="${message(code: 'common.uploadImage.link.title')}">
                <g:img dir="images" file="image-upload.png" width="32" height="32"/>
                <g:message code="common.uploadImage.link"/>
            </g:link>
            <g:link action="myimages" title="${message(code: 'common.myImages.link.title')}">
                <g:img dir="images" file="images-my.png" width="32" height="32"/>
                <g:message code="common.myImages.link"/>
            </g:link>
        </div>

        <content tag="menuItem">${params.category ?: 'home'}</content>
    </body>
</html>
