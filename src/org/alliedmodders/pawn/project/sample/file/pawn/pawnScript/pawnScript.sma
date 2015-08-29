<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "../Licenses/license-${project.license}.txt">

#include <amxmodx>
#include <amxmisc>

static const PLUGIN_NAME[] = "${name}";
static const PLUGIN_AUTHOR[] = "${user}";
static const PLUGIN_VERSION[] = "0.0.1";

public plugin_init() {
    register_plugin(PLUGIN_NAME, PLUGIN_VERSION, PLUGIN_AUTHOR);

    //...
}

public plugin_cfg() {
    //...
}

public plugin_precache() {
    //...
}

public plugin_natives() {
    //...
}