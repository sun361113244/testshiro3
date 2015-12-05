package sys.aspects;

import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

public interface LogAspects
{
    public Object logRound(ProceedingJoinPoint pjp)throws Throwable;
}
