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
<g:set var="menuItem" value="${pageProperty(name: 'page.menuItem')}"/>
<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="${message(code: 'application.title')}"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${resource(dir: '/images', file: 'favicon.ico')}" type="image/x-icon">
        <g:layoutHead/>
        <r:require module="application"/>
        <r:layoutResources/>
    </head>

    <body>
        <div class="menu">
            <div class="content">
                <ul>
                    <li class="${menuItem == 'home' ? 'active' : ''}"
                            ><link:home><span class="border">&#160;</span><span class="text"><g:message code="menu.home"/></span></link:home></li
                    ><g:each in="${ImageCategory.list()}" var="category"
                            ><li class="${menuItem == category.name ? 'active' : ''}"><g:link controller="image" action="list" params="[category: category.name]"
                            ><span class="border">&#160;</span><span class="text">${category.name}</span></g:link></li></g:each>
                </ul>
            </div>
        </div>

        <div class="content">
            <g:layoutBody/>
        </div>

        <div class="footer">
            <div class="content">
                <a href="https://github.com/shushkevich/ImageGallery" target="_blank"
                        ><g:message code="application.title"/></a>.
                <g:message code="application.copyright"/>
            </div>
        </div>

        <r:layoutResources/>
    </body>
</html>