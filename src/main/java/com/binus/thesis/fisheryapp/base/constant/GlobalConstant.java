package com.binus.thesis.fisheryapp.base.constant;

import com.binus.thesis.fisheryapp.base.dto.BaseInterface;

import java.util.ArrayList;
import java.util.List;

public interface GlobalConstant extends BaseInterface {

    public static String[] LIST_KECAMATAN = {
            "ARU SELATAN",
            "ARU SELATAN TIMUR",
            "ARU SELATAN UTARA",
            "ARU TENGAH",
            "ARU TENGAH TIMUR",
            "ARU TENGAH SELATAN",
            "PULAU-PULAU ARU",
            "ARU UTARA",
            "ARU UTARA TIMUR BATULEY",
            "SIR-SIR"
    };

    public static final int ACTIVE = 1;

    /** FORMAT **/

    String FORMAT_TIMESTAMP = "yyyy-MM-ddTHH:mm:ss.sssZ";

    String FORMAT_DATE = "yyyy-MM-dd";
}
