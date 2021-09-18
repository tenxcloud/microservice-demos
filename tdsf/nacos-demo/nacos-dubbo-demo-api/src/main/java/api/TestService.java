package api;

/**
 * @author wangshixiong
 * @date 2020-12-15 2:38 下午
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
