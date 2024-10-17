package sspscom.example.ssps.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import sspscom.example.ssps.Dto.Request.StudentCreationRequest;
import sspscom.example.ssps.Entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(target = "role", constant = "STUDENT")
    User toUser(StudentCreationRequest request);
}


