package ua.training.payments.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.training.payments.controller.assembler.UserModelAssembler;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.enums.ErrorCode;
import ua.training.payments.model.enums.ErrorType;
import ua.training.payments.service.AdminService;
import ua.training.payments.service.AuthService;
import ua.training.payments.test.config.TestWebConfig;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestWebConfig.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdminService adminService;
    @MockBean
    private UserModelAssembler modelAssembler;
    @MockBean
    private AuthService authService;

    @Test
    void getAllUsersTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("TestName");
        UserModel userModel = new UserModel(userDto);
        CollectionModel<UserModel> collectionModel =
                CollectionModel.of(Collections.singletonList(userModel));

        when(modelAssembler.toCollectionModel(anyList())).thenReturn(collectionModel);

        mvc.perform(get("/api/v1/admin/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].name").value(userDto.getName()));
    }

    @Test
    void getAllUsersWithExceptionTest() throws Exception {
        when(modelAssembler.toCollectionModel(anyList())).thenThrow(new NullPointerException());

        mvc.perform(get("/api/v1/admin/users"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.APPLICATION_ERROR_CODE.name()))
                .andExpect(jsonPath("$.errorType").value(ErrorType.FATAL_ERROR_TYPE.name()))
                .andExpect(jsonPath("$.dateTime").exists());
    }

}