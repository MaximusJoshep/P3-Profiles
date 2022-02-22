package com.nttdata.profiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    private String name;
    private String typeDoc;
    private String nroDoc;
    private String code;
    private Boolean status;
    private String profileId;

	
}
