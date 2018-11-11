package spring.test.variants.app;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import spring.test.variants.domain.VariantService;
import spring.test.variants.domain.model.User;
import spring.test.variants.domain.model.Variant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class VariantControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean VariantService variantService;

    @Test
    void check_is_user_interceptor_add_user_to_request() throws Exception {
        //given:
        final val variant = Variant.builder()
                .id(1)
                .name("firstVariant")
                .create();

        final val interceptorUser = new User("user");

        //when:
        when(variantService.getVariant("firstVariant")).thenReturn(variant);

        val request = get("/variants/{name}","firstVariant");

        final val result = mockMvc.perform(request)
                                   .andDo(print())
                                   .andExpect(status().isOk())
                                   .andReturn() ;

        //then:
        assertThat(result.getRequest().getAttribute("user"), is(interceptorUser));
    }
}