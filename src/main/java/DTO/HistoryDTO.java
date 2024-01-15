package DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
	private String id;
	private String lat;
	private String lnt;
	private String searchDate;
}
