<g:if test="${total > params.max}">
    <div class="pagination">
        <g:paginate total="${total}"/>
    </div>
</g:if>
<table class="image-list">
    <g:each in="${imageList}" var="image">
        <tr>
            <td>
                <gallery:thumbnail image="${image}" height="50"/>
            </td>
            <td>
                ${image.title.encodeAsHTML()}
            </td>
            <td>
                <g:link action="delete" id="${image.id}"
                        onclick="return confirm('${message(code: 'image.delete.confirm')}')">
                    <g:message code="common.delete"/>
                </g:link>
            </td>
        </tr>
    </g:each>
</table>
<g:if test="${total > params.max}">
    <div class="pagination">
        <g:paginate total="${total}"/>
    </div>
</g:if>