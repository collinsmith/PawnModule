package org.alliedmodders.pawn;

import org.alliedmodders.pawn.lexer.PawnTokenId;
import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;

@LanguageRegistration(mimeType = "text/x-pawn")
public class PawnLanguage extends DefaultLanguageConfig {
    @Override
    public Language<PawnTokenId> getLexerLanguage() {
	return PawnTokenId.language();
    }
    
    @Override
    public String getDisplayName() {
	return "Pawn";
    }

    /*@Override
    public Parser getParser() {
	return new PawnParser();
    }*/
}
