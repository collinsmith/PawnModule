@TemplateRegistrations(value = {
    @TemplateRegistration(
	folder = "Pawn",
	displayName = "#emptyPawnScript_displayName",
	description = "./emptyPawnScript/emptyPawnScript.html",
	content = "./emptyPawnScript/emptyPawnScript.sma",
	scriptEngine = "freemarker"),
    @TemplateRegistration(
	folder = "Pawn",
	displayName = "#pawnScript_displayName",
	description = "./pawnScript/pawnScript.html",
	content = "./pawnScript/pawnScript.sma",
	scriptEngine = "freemarker"),
    @TemplateRegistration(
	folder = "Pawn",
	displayName = "#emptyIncludeFile_displayName",
	description = "./emptyIncludeFile/emptyIncludeFile.html",
	content = "./emptyIncludeFile/emptyIncludeFile.inc",
	scriptEngine = "freemarker"),
    @TemplateRegistration(
	folder = "Pawn",
	displayName = "#includeFile_displayName",
	description = "./includeFile/includeFile.html",
	content = "./includeFile/includeFile.inc",
	scriptEngine = "freemarker"),
    @TemplateRegistration(
	folder = "Pawn",
	displayName = "#templateFile_displayName",
	description = "./templateFile/templateFile.html",
	content = "./templateFile/templateFile.inc",
	scriptEngine = "freemarker"),
})
package org.alliedmodders.pawn.project.sample.file.pawn;

import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.templates.TemplateRegistrations;
