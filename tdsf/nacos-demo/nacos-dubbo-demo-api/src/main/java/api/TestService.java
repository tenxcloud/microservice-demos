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

    /**
     * api
     * @param msg
     * @param interval
     * @return
     */
    String slowCall(String msg, Long interval);
}
