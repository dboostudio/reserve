package studio.dboo.reserve.api.inn.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InnType {

    HOTEL(0,"호텔"),
    MEDICAL_HOTEL(1, "의료관광호텔"),
    HOSTEL(2, "호스텔"),
    MOTEL(3, "모텔"),
    RESORT(4, "리조트"),
    PENSION(5, "펜션"),
    HOMESTAY(6, "농어촌민박"),
    GUESTHOUSE(7, "게스트하우스");

    private Integer innTypeId;
    private String innTypeName;
}
