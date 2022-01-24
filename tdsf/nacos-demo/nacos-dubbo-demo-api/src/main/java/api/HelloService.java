package api;

/**
 * @author zhangshuo
 */
public interface HelloService {
    /**
     * api
     * @param msg
     * @return
     */
    String sayHello(String msg);

    /**
     * api
     * @param msg
     * @param interval
     * @return
     */
    String slowCall(String msg, Long interval);
}
