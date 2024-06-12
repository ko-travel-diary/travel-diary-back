package com.traveldiary.back.dto.request.schedule;

import java.util.List;

import com.traveldiary.back.common.object.ExpenditureListItem;
import com.traveldiary.back.common.object.ScheduleListItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchScheduleRequestDto {
    
    @NotBlank
    private String travelScheduleName;

    @NotNull
    private Integer travelSchedulePeople;

    @NotNull
    private Integer travelScheduleTotalMoney;

    @NotNull
    private List<ExpenditureListItem> expenditureList;

    @NotNull
    private List<ScheduleListItem> scheduleList;

}