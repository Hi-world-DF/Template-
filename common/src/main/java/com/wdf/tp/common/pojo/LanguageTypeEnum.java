package com.wdf.tp.common.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wdf.tp.common.config.ITransferStringValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 各地区语言码枚举
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:19
 * {@see https://www.oracle.com/java/technologies/javase/jdk8-jre8-suported-locales.html}
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum LanguageTypeEnum implements ITransferStringValueEnum {
    /** Language : Albanian (sq) Country : Albania (AL) */
    SQ_AL("sq-AL", (byte) 1),
    /** Language : Arabic (ar) Country : Algeria (DZ) */
    AR_DZ("ar-DZ", (byte) 2),
    /** Language : Arabic (ar) Country : Bahrain (BH) */
    AR_BH("ar-BH", (byte) 3),
    /** Language : Arabic (ar) Country : Egypt (EG) */
    AR_EG("ar-EG", (byte) 4),
    /** Language : Arabic (ar) Country : Iraq (IQ) */
    AR_IQ("ar-IQ", (byte) 5),
    /** Language : Arabic (ar) Country : Jordan (JO) */
    AR_JO("ar-JO", (byte) 6),
    /** Language : Arabic (ar) Country : Kuwait (KW) */
    AR_KW("ar-KW", (byte) 7),
    /** Language : Arabic (ar) Country : Lebanon (LB) */
    AR_LB("ar-LB", (byte) 8),
    /** Language : Arabic (ar) Country : Libya (LY) */
    AR_LY("ar-LY", (byte) 9),
    /** Language : Arabic (ar) Country : Morocco (MA) */
    AR_MA("ar-MA", (byte) 10),
    /** Language : Arabic (ar) Country : Oman (OM) */
    AR_OM("ar-OM", (byte) 11),
    /** Language : Arabic (ar) Country : Qatar (QA) */
    AR_QA("ar-QA", (byte) 12),
    /** Language : Arabic (ar) Country : Saudi Arabia (SA) */
    AR_SA("ar-SA", (byte) 13),
    /** Language : Arabic (ar) Country : Sudan (SD) */
    AR_SD("ar-SD", (byte) 14),
    /** Language : Arabic (ar) Country : Syria (SY) */
    AR_SY("ar-SY", (byte) 15),
    /** Language : Arabic (ar) Country : Tunisia (TN) */
    AR_TN("ar-TN", (byte) 16),
    /** Language : Arabic (ar) Country : United Arab Emirates (AE) */
    AR_AE("ar-AE", (byte) 17),
    /** Language : Arabic (ar) Country : Yemen (YE) */
    AR_YE("ar-YE", (byte) 18),
    /** Language : Belarusian (be) Country : Belarus (BY) */
    BE_BY("be-BY", (byte) 19),
    /** Language : Bulgarian (bg) Country : Bulgaria (BG) */
    BG_BG("bg-BG", (byte) 20),
    /** Language : Catalan (ca) Country : Spain (ES) */
    CA_ES("ca-ES", (byte) 21),
    /** Language : Chinese (zh) Country : China (CN) */
    ZH_CN("zh-CN", (byte) 22),
    /** Language : Chinese (zh) Country : Singapore (SG) */
    ZH_SG("zh-SG", (byte) 23),
    /** Language : Chinese (zh) Country : Hong Kong (HK) */
    ZH_HK("zh-HK", (byte) 24),
    /** Language : Chinese (zh) Country : Taiwan (TW) */
    ZH_TW("zh-TW", (byte) 25),
    /** Language : Croatian (hr) Country : Croatia (HR) */
    HR_HR("hr-HR", (byte) 26),
    /** Language : Czech (cs) Country : Czech Republic (CZ) */
    CS_CZ("cs-CZ", (byte) 27),
    /** Language : Danish (da) Country : Denmark (DK) */
    DA_DK("da-DK", (byte) 28),
    /** Language : Dutch (nl) Country : Belgium (BE) */
    NL_BE("nl-BE", (byte) 29),
    /** Language : Dutch (nl) Country : Netherlands (NL) */
    NL_NL("nl-NL", (byte) 30),
    /** Language : English (en) Country : Australia (AU) */
    EN_AU("en-AU", (byte) 31),
    /** Language : English (en) Country : Canada (CA) */
    EN_CA("en-CA", (byte) 32),
    /** Language : English (en) Country : India (IN) */
    EN_IN("en-IN", (byte) 33),
    /** Language : English (en) Country : Ireland (IE) */
    EN_IE("en-IE", (byte) 34),
    /** Language : English (en) Country : Malta (MT) */
    EN_MT("en-MT", (byte) 35),
    /** Language : English (en) Country : New Zealand (NZ) */
    EN_NZ("en-NZ", (byte) 36),
    /** Language : English (en) Country : Philippines (PH) */
    EN_PH("en-PH", (byte) 37),
    /** Language : English (en) Country : Singapore (SG) */
    EN_SG("en-SG", (byte) 38),
    /** Language : English (en) Country : South Africa (ZA) */
    EN_ZA("en-ZA", (byte) 39),
    /** Language : English (en) Country : United Kingdom (GB) */
    EN_GB("en-GB", (byte) 40),
    /** Language : English (en) Country : United States (US) */
    EN_US("en-US", (byte) 41),
    /** Language : Estonian (et) Country : Estonia (EE) */
    ET_EE("et-EE", (byte) 42),
    /** Language : Finnish (fi) Country : Finland (FI) */
    FI_FI("fi-FI", (byte) 43),
    /** Language : French (fr) Country : Belgium (BE) */
    FR_BE("fr-BE", (byte) 44),
    /** Language : French (fr) Country : Canada (CA) */
    FR_CA("fr-CA", (byte) 45),
    /** Language : French (fr) Country : France (FR) */
    FR_FR("fr-FR", (byte) 46),
    /** Language : French (fr) Country : Luxembourg (LU) */
    FR_LU("fr-LU", (byte) 47),
    /** Language : French (fr) Country : Switzerland (CH) */
    FR_CH("fr-CH", (byte) 48),
    /** Language : German (de) Country : Austria (AT) */
    DE_AT("de-AT", (byte) 49),
    /** Language : German (de) Country : Germany (DE) */
    DE_DE("de-DE", (byte) 50),
    /** Language : German (de) Country : Luxembourg (LU) */
    DE_LU("de-LU", (byte) 51),
    /** Language : German (de) Country : Switzerland (CH) */
    DE_CH("de-CH", (byte) 52),
    /** Language : Greek (el) Country : Cyprus (CY) */
    EL_CY("el-CY", (byte) 53),
    /** Language : Greek (el) Country : Greece (GR) */
    EL_GR("el-GR", (byte) 54),
    /** Language : Hebrew (iw) Country : Israel (IL) */
    IW_IL("iw-IL", (byte) 55),
    /** Language : Hindi (hi) Country : India (IN) */
    HI_IN("hi-IN", (byte) 56),
    /** Language : Hungarian (hu) Country : Hungary (HU) */
    HU_HU("hu-HU", (byte) 57),
    /** Language : Icelandic (is) Country : Iceland (IS) */
    IS_IS("is-IS", (byte) 58),
    /** Language : Indonesian (in) Country : Indonesia (ID) */
    IN_ID("in-ID", (byte) 59),
    /** Language : Irish (ga) Country : Ireland (IE) */
    GA_IE("ga-IE", (byte) 60),
    /** Language : Italian (it) Country : Italy (IT) */
    IT_IT("it-IT", (byte) 61),
    /** Language : Italian (it) Country : Switzerland (CH) */
    IT_CH("it-CH", (byte) 62),
    /** Language : Japanese (ja) Country : Japan (JP) */
    JA_JP("ja-JP", (byte) 63),
    /** Language : Japanese (ja) Country : Japan (JP) */
    JA_JP_U_CA_JAPANESE("ja-JP-u-ca-japanese", (byte) 64),
    /** Language : Japanese (ja) Country : Japan (JP) */
    JA_JP_X_LVARIANT_JP("ja-JP-x-lvariant-JP", (byte) 65),
    /** Language : Korean (ko) Country : South Korea (KR) */
    KO_KR("ko-KR", (byte) 66),
    /** Language : Latvian (lv) Country : Latvia (LV) */
    LV_LV("lv-LV", (byte) 67),
    /** Language : Lithuanian (lt) Country : Lithuania (LT) */
    LT_LT("lt-LT", (byte) 68),
    /** Language : Macedonian (mk) Country : Macedonia (MK) */
    MK_MK("mk-MK", (byte) 69),
    /** Language : Malay (ms) Country : Malaysia (MY) */
    MS_MY("ms-MY", (byte) 70),
    /** Language : Maltese (mt) Country : Malta (MT) */
    MT_MT("mt-MT", (byte) 71),
    /** Language : Norwegian (no) Country : Norway (NO) */
    NO_NO("no-NO", (byte) 72),
    /** Language : Norwegian Bokmål (nb) Country : Norway (NO) */
    NB_NO("nb-NO", (byte) 73),
    /** Language : Norwegian Nynorsk (nn) Country : Norway (NO) */
    NN_NO("nn-NO", (byte) 74),
    /** Language : Norwegian (no) Country : Norway (NO) */
    NO_NO_X_LVARIANT_NY("no-NO-x-lvariant-NY", (byte) 75),
    /** Language : Polish (pl) Country : Poland (PL) */
    PL_PL("pl-PL", (byte) 76),
    /** Language : Portuguese (pt) Country : Brazil (BR) */
    PT_BR("pt-BR", (byte) 77),
    /** Language : Portuguese (pt) Country : Portugal (PT) */
    PT_PT("pt-PT", (byte) 78),
    /** Language : Romanian (ro) Country : Romania (RO) */
    RO_RO("ro-RO", (byte) 79),
    /** Language : Russian (ru) Country : Russia (RU) */
    RU_RU("ru-RU", (byte) 80),
    /** Language : Serbian (sr) Country : Bosnia and Herzegovina (BA) */
    SR_BA("sr-BA", (byte) 81),
    /** Language : Serbian (sr) Country : Montenegro (ME) */
    SR_ME("sr-ME", (byte) 82),
    /** Language : Serbian (sr) Country : Serbia (RS) */
    SR_RS("sr-RS", (byte) 83),
    /** Language : Serbian (sr) Country : Bosnia and Herzegovina (BA) */
    SR_LATN_BA("sr-Latn-BA", (byte) 84),
    /** Language : Serbian (sr) Country : Montenegro (ME) */
    SR_LATN_ME("sr-Latn-ME", (byte) 85),
    /** Language : Serbian (sr) Country : Serbia (RS) */
    SR_LATN_RS("sr-Latn-RS", (byte) 86),
    /** Language : Slovak (sk) Country : Slovakia (SK) */
    SK_SK("sk-SK", (byte) 87),
    /** Language : Slovenian (sl) Country : Slovenia (SI) */
    SL_SI("sl-SI", (byte) 88),
    /** Language : Spanish (es) Country : Argentina (AR) */
    ES_AR("es-AR", (byte) 89),
    /** Language : Spanish (es) Country : Bolivia (BO) */
    ES_BO("es-BO", (byte) 90),
    /** Language : Spanish (es) Country : Chile (CL) */
    ES_CL("es-CL", (byte) 91),
    /** Language : Spanish (es) Country : Colombia (CO) */
    ES_CO("es-CO", (byte) 92),
    /** Language : Spanish (es) Country : Costa Rica (CR) */
    ES_CR("es-CR", (byte) 93),
    /** Language : Spanish (es) Country : Dominican Republic (DO) */
    ES_DO("es-DO", (byte) 94),
    /** Language : Spanish (es) Country : Ecuador (EC) */
    ES_EC("es-EC", (byte) 95),
    /** Language : Spanish (es) Country : El Salvador (SV) */
    ES_SV("es-SV", (byte) 96),
    /** Language : Spanish (es) Country : Guatemala (GT) */
    ES_GT("es-GT", (byte) 97),
    /** Language : Spanish (es) Country : Honduras (HN) */
    ES_HN("es-HN", (byte) 98),
    /** Language : Spanish (es) Country : Mexico (MX) */
    ES_MX("es-MX", (byte) 99),
    /** Language : Spanish (es) Country : Nicaragua (NL) */
    ES_NI("es-NI", (byte) 100),
    /** Language : Spanish (es) Country : Panama (PA) */
    ES_PA("es-PA", (byte) 101),
    /** Language : Spanish (es) Country : Paraguay (PY) */
    ES_PY("es-PY", (byte) 102),
    /** Language : Spanish (es) Country : Peru (PE) */
    ES_PE("es-PE", (byte) 103),
    /** Language : Spanish (es) Country : Puerto Rico (PR) */
    ES_PR("es-PR", (byte) 104),
    /** Language : Spanish (es) Country : Spain (ES) */
    ES_ES("es-ES", (byte) 105),
    /** Language : Spanish (es) Country : United States (US) */
    ES_US("es-US", (byte) 106),
    /** Language : Spanish (es) Country : Uruguay (UY) */
    ES_UY("es-UY", (byte) 107),
    /** Language : Spanish (es) Country : Venezuela (VE) */
    ES_VE("es-VE", (byte) 108),
    /** Language : Swedish (sv) Country : Sweden (SE) */
    SV_SE("sv-SE", (byte) 109),
    /** Language : Thai (th) Country : Thailand (TH) */
    TH_TH("th-TH", (byte) 110),
    /** Language : Thai (th) Country : Thailand (TH) */
    TH_TH_U_CA_BUDDHIST("th-TH-u-ca-buddhist", (byte) 111),
    /** Language : Thai (th) Country : Thailand (TH) */
    TH_TH_U_CA_BUDDHIST_NU_THAI("th-TH-u-ca-buddhist-nu-thai", (byte) 112),
    /** Language : Thai (th) Country : Thailand (TH) */
    TH_TH_X_LVARIANT_TH("th-TH-x-lvariant-TH", (byte) 113),
    /** Language : Turkish (tr) Country : Turkey (TR) */
    TR_TR("tr-TR", (byte) 114),
    /** Language : Ukrainian (uk) Country : Ukraine (UA) */
    UK_UA("uk-UA", (byte) 115),
    /** Language : Vietnamese (vi) Country : Vietnam (VN) */
    VI_VN("vi-VN", (byte) 116);

    private static final Map<Byte, LanguageTypeEnum> VALUE_MAP = Stream.of(LanguageTypeEnum.values()).collect(Collectors.toMap(LanguageTypeEnum::getValue, Function.identity()));
    private static final Map<String, LanguageTypeEnum> LANGUAGE_CODE_MAP = Stream.of(LanguageTypeEnum.values()).collect(Collectors.toMap(LanguageTypeEnum::getLanguageCode, Function.identity()));

    @JsonValue
    private final String languageCode;
    private final Byte value;

    /**
     * byte to Enum
     *
     * @param value 枚举值
     * @return 枚举
     */
    public static LanguageTypeEnum from(Byte value) {
        if (null == value) {
            return null;
        }
        return VALUE_MAP.get(value);
    }

    /**
     * String to Enum
     *
     * @param languageCode 语言码
     * @return 枚举
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static LanguageTypeEnum from(String languageCode) {
        if (StringUtils.isEmpty(languageCode)) {
            return null;
        }
        return LANGUAGE_CODE_MAP.get(languageCode);
    }

    /**
     * 获取对应的值
     *
     * @return T
     */
    @Override
    public String getTransferValue() {
        return String.valueOf(this.languageCode);
    }
}
