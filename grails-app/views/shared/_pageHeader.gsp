<h1>
    <span style="color: #777;">
        <g:if test="${prefix}">
            ${prefix.encodeAsHTML()}
        </g:if>
        <g:else>
            <g:message code="application.title"/>
        </g:else>
        &gt;
    </span>
    ${title.encodeAsHTML()}
</h1>