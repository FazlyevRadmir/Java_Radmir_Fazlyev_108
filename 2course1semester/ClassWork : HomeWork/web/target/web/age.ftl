<html lang="en">
<#include "base.ftl">

<#macro title>LifeTime</#macro>

<#macro content>
    Hello, ${name}. You're living already:
    <br>
    ${year} years or ${monthTime} months or ${dayTime} days
    <br>
    Converted time:
    <br>
    ${year} years, ${month} months, ${day} days
    <br>
</#macro>

</html>