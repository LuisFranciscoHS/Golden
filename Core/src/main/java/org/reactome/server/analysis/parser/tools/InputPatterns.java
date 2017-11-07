package org.reactome.server.analysis.parser.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputPatterns {

    private static final String FLOAT_POINT_NUMBER = "[+-]?([0-9]*[.])?[0-9]+";
    private static final String EXPRESSION_VALUES = "(\\s+" + FLOAT_POINT_NUMBER + ")*\\s*";

    public static final String PROTEIN_UNIPROT = "([OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2})([-]\\d{1,2})?";
    public static final String PROTEIN_ENSEMBL = "ENSP\\d{11}";
    public static final String PROTEOFORM_CUSTOM = "^([OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2})([-]\\d{1,2})?;(\\d{5}:(\\d{1,11}|[Nn][Uu][Ll][Ll]))?(,\\d{5}:(\\d{1,11}|[Nn][Uu][Ll][Ll]))*";
    private static final String PROTEOFORM_PRO = "^UniProtKB:([OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2})[-]?\\d?(,(\\d+-\\d+)?(,([A-Z][a-z]{2}-\\d+(\\/[A-Z][a-z]{2}-\\d+)*,MOD:\\d{5}(\\|[A-Z][a-z]{2}-\\d+(\\/[A-Z][a-z]{2}-\\d+)*,MOD:\\d{5})*)?)?)?\\s*";
    private static final String PROTEOFORM_PIR = "^PR:\\d{9}\\d*\\s*";
    private static final String PROTEOFORM_GPMDB = "(?i)^((([OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2})([-]\\d+)?)|(ENSP\\d{11})):pm.([A-Z]\\d+[+=](([A-Z][a-z]+)|(MOD:\\d{5}))(\\/(([A-Z][a-z]+)|(MOD:\\d{5})))*;)+\\s*";    // GPMDB (http://wiki.thegpm.org/wiki/Nomenclature_for_the_description_of_protein_sequence_modifications)
    private static final String PROTEOFORM_PEFF = ""; //TODO

    private static final Pattern PATTERN_PROTEIN_UNIPROT = Pattern.compile(PROTEIN_UNIPROT);
    private static final Pattern PATTERN_PROTEIN_ENSEMBL = Pattern.compile(PROTEIN_ENSEMBL);
    private static final Pattern PATTERN_PROTEOFORM_CUSTOM = Pattern.compile(PROTEOFORM_CUSTOM);
    private static final Pattern PATTERN_PROTEOFORM_PRO = Pattern.compile(PROTEOFORM_PRO);
    private static final Pattern PATTERN_PROTEOFORM_PIR = Pattern.compile(PROTEOFORM_PIR);
    private static final Pattern PATTERN_PROTEOFORM_GPMDB = Pattern.compile(PROTEOFORM_GPMDB);
    private static final Pattern PATTERN_PROTEOFORM_PEFF = Pattern.compile(PROTEOFORM_PEFF);

    private static final Pattern PATTERN_PROTEIN_UNIPROT_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEIN_UNIPROT + EXPRESSION_VALUES);
    private static final Pattern PATTERN_PROTEIN_ENSEMBL_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEIN_ENSEMBL + EXPRESSION_VALUES);
    private static final Pattern PATTERN_PROTEOFORM_CUSTOM_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEOFORM_CUSTOM + EXPRESSION_VALUES);
    private static final Pattern PATTERN_PROTEOFORM_PRO_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEOFORM_PRO + EXPRESSION_VALUES);
    private static final Pattern PATTERN_PROTEOFORM_PIR_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEOFORM_PIR + EXPRESSION_VALUES);
    private static final Pattern PATTERN_PROTEOFORM_GPMDB_WITH_EXPRESSION_VALUES = Pattern.compile(PROTEOFORM_GPMDB + EXPRESSION_VALUES);

    public static boolean matches_Protein_Uniprot(String str) {
        Matcher m = PATTERN_PROTEIN_UNIPROT.matcher(str);
        return m.matches();
    }

    public static boolean matches_Protein_Ensembl(String str) {
        Matcher m = PATTERN_PROTEIN_ENSEMBL.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Custom(String str) {
        Matcher m = PATTERN_PROTEOFORM_CUSTOM.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Pro(String str) {
        Matcher m = PATTERN_PROTEOFORM_PRO.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Pir(String str) {
        Matcher m = PATTERN_PROTEOFORM_PIR.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Gpmdb(String str) {
        Matcher m = PATTERN_PROTEOFORM_GPMDB.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Peff(String str) {
        Matcher m = PATTERN_PROTEOFORM_PEFF.matcher(str);
        return m.matches();
    }

    public static boolean matches_Protein_Uniprot_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEIN_UNIPROT_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean matches_Protein_Ensembl_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEIN_ENSEMBL_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Custom_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEOFORM_CUSTOM_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Pro_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEOFORM_PRO_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Pir_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEOFORM_PIR_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean matches_Proteoform_Gpmdb_With_Expression_Values(String str) {
        Matcher m = PATTERN_PROTEOFORM_GPMDB_WITH_EXPRESSION_VALUES.matcher(str);
        return m.matches();
    }

    public static boolean isProteoformWithExpression(String str) {
        if (str.matches(PROTEOFORM_CUSTOM + EXPRESSION_VALUES)
                || str.matches(PROTEOFORM_PRO + EXPRESSION_VALUES)
                || str.matches(PROTEOFORM_GPMDB + EXPRESSION_VALUES)
                || str.matches(PROTEOFORM_PIR + EXPRESSION_VALUES)) {
            return true;
        }
        return false;
    }
}
