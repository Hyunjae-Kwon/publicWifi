package DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDTO {
	private String id;
	private String bookmarkGrpId;
	private String mgrNo;
	private String registrationDate;
}
