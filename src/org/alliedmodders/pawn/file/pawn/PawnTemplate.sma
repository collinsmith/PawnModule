#include <amxmodx>
#pragma dynamic 256
#if defined _included
    #endinput
#endif

// Single line comment
new const g_szHelloWorld[] = "Hello World";

/**
 * Some documentation with an <i>HTML</i> tag
 *
 * @param id    an identifier
 */
public plugin_init(id) {
    register_plugin("Sample", "1.0.0", "John Smith");
    new iValue = 1;
    new Float:fValue = 1.0;
    new cValue = '1';
}