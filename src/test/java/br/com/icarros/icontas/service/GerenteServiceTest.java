package br.com.icarros.icontas.service;

import br.com.icarros.icontas.repository.GerenteRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        GerenteService.class,
        ModelMapper.class
})
public class GerenteServiceTest {

    @Autowired
    private GerenteService gerenteService;

    @MockBean
    private GerenteRepository gerenteRepository;

    @Test
    public void testGetSaldo_Sucesso(){

    }

}
