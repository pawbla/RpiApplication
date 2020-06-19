package hardware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

//@Component
//@Profile("prod")
public class GPIOListener {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private HandleScreenSwitching screen;

	public GPIOListener() {
		System.out.println("Initialize GPIO Listener");

		final GpioController gpioController = GpioFactory.getInstance();
		final GpioPinDigitalInput gpio = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_DOWN);
		 
		gpio.addListener(new GpioPinListenerDigital() {
			 @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				 logger.debug("State change event has occured: " + event.getState().toString());
				 if (event.getState().equals(PinState.LOW)) {
					 screen.start();
				 }
				 
			 }
		});
	}
}
