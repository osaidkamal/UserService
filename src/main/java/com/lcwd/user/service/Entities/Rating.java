package com.lcwd.user.service.Entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    private String ratingId;
    private int userId;
    private int hotelId;
    private int rating;
    private String remark;
    private Hotel hotel;


}
