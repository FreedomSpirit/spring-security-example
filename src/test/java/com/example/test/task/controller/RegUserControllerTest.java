package com.example.test.task.controller;

import com.example.test.task.domain.Strength;
import com.example.test.task.service.PasswordStrengthService;
import com.example.test.task.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegUserControllerTest {
    @Mock
    private UserService serviceMock;

    @Mock
    private PasswordStrengthService strengthMock;

    @InjectMocks
    private RegUserController controller;


    @Test
    public void testRegisterForm() throws Exception {
        ModelAndView model = controller.registerForm(new ModelAndView());
        assertEquals(model.getViewName(),"register");
    }

    @Test
    public void testCheckStrength() throws Exception {
        when(strengthMock.checkStrenght("password")).thenReturn(Strength.HIGH);
        assertEquals(controller.checkStrength("password"), "high");
    }

    @Test
    public void testRegisterEmptyUser() throws Exception {
        ModelAndView model = controller.register("", "any_password", new ModelAndView());
        assertEquals(model.getViewName(),"register");
        verify(strengthMock, never()).checkStrenght(anyString());
    }

    @Test
    public void testRegisterEmptyPassword() throws Exception {
        ModelAndView model = controller.register("User1", "", new ModelAndView());
        assertEquals(model.getViewName(),"register");
        verify(strengthMock, never()).checkStrenght(anyString());
    }

    @Test
    public void testRegisterBadPassword() throws Exception {
        when(strengthMock.checkStrenght("bad_password")).thenReturn(Strength.LOW);
        ModelAndView model = controller.register("User2", "bad_password", new ModelAndView());

        verify(strengthMock).checkStrenght("bad_password");
        assertEquals(model.getViewName(),"register");
    }

    @Test
    public void testRegister() throws Exception {
        when(strengthMock.checkStrenght("good_password")).thenReturn(Strength.HIGH);

        ModelAndView model = controller.register("User3", "good_password", new ModelAndView());

        verify(strengthMock).checkStrenght("good_password");
        verify(serviceMock).createNewUser("User3","good_password");
        assertEquals(model.getViewName(),"login");
    }
}