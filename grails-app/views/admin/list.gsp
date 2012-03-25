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
    </head>

    <body>
        <gallery:pageHeader title="${message(code: 'admin.imageList.title')}"/>

        <div class="main-content">
            <g:if test="${flash.error}">
                <div class="error">${flash.error.encodeAsHTML()}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message.encodeAsHTML()}</div>
            </g:if>

            <g:if test="${imageList}">
                <g:render template="/shared/imageList"/>
            </g:if>
            <g:else>
                <g:message code="admin.imageList.empty"/>
            </g:else>
        </div>
    </body>
</html>
