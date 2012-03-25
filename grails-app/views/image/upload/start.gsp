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
        <gallery:pageHeader title="${message(code: 'imageUpload.step1.title')}"
                prefix="${message(code: 'imageUpload.step1.title.prefix')}"/>

        <g:uploadForm action="upload" class="main-content">
            <g:if test="${error}">
                <div class="error">${error.encodeAsHTML()}</div>
            </g:if>
            <g:if test="${flowExecutionException}">
                <div class="error"><g:message code="imageUpload.unexpectedError"/></div>
            </g:if>

            <label for="file"><g:message code="imageUpload.file.label"/></label>
            <input type="file" id="file" name="file" required="required">
            <ul class="form-hints">
                <li>
                    <g:message code="imageUpload.supportedFormats.hint"
                            args="[applicationContext.imageSupport.supportedFormats.join(', ')]"/>
                </li>
                <li>
                    <g:message code="imageUpload.maxSize.hint"
                            args="[applicationContext.multipartResolver.fileUpload.sizeMax / 1024 / 1024]"/>
                </li>
            </ul>

            <div class="space-before">
                <g:submitButton name="next" value="${message(code: 'common.next')}"/>
            </div>
        </g:uploadForm>
    </body>
</html>
