
import org.junit.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

 
@RunWith(MockitoJUnitRunner.class)
public class TesteControladorPTC {

	@Test
	public void TesteIniciaControladorPTC() {
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		controlador.run();
	
	}
	
	@Test
	public void TesteIsCruzamentoFalse() {
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		when(sensor.getVelocidade()).thenReturn(101.0);
		when(sensor.isCruzamento()).thenReturn(false);
		controlador.run();
		
		Mockito.verify(painelCondutor, Mockito.times(1)).imprimirAviso((new Double(101)).toString(),1);
		Mockito.verify(datacenter, Mockito.times(1)).gerarRelatorio();
	}
	
	@Test
	public void TesteIsCruzamentoTrue() {
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		when(sensor.getVelocidade()).thenReturn(101.0);
		when(sensor.isCruzamento()).thenReturn(true);

		when(painelCondutor.imprimirAviso("Velocidade alta", 1)).thenReturn(true);
		controlador.run();
		
		Mockito.verify(painelCondutor, Mockito.times(1)).imprimirAviso("Velocidade alta",1);
		
		when(painelCondutor.imprimirAviso("Velocidade alta", 1)).thenReturn(false);
		controlador.run();
		Mockito.verify(painelCondutor, Mockito.times(3)).imprimirAviso("Velocidade alta",1);
		Mockito.verify(painelCondutor, Mockito.times(1)).diminuiVelocidadeTrem(new Double(20));
		

	}
	
	@Test
	public void TesteIsCruzamentoTrue2() {
		Sensor sensor = mock(Sensor.class);
		PainelCondutor painelCondutor = mock(PainelCondutor.class);
		Datacenter datacenter = mock(Datacenter.class);
		ControladorPTC controlador = new ControladorPTC(sensor, datacenter, painelCondutor);
		
		when(sensor.getVelocidade()).thenReturn(19.0);
		when(sensor.isCruzamento()).thenReturn(true);

		when(painelCondutor.imprimirAviso("Velocidade Baixa", 1)).thenReturn(false);
		controlador.run();
		
		Mockito.verify(painelCondutor, Mockito.times(2)).imprimirAviso("Velocidade Baixa",1);
		Mockito.verify(painelCondutor, Mockito.times(1)).aceleraVelocidadeTrem(new Double(20));
		
	}
}
