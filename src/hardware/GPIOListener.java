package hardware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

@Component
//@Profile("prod")
public class GPIOListener {

	public GPIOListener() {
		System.out.println("Initialize GPIO Listener");
		/*final GpioController gpioController = GpioFactory.getInstance();
		final GpioPinDigitalInput gpio = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_DOWN);
		 
		gpio.addListener(new GpioPinListenerDigital() {
			 @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				 System.out.println("===== State change ===== " + event.getState().toString());
			 }
		});*/
	}
}
