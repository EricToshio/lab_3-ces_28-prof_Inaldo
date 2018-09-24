
import org.junit.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

 
@RunWith(MockitoJUnitRunner.class)
public class TesteControladorPTC {

	// item 2.a
	@Test
	public void TesteIniciaControladorPTC() {
		//cria os objectos dummy
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);

		//cria o objeto controlador
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);

		controlador.run();
	}

	// item 2.b
	@Test
	public void TesteIsCruzamentoFalse() {
		//cria os objectos dummy
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);

		//cria o objeto controlador
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		//cria os stubs
		when(sensor.getVelocidade()).thenReturn(101.0);
		when(sensor.isCruzamento()).thenReturn(false);

		controlador.run();
		
		//verifica se executou as acoes desejadas
		Mockito.verify(painelCondutor, Mockito.times(1)).imprimirAviso((new Double(101)).toString(),1);
		Mockito.verify(datacenter, Mockito.times(1)).gerarRelatorio();
	}
	
	// item 2.c
	@Test
	public void TesteIsCruzamentoTrue() {
		//cria os objectos dummy
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);

		//cria o objeto controlador
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		//cria os stubs
		when(sensor.getVelocidade()).thenReturn(101.0);
		when(sensor.isCruzamento()).thenReturn(true);
		when(painelCondutor.imprimirAviso("Velocidade alta", 1)).thenReturn(true);

		controlador.run();
		
		//verifica se executou as acoes desejadas
		Mockito.verify(painelCondutor, Mockito.times(1)).imprimirAviso("Velocidade alta",1);
	}
	
	// item 2.d
	@Test
	public void TesteIsCruzamentoTrue2() {
		//cria os objectos dummy
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);

		//cria o objeto controlador
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		//cria os stubs
		when(sensor.getVelocidade()).thenReturn(19.0);
		when(sensor.isCruzamento()).thenReturn(true);
		when(painelCondutor.imprimirAviso("Velocidade Baixa", 1)).thenReturn(false);

		controlador.run();
		
		//verifica se executou as acoes desejadas
		Mockito.verify(painelCondutor, Mockito.times(2)).imprimirAviso("Velocidade Baixa",1);
		Mockito.verify(painelCondutor, Mockito.times(1)).aceleraVelocidadeTrem(new Double(20));
	}
}
