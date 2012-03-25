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
        <gallery:pageHeader title="${message(code: 'imageUpload.step2.title')}"
                prefix="${message(code: 'imageUpload.step2.title.prefix')}"/>

        <g:form class="main-content">
            <g:hasErrors>
                <div class="errors">
                    <g:renderErrors/>
                </div>
            </g:hasErrors>

            <table>
                <tr>
                    <td>
                        <label for="title"><g:message code="imageUpload.title.label"/></label>
                    </td>
                    <td>
                        <g:textField name="title" value="${title}" required="required"
                                maxlength="100" size="50"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="description"><g:message code="imageUpload.description.label"/></label>
                    </td>
                    <td>
                        <g:textArea name="description" value="${description}"
                                rows="4" cols="50" maxlength="255"/>
                    </td>
                </tr>
            </table>

            <div class="space-before">
                <g:submitButton name="back" value="${message(code: 'common.back')}"/>
                <g:submitButton name="next" value="${message(code: 'common.next')}"/>
            </div>
        </g:form>
    </body>
</html>
