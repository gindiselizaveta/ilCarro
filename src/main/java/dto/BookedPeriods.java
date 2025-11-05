package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString

public class BookedPeriods {
    private String email;
    private String startDate;
    private String endDate;

}
