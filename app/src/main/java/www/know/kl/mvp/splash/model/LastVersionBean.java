package www.retrofit.com.retrofitrxjavademo.mvp.splash.model;

/**
 * Created by kui.liu on 2016/11/23.
 */
public class LastVersionBean {


    /**
     * status : 1
     * msg : 【更新内容】（后略）
     * latest : 2.2.0
     */

    private int status;
    private String msg;
    private String latest;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
}
