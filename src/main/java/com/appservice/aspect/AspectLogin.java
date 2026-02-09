package com.appservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//import org.asp
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLogin {

	// ejecuta esto antes de llamar al metodo
	// podemos incluir la ruta del metodo para llamar a un metodo en especial
	@Before("execution(public * com.appservice..*(..))")
	public void insertarUsu() {
		System.out.print("Entramos");

	}

	@Pointcut("within(com.xyz.trading..*)")
	public void inTrading() {
		System.out.print("Entramos Pointcut withIn");
	}

	@Pointcut("publicMethod() && inTrading()")
	public void tradingOperation() {
		System.out.print("Entramos Pointcut con operadores");
	}

}
