package sspscom.example.ssps.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import sspscom.example.ssps.Dto.Request.UserCreationRequest;
import sspscom.example.ssps.Entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {
    @Mapping(target = "role", constant = "ADMIN")
    User toUser(UserCreationRequest request);
}


