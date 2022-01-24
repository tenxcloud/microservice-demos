package api;

/**
 * @author zhangshuo
 */
public interface TestService {
    /**
     * api
     * @param msg
     * @return
     */
    String test(String msg);
    String slowCall(String msg,long interval);
}
