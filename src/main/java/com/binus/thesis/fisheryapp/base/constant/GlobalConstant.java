package com.binus.thesis.fisheryapp.base.constant;

import com.binus.thesis.fisheryapp.base.dto.BaseInterface;

public interface GlobalConstant extends BaseInterface {

    public static final String _REGEX_ALPHABETH = "[A-Za-z]*";
    public static final String _REGEX_ALPHABETH_SPACE = "[A-Za-z ]*";
    public static final String _REGEX_ALPHANUMERIC = "[0-9A-Za-z]*";
    public static final String _REGEX_ALPHANUMERIC_SPACE = "[0-9A-Za-z ]*";
    public static final String _REGEX_ALPHABET_UPPER = "[A-Z]*";
    public static final String _REGEX_PHONECHAR = "[+0-9]*";
    public static final String _REGEX_NUMBERS = "[0-9]*";
    public static final String _REGEX_ALPHANUMERIC_UPPER = "[0-9A-Z]*";
    public static final String _REGEX_IDCARD = "[\\.\\-0-9]*";
    public static final String _REGEX_PASSPORT = "[\\.\\-0-9A-Z]*";
    public static final String _REGEX_EMAIL="^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String _REGEX_DECIMAL = "^\\d*\\.?\\d*$";

    // UNICODE REGEX
    public static final String _REGEX_ALPHABETH_UNICODE = "[\\p{L}]*";
    public static final String _REGEX_ALPHABETH_SPACE_UNICODE = "[\\p{L} ]*";
    public static final String _REGEX_ALPHANUMERIC_UNICODE = "[\\p{L}\\p{N}]*";
    public static final String _REGEX_ALPHANUMERIC_SPACE_UNICODE = "[\\p{L}\\p{N} ]*";
    public static final String _REGEX_ALPHABET_UPPER_UNICODE = "[\\p{Lu}]*";
    public static final String _REGEX_PHONECHAR_UNICODE = "[+\\p{N}]*";
    public static final String _REGEX_NUMBERS_UNICODE = "[\\p{N}]*";
    public static final String _REGEX_ALPHANUMERIC_UPPER_UNICODE = "[\\p{Lu}\\p{N}]*";
    public static final String _REGEX_IDCARD_UNICODE = "[\\.\\p{Pd}\\p{N}]*";
    public static final String _REGEX_PASSPORT_UNICODE = "[\\.\\p{Pd}\\p{N}\\p{Lu}]*";


    public static String SPACE = " ";
    public static String SYSTEM = "SYSTEM";

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int ALL = -1;

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    /** FORMAT **/

    String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    String FORMAT_DATE = "yyyy-MM-dd";
    String FORMAT_DATE_LONG = "dd MMMM yyyy";
    String TIME_ZONE_AREA = "Asia/Jakarta";
}
