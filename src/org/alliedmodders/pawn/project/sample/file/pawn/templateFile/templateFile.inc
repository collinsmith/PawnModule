<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "../Licenses/license-${project.license}.txt">

#if defined _${name}_included
    #endinput
#endif
#define _${name}_included

/*******************************************************************************
 * ${name} template
 ******************************************************************************/

enum ${name} {
    //...
};