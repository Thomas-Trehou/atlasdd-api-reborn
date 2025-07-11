package fr.ttl.atlasdd.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect      // Définit cette classe comme un Aspect
@Component   // Permet à Spring de la détecter et de l'instancier
@Slf4j       // Fournit un logger SLF4J, plus standard que Log4j2 et compatible avec la conf par défaut
public class LoggingAspect {

    /**
     * Pointcut qui cible toutes les méthodes publiques dans les classes annotées avec @Service.
     * C'est parfait pour logger l'ensemble de votre couche de service.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *) && execution(public * *(..))")
    public void serviceMethodsPointcut() {
    }

    /**
     * "Advice" qui s'exécute "autour" des méthodes capturées par le pointcut.
     * Il permet de logger l'entrée, la sortie, les exceptions et le temps d'exécution.
     */
    @Around("serviceMethodsPointcut()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // Nom de la classe et de la méthode pour des logs clairs
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info(">> Entrée: {}.{}() avec arguments = {}", className, methodName, Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();

        try {
            // Exécution de la méthode cible
            Object result = joinPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;

            log.info("<< Sortie: {}.{}() avec résultat = {} (Exécution en {} ms)", className, methodName, result, timeTaken);
            return result;
        } catch (Exception e) {
            long timeTaken = System.currentTimeMillis() - startTime;
            // Log de l'erreur avec la stack trace complète
            log.error("!! Exception dans {}.{}() après {} ms", className, methodName, timeTaken, e);
            // Il est crucial de relancer l'exception pour ne pas altérer le comportement de l'application
            throw e;
        }
    }
}