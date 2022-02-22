package com.nttdata.profiles.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "profiles")
public class Profiles {


	@Id
    public String id;
    public String name;
    public String type;
    public String codProfile;
    public Boolean status;
}
