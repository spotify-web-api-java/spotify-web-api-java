package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * An enumeration of the <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
 * country codes, as used by Spotify's {@code market} and {@code country} parameters.
 * <p>
 * The constant name is the alpha-2 code itself, so {@code toString()} yields exactly what the Web API
 * expects on the wire. The list is {@link java.util.Locale#getISOCountries()} plus {@code XK}, which is
 * user-assigned rather than ISO-assigned but is the one market code Spotify returns that the JDK omits.
 */
public enum CountryCode {

  /** Andorra. */
  AD,
  /** United Arab Emirates. */
  AE,
  /** Afghanistan. */
  AF,
  /** Antigua &amp; Barbuda. */
  AG,
  /** Anguilla. */
  AI,
  /** Albania. */
  AL,
  /** Armenia. */
  AM,
  /** Angola. */
  AO,
  /** Antarctica. */
  AQ,
  /** Argentina. */
  AR,
  /** American Samoa. */
  AS,
  /** Austria. */
  AT,
  /** Australia. */
  AU,
  /** Aruba. */
  AW,
  /** Åland Islands. */
  AX,
  /** Azerbaijan. */
  AZ,
  /** Bosnia &amp; Herzegovina. */
  BA,
  /** Barbados. */
  BB,
  /** Bangladesh. */
  BD,
  /** Belgium. */
  BE,
  /** Burkina Faso. */
  BF,
  /** Bulgaria. */
  BG,
  /** Bahrain. */
  BH,
  /** Burundi. */
  BI,
  /** Benin. */
  BJ,
  /** St. Barthélemy. */
  BL,
  /** Bermuda. */
  BM,
  /** Brunei. */
  BN,
  /** Bolivia. */
  BO,
  /** Caribbean Netherlands. */
  BQ,
  /** Brazil. */
  BR,
  /** Bahamas. */
  BS,
  /** Bhutan. */
  BT,
  /** Bouvet Island. */
  BV,
  /** Botswana. */
  BW,
  /** Belarus. */
  BY,
  /** Belize. */
  BZ,
  /** Canada. */
  CA,
  /** Cocos (Keeling) Islands. */
  CC,
  /** Congo - Kinshasa. */
  CD,
  /** Central African Republic. */
  CF,
  /** Congo - Brazzaville. */
  CG,
  /** Switzerland. */
  CH,
  /** Côte d’Ivoire. */
  CI,
  /** Cook Islands. */
  CK,
  /** Chile. */
  CL,
  /** Cameroon. */
  CM,
  /** China. */
  CN,
  /** Colombia. */
  CO,
  /** Costa Rica. */
  CR,
  /** Cuba. */
  CU,
  /** Cape Verde. */
  CV,
  /** Curaçao. */
  CW,
  /** Christmas Island. */
  CX,
  /** Cyprus. */
  CY,
  /** Czechia. */
  CZ,
  /** Germany. */
  DE,
  /** Djibouti. */
  DJ,
  /** Denmark. */
  DK,
  /** Dominica. */
  DM,
  /** Dominican Republic. */
  DO,
  /** Algeria. */
  DZ,
  /** Ecuador. */
  EC,
  /** Estonia. */
  EE,
  /** Egypt. */
  EG,
  /** Western Sahara. */
  EH,
  /** Eritrea. */
  ER,
  /** Spain. */
  ES,
  /** Ethiopia. */
  ET,
  /** Finland. */
  FI,
  /** Fiji. */
  FJ,
  /** Falkland Islands. */
  FK,
  /** Micronesia. */
  FM,
  /** Faroe Islands. */
  FO,
  /** France. */
  FR,
  /** Gabon. */
  GA,
  /** United Kingdom. */
  GB,
  /** Grenada. */
  GD,
  /** Georgia. */
  GE,
  /** French Guiana. */
  GF,
  /** Guernsey. */
  GG,
  /** Ghana. */
  GH,
  /** Gibraltar. */
  GI,
  /** Greenland. */
  GL,
  /** Gambia. */
  GM,
  /** Guinea. */
  GN,
  /** Guadeloupe. */
  GP,
  /** Equatorial Guinea. */
  GQ,
  /** Greece. */
  GR,
  /** South Georgia &amp; South Sandwich Islands. */
  GS,
  /** Guatemala. */
  GT,
  /** Guam. */
  GU,
  /** Guinea-Bissau. */
  GW,
  /** Guyana. */
  GY,
  /** Hong Kong SAR China. */
  HK,
  /** Heard &amp; McDonald Islands. */
  HM,
  /** Honduras. */
  HN,
  /** Croatia. */
  HR,
  /** Haiti. */
  HT,
  /** Hungary. */
  HU,
  /** Indonesia. */
  ID,
  /** Ireland. */
  IE,
  /** Israel. */
  IL,
  /** Isle of Man. */
  IM,
  /** India. */
  IN,
  /** British Indian Ocean Territory. */
  IO,
  /** Iraq. */
  IQ,
  /** Iran. */
  IR,
  /** Iceland. */
  IS,
  /** Italy. */
  IT,
  /** Jersey. */
  JE,
  /** Jamaica. */
  JM,
  /** Jordan. */
  JO,
  /** Japan. */
  JP,
  /** Kenya. */
  KE,
  /** Kyrgyzstan. */
  KG,
  /** Cambodia. */
  KH,
  /** Kiribati. */
  KI,
  /** Comoros. */
  KM,
  /** St. Kitts &amp; Nevis. */
  KN,
  /** North Korea. */
  KP,
  /** South Korea. */
  KR,
  /** Kuwait. */
  KW,
  /** Cayman Islands. */
  KY,
  /** Kazakhstan. */
  KZ,
  /** Laos. */
  LA,
  /** Lebanon. */
  LB,
  /** St. Lucia. */
  LC,
  /** Liechtenstein. */
  LI,
  /** Sri Lanka. */
  LK,
  /** Liberia. */
  LR,
  /** Lesotho. */
  LS,
  /** Lithuania. */
  LT,
  /** Luxembourg. */
  LU,
  /** Latvia. */
  LV,
  /** Libya. */
  LY,
  /** Morocco. */
  MA,
  /** Monaco. */
  MC,
  /** Moldova. */
  MD,
  /** Montenegro. */
  ME,
  /** St. Martin. */
  MF,
  /** Madagascar. */
  MG,
  /** Marshall Islands. */
  MH,
  /** North Macedonia. */
  MK,
  /** Mali. */
  ML,
  /** Myanmar (Burma). */
  MM,
  /** Mongolia. */
  MN,
  /** Macao SAR China. */
  MO,
  /** Northern Mariana Islands. */
  MP,
  /** Martinique. */
  MQ,
  /** Mauritania. */
  MR,
  /** Montserrat. */
  MS,
  /** Malta. */
  MT,
  /** Mauritius. */
  MU,
  /** Maldives. */
  MV,
  /** Malawi. */
  MW,
  /** Mexico. */
  MX,
  /** Malaysia. */
  MY,
  /** Mozambique. */
  MZ,
  /** Namibia. */
  NA,
  /** New Caledonia. */
  NC,
  /** Niger. */
  NE,
  /** Norfolk Island. */
  NF,
  /** Nigeria. */
  NG,
  /** Nicaragua. */
  NI,
  /** Netherlands. */
  NL,
  /** Norway. */
  NO,
  /** Nepal. */
  NP,
  /** Nauru. */
  NR,
  /** Niue. */
  NU,
  /** New Zealand. */
  NZ,
  /** Oman. */
  OM,
  /** Panama. */
  PA,
  /** Peru. */
  PE,
  /** French Polynesia. */
  PF,
  /** Papua New Guinea. */
  PG,
  /** Philippines. */
  PH,
  /** Pakistan. */
  PK,
  /** Poland. */
  PL,
  /** St. Pierre &amp; Miquelon. */
  PM,
  /** Pitcairn Islands. */
  PN,
  /** Puerto Rico. */
  PR,
  /** Palestinian Territories. */
  PS,
  /** Portugal. */
  PT,
  /** Palau. */
  PW,
  /** Paraguay. */
  PY,
  /** Qatar. */
  QA,
  /** Réunion. */
  RE,
  /** Romania. */
  RO,
  /** Serbia. */
  RS,
  /** Russia. */
  RU,
  /** Rwanda. */
  RW,
  /** Saudi Arabia. */
  SA,
  /** Solomon Islands. */
  SB,
  /** Seychelles. */
  SC,
  /** Sudan. */
  SD,
  /** Sweden. */
  SE,
  /** Singapore. */
  SG,
  /** St. Helena. */
  SH,
  /** Slovenia. */
  SI,
  /** Svalbard &amp; Jan Mayen. */
  SJ,
  /** Slovakia. */
  SK,
  /** Sierra Leone. */
  SL,
  /** San Marino. */
  SM,
  /** Senegal. */
  SN,
  /** Somalia. */
  SO,
  /** Suriname. */
  SR,
  /** South Sudan. */
  SS,
  /** São Tomé &amp; Príncipe. */
  ST,
  /** El Salvador. */
  SV,
  /** Sint Maarten. */
  SX,
  /** Syria. */
  SY,
  /** Eswatini. */
  SZ,
  /** Turks &amp; Caicos Islands. */
  TC,
  /** Chad. */
  TD,
  /** French Southern Territories. */
  TF,
  /** Togo. */
  TG,
  /** Thailand. */
  TH,
  /** Tajikistan. */
  TJ,
  /** Tokelau. */
  TK,
  /** Timor-Leste. */
  TL,
  /** Turkmenistan. */
  TM,
  /** Tunisia. */
  TN,
  /** Tonga. */
  TO,
  /** Türkiye. */
  TR,
  /** Trinidad &amp; Tobago. */
  TT,
  /** Tuvalu. */
  TV,
  /** Taiwan. */
  TW,
  /** Tanzania. */
  TZ,
  /** Ukraine. */
  UA,
  /** Uganda. */
  UG,
  /** U.S. Outlying Islands. */
  UM,
  /** United States. */
  US,
  /** Uruguay. */
  UY,
  /** Uzbekistan. */
  UZ,
  /** Vatican City. */
  VA,
  /** St. Vincent &amp; Grenadines. */
  VC,
  /** Venezuela. */
  VE,
  /** British Virgin Islands. */
  VG,
  /** U.S. Virgin Islands. */
  VI,
  /** Vietnam. */
  VN,
  /** Vanuatu. */
  VU,
  /** Wallis &amp; Futuna. */
  WF,
  /** Samoa. */
  WS,
  /** Kosovo (user-assigned code, served by Spotify as a market). */
  XK,
  /** Yemen. */
  YE,
  /** Mayotte. */
  YT,
  /** South Africa. */
  ZA,
  /** Zambia. */
  ZM,
  /** Zimbabwe. */
  ZW;

  private static final Map<String, CountryCode> map = new HashMap<>();

  static {
    for (CountryCode countryCode : CountryCode.values()) {
      map.put(countryCode.name(), countryCode);
    }
  }

  /**
   * Get a CountryCode by its ISO 3166-1 alpha-2 code.
   *
   * @param code The two-letter country code, matched case-insensitively.
   * @return The corresponding CountryCode or null if there is none.
   */
  public static CountryCode getByCode(String code) {
    return code == null ? null : map.get(code.toUpperCase(Locale.ROOT));
  }

  /**
   * Get the ISO 3166-1 alpha-2 code.
   *
   * @return The two-letter country code.
   */
  public String getAlpha2() {
    return name();
  }

}
