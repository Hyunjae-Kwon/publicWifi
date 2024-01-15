package DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkGrpDTO {
	private String id;
	private String bookmarkGrpNm;
	private String orderNo;
	private String registrationDate;
	private String updateDate;
}
