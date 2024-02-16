package com.test.constants;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileExtension {

    // Documents and Text Files
    @JsonProperty("DOC")
    DOC("doc", FileType.DOCUMENT),

    @JsonProperty("DOCX")
    DOCX("docx", FileType.DOCUMENT),

    @JsonProperty("DOT")
    DOT("dot", FileType.DOCUMENT),

    @JsonProperty("DOTX")
    DOTX("dotx", FileType.DOCUMENT),

    @JsonProperty("DOCM")
    DOCM("docm", FileType.DOCUMENT),

    @JsonProperty("DOTM")
    DOTM("dotm", FileType.DOCUMENT),

    @JsonProperty("ODT")
    ODT("odt", FileType.DOCUMENT),

    @JsonProperty("OTT")
    OTT("ott", FileType.DOCUMENT),

    @JsonProperty("RTF")
    RTF("rtf", FileType.DOCUMENT),

    @JsonProperty("TXT")
    TXT("txt", FileType.DOCUMENT),

    @JsonProperty("PDF")
    PDF("pdf", FileType.DOCUMENT),

    @JsonProperty("XPS")
    XPS("xps", FileType.DOCUMENT),

    @JsonProperty("XML")
    XML("xml", FileType.DOCUMENT),

    @JsonProperty("HTML")
    HTML("html", FileType.DOCUMENT),

    @JsonProperty("HTM")
    HTM("htm", FileType.DOCUMENT),

    @JsonProperty("CSV")
    CSV("csv", FileType.DOCUMENT),

    @JsonProperty("XLS")
    XLS("xls", FileType.DOCUMENT),

    @JsonProperty("XLSX")
    XLSX("xlsx", FileType.DOCUMENT),

    @JsonProperty("XLSM")
    XLSM("xlsm", FileType.DOCUMENT),

    @JsonProperty("XLSB")
    XLSB("xlsb", FileType.DOCUMENT),

    @JsonProperty("XLT")
    XLT("xlt", FileType.DOCUMENT),

    @JsonProperty("XLTX")
    XLTX("xltx", FileType.DOCUMENT),

    @JsonProperty("XLTM")
    XLTM("xltm", FileType.DOCUMENT),

    @JsonProperty("ODS")
    ODS("ods", FileType.DOCUMENT),

    @JsonProperty("OTS")
    OTS("ots", FileType.DOCUMENT),

    @JsonProperty("PPT")
    PPT("ppt", FileType.DOCUMENT),

    @JsonProperty("PPTX")
    PPTX("pptx", FileType.DOCUMENT),

    @JsonProperty("PPTM")
    PPTM("pptm", FileType.DOCUMENT),

    @JsonProperty("POT")
    POT("pot", FileType.DOCUMENT),

    @JsonProperty("POTX")
    POTX("potx", FileType.DOCUMENT),

    @JsonProperty("POTM")
    POTM("potm", FileType.DOCUMENT),

    @JsonProperty("ODP")
    ODP("odp", FileType.DOCUMENT),

    @JsonProperty("OTP")
    OTP("otp", FileType.DOCUMENT),

    @JsonProperty("PPS")
    PPS("pps", FileType.DOCUMENT),

    @JsonProperty("PPSX")
    PPSX("ppsx", FileType.DOCUMENT),

    @JsonProperty("PPSM")
    PPSM("ppsm", FileType.DOCUMENT),

    @JsonProperty("ODG")
    ODG("odg", FileType.DOCUMENT),

    @JsonProperty("OTG")
    OTG("otg", FileType.DOCUMENT),

    @JsonProperty("VSD")
    VSD("vsd", FileType.DOCUMENT),

    @JsonProperty("VSDX")
    VSDX("vsdx", FileType.DOCUMENT),

    @JsonProperty("PUB")
    PUB("pub", FileType.DOCUMENT),

    @JsonProperty("PAGES")
    PAGES("pages", FileType.DOCUMENT),

    @JsonProperty("KEY")
    KEY("key", FileType.DOCUMENT),

    @JsonProperty("NUMBERS")
    NUMBERS("numbers", FileType.DOCUMENT),

    // Images

    @JsonProperty("AI")
    AI("ai", FileType.IMAGE),

    @JsonProperty("BMP")
    BMP("bmp", FileType.IMAGE),

    @JsonProperty("CDR")
    CDR("cdr", FileType.IMAGE),

    @JsonProperty("CGM")
    CGM("cgm", FileType.IMAGE),

    @JsonProperty("DJVU")
    DJVU("djvu", FileType.IMAGE),

    @JsonProperty("EMF")
    EMF("emf", FileType.IMAGE),

    @JsonProperty("EPS")
    EPS("eps", FileType.IMAGE),

    @JsonProperty("EXIF")
    EXIF("exif", FileType.IMAGE),

    @JsonProperty("GIF")
    GIF("gif", FileType.IMAGE),

    @JsonProperty("HEIC")
    HEIC("heic", FileType.IMAGE),

    @JsonProperty("ICO")
    ICO("ico", FileType.IMAGE),

    @JsonProperty("JP2")
    JP2("jp2", FileType.IMAGE),

    @JsonProperty("JPEG")
    JPEG("jpeg", FileType.IMAGE),

    @JsonProperty("JPG")
    JPG("jpg", FileType.IMAGE),

    @JsonProperty("PBM")
    PBM("pbm", FileType.IMAGE),

    @JsonProperty("PCX")
    PCX("pcx", FileType.IMAGE),

    @JsonProperty("PGM")
    PGM("pgm", FileType.IMAGE),

    @JsonProperty("PNG")
    PNG("png", FileType.IMAGE),

    @JsonProperty("PNM")
    PNM("pnm", FileType.IMAGE),

    @JsonProperty("PPM")
    PPM("ppm", FileType.IMAGE),

    @JsonProperty("PSD")
    PSD("psd", FileType.IMAGE),

    @JsonProperty("RAS")
    RAS("ras", FileType.IMAGE),

    @JsonProperty("RAW")
    RAW("raw", FileType.IMAGE),

    @JsonProperty("SVG")
    SVG("svg", FileType.IMAGE),

    @JsonProperty("TIF")
    TIF("tif", FileType.IMAGE),

    @JsonProperty("TIFF")
    TIFF("tiff", FileType.IMAGE),

    @JsonProperty("WBMP")
    WBMP("wbmp", FileType.IMAGE),

    @JsonProperty("WEBP")
    WEBP("webp", FileType.IMAGE),

    @JsonProperty("WMF")
    WMF("wmf", FileType.IMAGE),

    @JsonProperty("XCF")
    XCF("xcf", FileType.IMAGE),

    @JsonProperty("XPM")
    XPM("xpm", FileType.IMAGE),

    // Audio
    @JsonProperty("AAC")
    AAC("aac", FileType.AUDIO),

    @JsonProperty("AAX")
    AAX("aax", FileType.AUDIO),

    @JsonProperty("ACT")
    ACT("act", FileType.AUDIO),

    @JsonProperty("AIFF")
    AIFF("aiff", FileType.AUDIO),

    @JsonProperty("AMR")
    AMR("amr", FileType.AUDIO),

    @JsonProperty("APE")
    APE("ape", FileType.AUDIO),

    @JsonProperty("AU")
    AU("au", FileType.AUDIO),

    @JsonProperty("AWB")
    AWB("awb", FileType.AUDIO),

    @JsonProperty("DCT")
    DCT("dct", FileType.AUDIO),

    @JsonProperty("DSS")
    DSS("dss", FileType.AUDIO),

    @JsonProperty("DVF")
    DVF("dvf", FileType.AUDIO),

    @JsonProperty("FLAC")
    FLAC("flac", FileType.AUDIO),

    @JsonProperty("GSM")
    GSM("gsm", FileType.AUDIO),

    @JsonProperty("IKLAX")
    IKLAX("iklax", FileType.AUDIO),

    @JsonProperty("IVS")
    IVS("ivs", FileType.AUDIO),

    @JsonProperty("M4A")
    M4A("m4a", FileType.AUDIO),

    @JsonProperty("M4B")
    M4B("m4b", FileType.AUDIO),

    @JsonProperty("MMF")
    MMF("mmf", FileType.AUDIO),

    @JsonProperty("MP3")
    MP3("mp3", FileType.AUDIO),

    @JsonProperty("MPC")
    MPC("mpc", FileType.AUDIO),

    @JsonProperty("MSV")
    MSV("msv", FileType.AUDIO),

    @JsonProperty("NMF")
    NMF("nmf", FileType.AUDIO),

    @JsonProperty("OGG")
    OGG("ogg", FileType.AUDIO),

    @JsonProperty("OGA")
    OGA("oga", FileType.AUDIO),

    @JsonProperty("OPUS")
    OPUS("opus", FileType.AUDIO),

    @JsonProperty("RA")
    RA("ra", FileType.AUDIO),

    @JsonProperty("SLN")
    SLN("sln", FileType.AUDIO),

    @JsonProperty("TTA")
    TTA("tta", FileType.AUDIO),

    @JsonProperty("VOC")
    VOC("voc", FileType.AUDIO),

    @JsonProperty("VOX")
    VOX("vox", FileType.AUDIO),

    @JsonProperty("WAV")
    WAV("wav", FileType.AUDIO),

    @JsonProperty("WMA")
    WMA("wma", FileType.AUDIO),

    @JsonProperty("WV")
    WV("wv", FileType.AUDIO),

    // Video

    @JsonProperty("3G2")
    V3G2("3g2", FileType.VIDEO),

    @JsonProperty("3GP")
    V3GP("3gp", FileType.VIDEO),

    @JsonProperty("AMV")
    AMV("amv", FileType.VIDEO),

    @JsonProperty("ASF")
    ASF("asf", FileType.VIDEO),

    @JsonProperty("AVI")
    AVI("avi", FileType.VIDEO),

    @JsonProperty("DRC")
    DRC("drc", FileType.VIDEO),

    @JsonProperty("FLV")
    FLV("flv", FileType.VIDEO),

    @JsonProperty("GIFV")
    GIFV("gifv", FileType.VIDEO),

    @JsonProperty("H264")
    H264("h264", FileType.VIDEO),

    @JsonProperty("M4P")
    M4P("m4p", FileType.VIDEO),

    @JsonProperty("M4V")
    M4V("m4v", FileType.VIDEO),

    @JsonProperty("MKV")
    MKV("mkv", FileType.VIDEO),

    @JsonProperty("MNG")
    MNG("mng", FileType.VIDEO),

    @JsonProperty("MOV")
    MOV("mov", FileType.VIDEO),

    @JsonProperty("MP2")
    MP2("mp2", FileType.VIDEO),

    @JsonProperty("MP4")
    MP4("mp4", FileType.VIDEO),

    @JsonProperty("MPE")
    MPE("mpe", FileType.VIDEO),

    @JsonProperty("MPEG")
    MPEG("mpeg", FileType.VIDEO),

    @JsonProperty("MPG")
    MPG("mpg", FileType.VIDEO),

    @JsonProperty("MPV")
    MPV("mpv", FileType.VIDEO),

    @JsonProperty("MXF")
    MXF("mxf", FileType.VIDEO),

    @JsonProperty("NSV")
    NSV("nsv", FileType.VIDEO),

    @JsonProperty("OGV")
    OGV("ogv", FileType.VIDEO),

    @JsonProperty("QT")
    QT("qt", FileType.VIDEO),

    @JsonProperty("RM")
    RM("rm", FileType.VIDEO),

    @JsonProperty("RMVB")
    RMVB("rmvb", FileType.VIDEO),

    @JsonProperty("ROQ")
    ROQ("roq", FileType.VIDEO),

    @JsonProperty("SRT")
    SRT("srt", FileType.VIDEO),

    @JsonProperty("SVI")
    SVI("svi", FileType.VIDEO),

    @JsonProperty("VOB")
    VOB("vob", FileType.VIDEO),

    @JsonProperty("WEBM")
    WEBM("webm", FileType.VIDEO),

    @JsonProperty("WMV")
    WMV("wmv", FileType.VIDEO),

    @JsonProperty("YUV")
    YUV("yuv", FileType.VIDEO),

    // Compressed Files
    @JsonProperty("ZIP")
    ZIP("zip", FileType.FILE),

    @JsonProperty("RAR")
    RAR("rar", FileType.FILE),

    @JsonProperty("TAR")
    TAR("tar", FileType.FILE),

    @JsonProperty("GZ")
    GZ("gz", FileType.FILE),

    @JsonProperty("7Z")
    SEVEN_Z("7z", FileType.FILE),

    // Executables
    @JsonProperty("EXE")
    EXE("exe", FileType.EXECUTABLE),

    @JsonProperty("APP")
    APP("app", FileType.EXECUTABLE),

    @JsonProperty("MSI")
    MSI("msi", FileType.EXECUTABLE),

    @JsonProperty("APK")
    APK("apk", FileType.EXECUTABLE),

    @JsonProperty("DEB")
    DEB("deb", FileType.EXECUTABLE),

    @JsonProperty("NAN")
    NAN("NAN", FileType.FILE);

    private final String value;

    private final FileType type;

    public static FileExtension getByValue(String value) {
        return Optional.ofNullable(value)
                .map(val -> Arrays.asList(values())
                        .stream()
                        .filter(e -> e != NAN)
                        .filter(e -> e.getValue().equalsIgnoreCase(val))
                        .findFirst()
                        .orElse(NAN))
                .orElse(NAN);

    }

}
