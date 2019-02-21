package services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CounterAspect {
    Map<String, Integer> initialCountMap = java.util.Collections.synchronizedMap(new HashMap<>());

    @Around("execution(* services.LocationsService.createLocation(..))")
    public synchronized Object countInitials(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();

        String nameOfLocation = (String)joinPoint.getArgs()[0];
        String initial = nameOfLocation.substring(0,1);
        if(initialCountMap.get(initial) == null){
            initialCountMap.put(initial, 1);
        }
        else{
            int numberOfOccurence = initialCountMap.get(initial);
            initialCountMap.put(initial, ++numberOfOccurence);
        }

        return result;
    }

    public Map<String, Integer> getInitialCountMap() {
        return initialCountMap;
    }
}
