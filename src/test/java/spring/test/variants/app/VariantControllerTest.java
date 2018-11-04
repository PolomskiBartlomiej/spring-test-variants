package spring.test.variants.app;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import spring.test.variants.domain.VariantService;
import spring.test.variants.domain.model.User;
import spring.test.variants.domain.model.Variant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class VariantControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean VariantService variantService;

    @Test
    public void check_is_user_interceptor_add_user_to_request() throws Exception {
        //given:
        final var variant = new Variant(1, "firstVariant");
        final var interceptorUser = new User("user");

        //when:
        when(variantService.getVariant(1)).thenReturn(variant);

        var request = get("/variants/{id}",1);

        final var result = mockMvc.perform(request)
                                   .andDo(print())
                                   .andExpect(status().isOk())
                                   .andReturn() ;

        //then:
        assertThat(result.getRequest().getAttribute("user"), is(interceptorUser));
    }
}