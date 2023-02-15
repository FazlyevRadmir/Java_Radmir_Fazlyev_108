<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    Hello,
    <br>
    <#list users as u>
        ${u.firstName} ${u.lastName}
        <br>
    </#list>!
</#macro>

</html>